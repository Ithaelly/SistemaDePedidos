package com.apiweb.pedidos.controllers;

import com.apiweb.pedidos.models.Produto;
import com.apiweb.pedidos.services.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value="/produtos")
public class ProdutoController {

    @Autowired
    ProdutoService produtoService;

    @GetMapping("/")
    public List<Produto> listaProdutos(){
        return produtoService.listarTodosProdutos();
    }

    // Método para pizzas
    @GetMapping("/pizzas")
    public String listarPizzas() {
        // Retorna a página de pizzas
        return "pizzas"; // pizzas.html na pasta templates
    }

    @GetMapping("/produto/{id}")
    public Produto listaProdutoUnico(@PathVariable(value="id") long id){
        return produtoService.getProdutoById(id);
    }

    @PostMapping("/")
    public Produto salvaProduto(@RequestBody Produto produto) {
        return produtoService.salvarProduto(produto);
    }

    @DeleteMapping("/produto")
    public void deletaProduto(@RequestBody Produto produto) {
        produtoService.deleteProduto(produto.getId());
    }

    @PutMapping("/produto")
    public Produto atualizaProduto(@RequestBody Produto produto) {
        return produtoService.atualizarProduto(produto.getId(), produto);
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