$(document).ready(function() {

    // Quando o bot�o de carregar pedidos for clicado
    $('#carregarPedidos').click(function() {
        // Requisi��o AJAX para a API
        $.get('/api/pedidos', function(data) {
            let pedidosHtml = '';

            // Loop para criar a visualiza��o dos pedidos
            data.forEach(function(pedido) {
                pedidosHtml += `
                    <div class="pedido">
                        <h3>Pedido #${pedido.id}</h3>
                        <p>Status: ${pedido.status}</p>
                        <p>Total: R$ ${pedido.total}</p>
                    </div>
                `;
            });

            // Inserir os pedidos no HTML
            $('#pedidosList').html(pedidosHtml);
        });
    });
});
