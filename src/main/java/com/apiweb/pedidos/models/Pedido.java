package com.apiweb.pedidos.models;

import jakarta.persistence.*;
import org.springframework.data.repository.cdi.Eager;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "pedidos")
public class Pedido implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pedido_seq")
    @SequenceGenerator(name = "pedido_seq", sequenceName = "pedidos_id_seq", allocationSize = 1)
    private long id;

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "pedido_id")  // Vincula o pedido com os produtos
    private List<ItensPedido> itens; // Lista de itens do pedido, cada item é um produto + quantidade

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private StatusPedido status;  // Status do pedido, usando um Enum

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "data_pedido", nullable = false)
    private Date dataPedido;  // Data de criação do pedido

    @Column(name = "valor_total_pedido", nullable = false)
    private BigDecimal valorTotalPedido; // Valor total do pedido

    // Getters e Setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<ItensPedido> getItens() {
        return itens;
    }

    public void setItens(List<ItensPedido> itens) {
        this.itens = itens;
    }

    public StatusPedido getStatus() {
        return status;
    }

    public void setStatus(StatusPedido status) {
        this.status = status;
    }

    public Date getDataPedido() {
        return dataPedido;
    }

    public void setDataPedido(Date dataPedido) {
        this.dataPedido = dataPedido;
    }

    // Método para calcular o valor total do pedido somando o valor de todos os itens
    public BigDecimal getValorTotalPedido() {
        BigDecimal valorTotalPedido = BigDecimal.ZERO;
        for (ItensPedido item : itens) {
            valorTotalPedido = valorTotalPedido.add(item.getValorTotal()); // Usando o valorTotal calculado em ItensPedido
        }
        return valorTotalPedido;
    }

    public void setValorTotalPedido(BigDecimal valorTotalPedido) {
        this.valorTotalPedido = valorTotalPedido;
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