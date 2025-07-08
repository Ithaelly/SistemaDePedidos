package com.apiweb.pedidos.controllers;

import com.apiweb.pedidos.services.ItensPedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/itens-pedido")
public class ItensPedidoController {

    private final ItensPedidoService itensPedidoService;

    @Autowired
    public ItensPedidoController(ItensPedidoService itensPedidoService) {
        this.itensPedidoService = itensPedidoService;
    }

    // Endpoint para deletar um item de pedido
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable Long id) {
        try {
            itensPedidoService.deleteItemById(id);
            return ResponseEntity.noContent().build(); // Retorna código 204 - Sem Conteúdo, se deletado com sucesso
        } catch (EmptyResultDataAccessException e) {
            return ResponseEntity.notFound().build(); // Retorna código 404 - Não Encontrado, se o item não existir
        }
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