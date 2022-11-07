package io.com.github.joaovictorjpg.domen.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Pedido {

    private Integer id;
    private Cliente cliente;
    private LocalDate dataPedi;
    private BigDecimal total;

    public Pedido() {
    }

    public Pedido(Integer id, Cliente cliente, LocalDate dataPedi, BigDecimal total) {
        this.id = id;
        this.cliente = cliente;
        this.dataPedi = dataPedi;
        this.total = total;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public LocalDate getDataPedi() {
        return dataPedi;
    }

    public void setDataPedi(LocalDate dataPedi) {
        this.dataPedi = dataPedi;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }
}
