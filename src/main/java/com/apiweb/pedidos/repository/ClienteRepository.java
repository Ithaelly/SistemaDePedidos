package com.apiweb.pedidos.repository;

import com.apiweb.pedidos.models.Cliente;
import com.apiweb.pedidos.models.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    // Aqui você pode adicionar consultas customizadas, se necessário.
    Cliente findByCpf(String cpf);  // Busca por CPF
}

/*
 * TODO comente aqui
 *
 * @author Ithaelly M. da S. de Medeiros - ithaelly.medeiros.103@ufrn.edu.br
 *
 * @version 1.0
 * @since 04/07/2025
 */