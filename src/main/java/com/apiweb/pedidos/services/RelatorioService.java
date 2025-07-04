package com.apiweb.pedidos.services;

import com.apiweb.pedidos.models.Pedido;
import com.apiweb.pedidos.models.StatusPedido;
import com.apiweb.pedidos.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class RelatorioService {
    @Autowired
    private PedidoRepository pedidoRepository;

    // Método para gerar o Resumo das Vendas
    public Object gerarResumoVendas() {
        long totalPedidos = pedidoRepository.contarTotalPedidos();
        BigDecimal totalFaturado = pedidoRepository.somarTotalFaturado();
        long quantidadeProdutosVendidos = pedidoRepository.contarQuantidadeProdutosVendidos();

        return new Object[]{totalPedidos, totalFaturado, quantidadeProdutosVendidos};
    }

    // Método para gerar os Pedidos Pendentes
    public List<Pedido> gerarPedidosPendentes() {
        return pedidoRepository.listarPedidosPendentes();
    }

    // Método para gerar clientes mais ativos
    public List<Object[]> gerarClientesMaisAtivos() {
        return pedidoRepository.listarClientesMaisAtivos();
    }

    // Método para gerar o Total Faturado
    public BigDecimal totalFaturado() {
        return pedidoRepository.somarTotalFaturado();
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