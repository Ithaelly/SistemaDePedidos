package com.apiweb.pedidos.repository;

import com.apiweb.pedidos.models.Cliente;
import com.apiweb.pedidos.models.Pedido;
import com.apiweb.pedidos.models.StatusPedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {
    // Métodos derivados: Consultas simples
    List<Pedido> findByStatus(StatusPedido status); // Consulta pedidos por status
    List<Pedido> findByClienteId(Long clienteId); // Consulta pedidos por cliente

    // Métodos com consultas personalizadas: Consultas mais avançadas
    // Contar o total de pedidos (independente do status)
    @Query("SELECT COUNT(p) FROM Pedido p")
    long contarTotalPedidos();

    // Somar o total faturado = soma dos pedidos Finalizados
    @Query("SELECT SUM(p.valorTotalPedido) FROM Pedido p WHERE p.status = 'FINALIZADO'")
    BigDecimal somarTotalFaturado();

    // Contar o total de pedidos Finalizados
    @Query("SELECT COUNT(p) FROM Pedido p WHERE p.status = 'FINALIZADO'")
    long contarTotalPedidosFinalizados();

    // Contar a quantidade total de produtos vendidos
    @Query("SELECT SUM(ip.quantidade) FROM ItensPedido ip WHERE ip.pedido.status = 'FINALIZADO'")
    long contarQuantidadeProdutosVendidos();

    // Contar o total de pedidos Em Andamento
    @Query("SELECT COUNT(p) FROM Pedido p WHERE p.status = 'EM_ANDAMENTO'")
    long contarTotalPedidosEmAndamento();

    // Listagar de pedidos em andamento
    @Query("SELECT p FROM Pedido p WHERE p.status = 'EM_ANDAMENTO'")
    List<Pedido> listarPedidosPendentes();

    // Listar clientes com mais pedidos
    @Query("SELECT p.cliente, COUNT(p) FROM Pedido p GROUP BY p.cliente ORDER BY COUNT(p) DESC")
    List<Object[]> listarClientesMaisAtivos();

    @Query("SELECT p FROM Pedido p WHERE p.status = :status AND p.cliente = :cliente")
    List<Pedido> findByStatusAndCliente(@Param("status") StatusPedido status, @Param("cliente") Cliente cliente);
}
/*
 * TODO comente aqui
 *
 * @author Ithaelly M. da S. de Medeiros - ithaelly.medeiros.103@ufrn.edu.br
 *
 * @version 1.0
 * @since 30/06/2025
 */