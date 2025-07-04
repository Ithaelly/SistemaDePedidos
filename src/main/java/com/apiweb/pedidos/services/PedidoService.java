package com.apiweb.pedidos.services;

import com.apiweb.pedidos.models.Pedido;
import com.apiweb.pedidos.models.StatusPedido;
import com.apiweb.pedidos.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class PedidoService {
    @Autowired
    private PedidoRepository pedidoRepository;

    // Método para obter pedidos com status específico
    public List<Pedido> obterPedidosPorStatus(StatusPedido status) {
        return pedidoRepository.findByStatus(status);
    }

    // Método para obter pedidos de um cliente específico
    public List<Pedido> obterPedidosPorCliente(Long clienteId) {
        return pedidoRepository.findByClienteId(clienteId);
    }

    // Método para contar o total de pedidos
    public long obterTotalPedidos() {
        return pedidoRepository.contarTotalPedidos();
    }

    // Método para somar o total faturado dos pedidos com status 'FINALIZADO'
    public BigDecimal obterTotalFaturado() {
        return pedidoRepository.somarTotalFaturado();
    }

    // Método para listar pedidos pendentes (status 'EM_ANDAMENTO')
    public List<Pedido> obterPedidosPendentes() {
        return pedidoRepository.listarPedidosPendentes();
    }

    // Método para listar clientes com mais pedidos
    public List<Object[]> obterClientesMaisAtivos() {
        return pedidoRepository.listarClientesMaisAtivos();
    }

    // Criar novo pedido
    public Pedido createPedido(Pedido pedido) {
        return pedidoRepository.save(pedido);
    }

    // Atualizar pedido e salvar se não existir
    public Pedido updatePedido(long id, Pedido pedidoAtualizado) {
        return pedidoRepository.findById(id)
                .map(pedido -> {
                    pedido.setStatus(pedidoAtualizado.getStatus());
                    pedido.setValorTotal(pedidoAtualizado.getValorTotal());
                    pedido.setCliente(pedidoAtualizado.getCliente());
                    return pedidoRepository.save(pedido);
                })
                .orElseGet(() -> {
                    pedidoAtualizado.setId(id);
                    return pedidoRepository.save(pedidoAtualizado);
                });
    }

    // Deletar pedido
    public void deletePedido(long id) {
        pedidoRepository.deleteById(id);
    }
}

/*
 * TODO comente aqui
 *
 * @author Ithaelly M. da S. de Medeiros - ithaelly.medeiros.103@ufrn.edu.br
 *
 * @version 1.0
 * @since 30/06/2025
 */