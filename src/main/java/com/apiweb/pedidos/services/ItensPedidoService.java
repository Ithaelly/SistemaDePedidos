package com.apiweb.pedidos.services;

import com.apiweb.pedidos.models.ItensPedido;
import com.apiweb.pedidos.repository.ItensPedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class ItensPedidoService {
    private final ItensPedidoRepository itensPedidoRepository;

    @Autowired
    public ItensPedidoService(ItensPedidoRepository itensPedidoRepository) {
        this.itensPedidoRepository = itensPedidoRepository;
    }

    // Atualiza um ItensPedido existente ou cria um novo se não encontrado
    public ItensPedido atualizarItensPedido(long id, ItensPedido itensPedidoAtualizado) {
        // Buscar o item de pedido pelo ID
        Optional<ItensPedido> itensPedidoOptional = itensPedidoRepository.findById(id);

        if (itensPedidoOptional.isPresent()) {
            // Se o item for encontrado, atualize os dados
            ItensPedido itensPedido = itensPedidoOptional.get();
            itensPedido.setProduto(itensPedidoAtualizado.getProduto());
            itensPedido.setPedido(itensPedidoAtualizado.getPedido());
            itensPedido.setQuantidade(itensPedidoAtualizado.getQuantidade());
            itensPedido.setPrecoUnitario(itensPedidoAtualizado.getPrecoUnitario());
            itensPedido.setValorTotal(calcularValorTotal(itensPedidoAtualizado)); // Atualiza o valor total baseado na quantidade e preço

            return itensPedidoRepository.save(itensPedido); // Salva o item de pedido atualizado
        } else {
            // Caso não encontre o item, cria um novo com o ID fornecido
            itensPedidoAtualizado.setId(id);
            itensPedidoAtualizado.setValorTotal(calcularValorTotal(itensPedidoAtualizado)); // Calcula o valor total para o novo item

            return itensPedidoRepository.save(itensPedidoAtualizado); // Salva o novo item de pedido
        }
    }

    // Método para calcular o valor total do item de pedido (quantidade * preço unitário)
    private BigDecimal calcularValorTotal(ItensPedido itensPedido) {
        return itensPedido.getQuantidade().multiply(itensPedido.getPrecoUnitario());
    }

    // Encontrar todos os itens do pedido
    public List<ItensPedido> buscarTodosItensDoPedido() {
        return itensPedidoRepository.findAll();
    }

    // Encontrar item de pedido por ID
    public Optional<ItensPedido> getItemById(Long id) {
        return itensPedidoRepository.findById(id);
    }

    // Deletar item de pedido
    public void deleteItemById(Long id) {
        itensPedidoRepository.deleteById(id);
    }
}

/*
 * TODO comente aqui
 *
 * @author Ithaelly M. da S. de Medeiros - ithaelly.medeiros.103@ufrn.edu.br
 *
 * @version 1.0
 * @since 04/07/2025
 */