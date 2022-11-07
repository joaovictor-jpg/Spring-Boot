package io.com.github.joaovictorjpg.domen.entity;

import io.com.github.joaovictorjpg.domen.enums.StatusPedido;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;
    @Column(name = "data_pedido")
    private LocalDate dataPedido;
    @Column(precision = 20, scale = 2)
    private BigDecimal total;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private StatusPedido status;
    @OneToMany
    @JoinColumn(name = "pedido")
    private List<ItemPedido> itemPedidos = new ArrayList<>();

    public Pedido(Cliente cliente, LocalDate dataPedido, BigDecimal total, List<ItemPedido> itemPedidos) {
        this.cliente = cliente;
        this.dataPedido = dataPedido;
        this.total = total;
        this.itemPedidos = itemPedidos;
    }
}
