package io.com.github.joaovictorjpg.service.imp;

import io.com.github.joaovictorjpg.domen.entity.Cliente;
import io.com.github.joaovictorjpg.domen.entity.ItemPedido;
import io.com.github.joaovictorjpg.domen.entity.Pedido;
import io.com.github.joaovictorjpg.domen.entity.Produto;
import io.com.github.joaovictorjpg.domen.enums.StatusPedido;
import io.com.github.joaovictorjpg.domen.repository.ClienteRepository;
import io.com.github.joaovictorjpg.domen.repository.ItemPedidoRepository;
import io.com.github.joaovictorjpg.domen.repository.PedidoRepository;
import io.com.github.joaovictorjpg.domen.repository.ProdutoRepository;
import io.com.github.joaovictorjpg.exception.PedidoNaoEncontradoException;
import io.com.github.joaovictorjpg.exception.RegraNegocioException;
import io.com.github.joaovictorjpg.rest.dto.AtualizarStatusPedidoDTO;
import io.com.github.joaovictorjpg.rest.dto.ItemPedidoDTO;
import io.com.github.joaovictorjpg.rest.dto.PedidoDTO;
import io.com.github.joaovictorjpg.service.PedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PedidoServiceImpl implements PedidoService {

    private final PedidoRepository pedidoRepository;
    private final ClienteRepository clienteRepository;
    private final ProdutoRepository produtoRepository;
    private final ItemPedidoRepository itemPedidoRepository;

    @Override
    @Transactional
    public Pedido salvar(PedidoDTO dto) {

        Integer idCliente = dto.getCliente();
        Cliente cliente = clienteRepository.findById(idCliente).orElseThrow(() -> new RegraNegocioException("Código do cliente inválido"));

        Pedido pedido = new Pedido();
        pedido.setCliente(cliente);
        pedido.setDataPedido(LocalDate.now());
        pedido.setTotal(dto.getTotal());
        pedido.setStatus(StatusPedido.REALIZADO);

        List<ItemPedido> itemsPedidos = conversterItems(pedido, dto.getItems());
        pedidoRepository.save(pedido);
        itemPedidoRepository.saveAll(itemsPedidos);
            pedido.setItemPedidos(itemsPedidos);

        return pedido;
    }

    @Override
    public Optional<Pedido> findById(Integer id) {

        return pedidoRepository.findByIdFetchItens(id);
    }

    @Override
    public void updateStatus(Integer id, AtualizarStatusPedidoDTO atualizarStatusPedidoDTO) {
        pedidoRepository.findById(id).map(pedido -> {
            pedido.setStatus(StatusPedido.valueOf(atualizarStatusPedidoDTO.getStatus()));
            return pedidoRepository.save(pedido);
        }).orElseThrow(() -> new PedidoNaoEncontradoException("Pedido não encontrado"));
    }

    private List<ItemPedido> conversterItems(Pedido pedido, List<ItemPedidoDTO> items) {

        if(items.isEmpty()) {
            throw new RegraNegocioException("Não é possível realizar um pedido sem items.");
        }

        return items.stream().map(dto -> {
            Integer idProduto = dto.getProduto();
            Produto produto = produtoRepository.findById(idProduto)
                    .orElseThrow(() -> new RegraNegocioException("Código de produto inválido: " + idProduto));
            ItemPedido itemPedido = new ItemPedido();
            itemPedido.setPedido(pedido);
            itemPedido.setProduto(produto);
            itemPedido.setQuantidade(dto.getQuantidade());
            return itemPedido;
        }).collect(Collectors.toList());

    }
}
