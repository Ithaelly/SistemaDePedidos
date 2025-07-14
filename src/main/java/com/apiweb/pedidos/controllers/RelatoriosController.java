package com.apiweb.pedidos.controllers;

import com.apiweb.pedidos.models.Pedido;
import com.apiweb.pedidos.services.RelatorioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@Controller
@RequestMapping(value = "/relatorios")
public class RelatoriosController {
    @Autowired
    private RelatorioService relatorioService;

    // Resumo das Vendas
    @GetMapping("/resumo-vendas")
    public String getResumoVendas(Model model) {
        // Chama o serviço para obter os dados (array de objetos)
        Object[] resumoVendas = relatorioService.gerarResumoVendas();

        // Passa os dados do array para o modelo
        model.addAttribute("totalPedidos", resumoVendas[0]);
        model.addAttribute("valorTotal", resumoVendas[1]);
        model.addAttribute("produtosVendidos", resumoVendas[2]);

        // Dados dos pedidos pendentes
        model.addAttribute("pedidosPendentes", relatorioService.gerarPedidosPendentes());

        // Dados dos clientes mais ativos
        model.addAttribute("clientesAtivos", relatorioService.gerarClientesMaisAtivos());

        // Retorna o nome correto do template
        return "relatoriosDeVendas";  // O nome correto do template
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