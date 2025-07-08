package com.apiweb.pedidos.controllers;

import com.apiweb.pedidos.models.Pedido;
import com.apiweb.pedidos.services.RelatorioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@Controller
@RequestMapping(value = "/api/relatorios")
public class RelatoriosController {
    @Autowired
    private RelatorioService relatorioService;

    // Resumo das Vendas
    @GetMapping("/resumo-vendas")
    public Object getResumoVendas() {
        return relatorioService.gerarResumoVendas();
    }

    // Pedidos Pendentes
    @GetMapping("/pedidos-pendentes")
    public List<Pedido> getPedidosPendentes() {
        return relatorioService.gerarPedidosPendentes();
    }

    // Clientes Mais Ativos
    @GetMapping("/clientes-ativos")
    public List<Object[]> getClientesMaisAtivos() {
        return relatorioService.gerarClientesMaisAtivos();
    }

    // Total Faturado
    @GetMapping("/total-faturado")
    public BigDecimal getTotalFaturado() {
        return relatorioService.totalFaturado();
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