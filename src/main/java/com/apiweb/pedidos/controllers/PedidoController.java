package com.apiweb.pedidos.controllers;

import com.apiweb.pedidos.models.Pedido;
import com.apiweb.pedidos.models.StatusPedido;
import com.apiweb.pedidos.services.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@Controller
public class PedidoController {
    @Autowired
    private PedidoService pedidoService;

    // Listar pedidos por status (Exemplo: "EM_ANDAMENTO", "FINALIZADO")
    @GetMapping("/api/pedidos/status/{status}")
    @ResponseBody
    public List<Pedido> listarPedidosPorStatus(@PathVariable String status) {
        return pedidoService.obterPedidosPorStatus(StatusPedido.valueOf(status));
    }

    // Listar todos os pedidos (opcional: pode ser filtrado por status, cliente, etc)
    @GetMapping("/api/pedidos")
    @ResponseBody
    public List<Pedido> listarPedidos() {
        return pedidoService.obterPedidosPorStatus(null);  // Lista todos os pedidos
    }

    // Endpoint para renderizar a página HTML com Thymeleaf
    @GetMapping("/home")
    public String home(Model model) {
        List<Pedido> pedidos = pedidoService.obterPedidosPorStatus(null);  // Obter todos os pedidos
        model.addAttribute("pedidos", pedidos);  // Passa a lista de pedidos para o template
        return "index";  // Nome do template (paginaPedidos.html)
    }

    // Método para tarefa 1
    @GetMapping("/tarefa1")
    public String tarefa1() {
        // Retorna a página de pizzas
        return "tarefa1"; // pizzas.html na pasta templates
    }

    // Endpoint para obter pedidos de um cliente específico
    @GetMapping("/cliente/{clienteId}")
    public List<Pedido> obterPedidosPorCliente(@PathVariable Long clienteId) {
        return pedidoService.obterPedidosPorCliente(clienteId);
    }

    // Endpoint para contar o total de pedidos
    @GetMapping("/total")
    public long obterTotalPedidos() {
        return pedidoService.obterTotalPedidos();
    }

    // Endpoint para somar o total faturado dos pedidos finalizados
    @GetMapping("/faturado")
    public BigDecimal obterTotalFaturado() {
        return pedidoService.obterTotalFaturado();
    }

    // Endpoint para listar os pedidos pendentes
    @GetMapping("/pendentes")
    public List<Pedido> obterPedidosPendentes() {
        return pedidoService.obterPedidosPendentes();
    }

    // Endpoint para listar clientes mais ativos
    @GetMapping("/clientes-ativos")
    public List<Object[]> obterClientesMaisAtivos() {
        return pedidoService.obterClientesMaisAtivos();
    }

    // Criar novo pedido
    @PostMapping
    public ResponseEntity<Pedido> createPedido(@RequestBody Pedido pedido) {
        Pedido pedidoCriado = pedidoService.createPedido(pedido);
        return new ResponseEntity<>(pedidoCriado, HttpStatus.CREATED);  // Retorna o pedido criado com o status 201
    }

    // Atualizar pedido
    @PutMapping("/{id}")
    public ResponseEntity<Pedido> updatePedido(@PathVariable long id, @RequestBody Pedido pedidoAtualizado) {
        Pedido pedido = pedidoService.updatePedido(id, pedidoAtualizado);
        return new ResponseEntity<>(pedido, HttpStatus.OK);  // Retorna o pedido atualizado com o status 200
    }

    // Deletar pedido
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePedido(@PathVariable long id) {
        pedidoService.deletePedido(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);  // Retorna 204 - No Content
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