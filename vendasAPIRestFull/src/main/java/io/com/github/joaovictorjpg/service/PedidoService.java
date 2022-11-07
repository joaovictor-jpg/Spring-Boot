package io.com.github.joaovictorjpg.service;

import io.com.github.joaovictorjpg.domen.entity.Pedido;
import io.com.github.joaovictorjpg.rest.dto.AtualizarStatusPedidoDTO;
import io.com.github.joaovictorjpg.rest.dto.PedidoDTO;

import java.util.Optional;

public interface PedidoService {

    Pedido salvar(PedidoDTO dto);

    Optional<Pedido> findById(Integer id);
    void updateStatus(Integer id, AtualizarStatusPedidoDTO atualizarStatusPedidoDTO);

}
