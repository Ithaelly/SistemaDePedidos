package com.apiweb.pedidos.repository;

import com.apiweb.pedidos.models.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    // Este repositório herda métodos CRUD do JpaRepository
    // Não é necessário adicionar métodos adicionais a menos que haja consultas personalizadas
}

/*
 * TODO comente aqui
 *
 * @author Ithaelly M. da S. de Medeiros - ithaelly.medeiros.103@ufrn.edu.br
 *
 * @version 1.0
 * @since 30/06/2025
 */