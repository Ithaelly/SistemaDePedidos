package com.apiweb.pedidos.services;

import com.apiweb.pedidos.models.Produto;
import com.apiweb.pedidos.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {
    @Autowired
    private ProdutoRepository produtoRepository;

    // Listar todos os produtos
    public List<Produto> listarTodosProdutos() {
        return produtoRepository.findAll();
    }

    // Listar todos as pizzas
    public List<Produto> listarPizzas() {
        return produtoRepository.findByCategoria("pizza");
    }

    // Buscar produto por ID
    public Produto getProdutoById(long id) {
        return produtoRepository.findById(id).orElse(null);
    }

    // Salvar novo produto
    public Produto salvarProduto(Produto produto) {
        return produtoRepository.save(produto);
    }

    // Atualizar produto
    public Produto atualizarProduto(long id, Produto produtoAtualizado) {
        Produto produto = produtoRepository.findById(id).orElse(null);

        if (produto != null) {
            // Se o produto for encontrado, atualize os dados
            produto.setNome(produtoAtualizado.getNome());
            produto.setQuantidade(produtoAtualizado.getQuantidade());
            produto.setValor(produtoAtualizado.getValor());
            produto.setCategoria(produtoAtualizado.getCategoria());
            produto.setImagemUrl(produtoAtualizado.getImagemUrl());
            return produtoRepository.save(produto); // Salva o produto atualizado
        } else {
            // Caso não encontre o produto, crie um novo com o id fornecido
            produtoAtualizado.setId(id);
            return produtoRepository.save(produtoAtualizado); // Salva o novo produto
        }
    }

    // Deletar produto
    public void deleteProduto(long id) {
        produtoRepository.deleteById(id);
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