package com.apiweb.pedidos.repository;

import com.apiweb.pedidos.models.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    // Este repositório herda métodos CRUD do JpaRepository
    List<Produto> findByCategoria(String categoria);
}

/*
 * TODO comente aqui
 *
 * @author Ithaelly M. da S. de Medeiros - ithaelly.medeiros.103@ufrn.edu.br
 *
 * @version 1.0
 * @since 30/06/2025
 */