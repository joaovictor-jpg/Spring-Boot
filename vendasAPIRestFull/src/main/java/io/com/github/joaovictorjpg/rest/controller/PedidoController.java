package io.com.github.joaovictorjpg.rest.controller;

import io.com.github.joaovictorjpg.domen.entity.ItemPedido;
import io.com.github.joaovictorjpg.domen.entity.Pedido;
import io.com.github.joaovictorjpg.rest.dto.AtualizarStatusPedidoDTO;
import io.com.github.joaovictorjpg.rest.dto.InformacoesItemPedidoDTO;
import io.com.github.joaovictorjpg.rest.dto.InformacoesPedidoDTO;
import io.com.github.joaovictorjpg.rest.dto.PedidoDTO;
import io.com.github.joaovictorjpg.service.PedidoService;
import org.springframework.http.HttpStatus;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    private PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Integer save(@RequestBody @Valid PedidoDTO pedidoDTO) {
        Pedido pedido = pedidoService.salvar(pedidoDTO);
        return pedido.getId();
    }

    @GetMapping("/{id}")
    public InformacoesPedidoDTO getById(@PathVariable Integer id) {
        return pedidoService.findById(id)
                .map(p -> converter(p))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pedido não encontrado."));
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateStatus(@PathVariable Integer id, @RequestBody AtualizarStatusPedidoDTO atualizarStatusPedidoDTO) {
        pedidoService.updateStatus(id, atualizarStatusPedidoDTO);
    }

    private InformacoesPedidoDTO converter (Pedido pedido) {
        return InformacoesPedidoDTO.builder()
                .codigo(pedido.getId())
                .nomeCliente(pedido.getCliente().getNome())
                .cpf(pedido.getCliente().getCpf())
                .total(pedido.getTotal())
                .dataPedido(pedido.getDataPedido().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))
                .status(pedido.getStatus().name())
                .items(converter(pedido.getItemPedidos()))
                .build();
    }

    private List<InformacoesItemPedidoDTO> converter(List<ItemPedido> items) {
        if(CollectionUtils.isEmpty(items)) {
            return Collections.emptyList();
        }
        return items.stream().map(
                itemPedido -> InformacoesItemPedidoDTO.builder()
                        .nomeDoProduto(itemPedido.getProduto().getNome())
                        .descricao(itemPedido.getProduto().getDescricao())
                        .precoUnitario(itemPedido.getProduto().getPreco())
                        .quantidade(itemPedido.getQuantidade())
                        .precoUnitario(itemPedido.getProduto().getPreco())
                        .build()
        ).collect(Collectors.toList());
    }

}
