// Função para carregar os produtos dinamicamente do backend
window.onload = function() {
    carregarProdutos();
};

// Função para obter produtos do backend
function carregarProdutos() {
    fetch('/api/produtos')  // Alterar para o endpoint correto para buscar produtos
        .then(response => response.json())
        .then(data => {
            const produtoSelect = document.getElementById("produto");
            data.forEach(produto => {
                const option = document.createElement("option");
                option.value = produto.id;  // Assumindo que o produto tem um campo 'id'
                option.textContent = produto.nome;  // Assumindo que o produto tem um campo 'nome'
                produtoSelect.appendChild(option);
            });
        })
        .catch(error => {
            console.error("Erro ao carregar produtos:", error);
        });
}

// Função para cadastrar o pedido
document.getElementById('formPedido').addEventListener('submit', function(e) {
    e.preventDefault();

    const cliente = document.getElementById('cliente').value;
    const produto = document.getElementById('produto').value;
    const quantidade = document.getElementById('quantidade').value;
    const status = document.getElementById('status').value;

    // Criando o objeto do pedido
    const pedido = {
        clienteId: cliente,  // Supondo que o cliente é representado por um ID
        produtoId: produto,
        quantidade: quantidade,
        status: status
    };

    // Enviando o pedido para a API
    fetch('/api/pedidos', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(pedido)
    })
        .then(response => response.json())
        .then(data => {
            document.getElementById('msg').textContent = "Pedido cadastrado com sucesso!";
            document.getElementById('msg').style.color = "green";
        })
        .catch(error => {
            document.getElementById('msg').textContent = "Erro ao cadastrar o pedido.";
            document.getElementById('msg').style.color = "red";
        });
});