package com.apiweb.pedidos.repository;

import org.springframework.stereotype.Repository;

@Repository
public interface ItensPedidoRepository extends org.springframework.data.jpa.repository.JpaRepository<com.apiweb.pedidos.models.ItensPedido, Long> {
    // Este repositório herda métodos CRUD do JpaRepository
    // Não é necessário adicionar métodos adicionais a menos que haja consultas personalizadas
}

/*
 * TODO comente aqui
 *
 * @author Ithaelly M. da S. de Medeiros - ithaelly.medeiros.103@ufrn.edu.br
 *
 * @version 1.0
 * @since 04/07/2025
 */