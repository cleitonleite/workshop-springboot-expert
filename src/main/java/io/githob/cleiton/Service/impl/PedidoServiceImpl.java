package io.githob.cleiton.Service.impl;

import io.githob.cleiton.Service.PedidoService;
import io.githob.cleiton.domain.entity.Cliente;
import io.githob.cleiton.domain.entity.ItemPedido;
import io.githob.cleiton.domain.entity.Pedido;
import io.githob.cleiton.domain.entity.Produto;
import io.githob.cleiton.domain.enums.StatusPedido;
import io.githob.cleiton.domain.repository.Clientes;
import io.githob.cleiton.domain.repository.ItemsPedido;
import io.githob.cleiton.domain.repository.Pedidos;
import io.githob.cleiton.domain.repository.Produtos;
import io.githob.cleiton.exception.RegraNegocioException;
import io.githob.cleiton.rest.dto.ItemPedidoDTO;
import io.githob.cleiton.rest.dto.PedidoDTO;
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

    private final Pedidos repository;
    private final Clientes clientesRepository;
    private final Produtos produtosRepository;
    private final ItemsPedido itemsPedidoRepository;

    @Override
    @Transactional
    public Pedido salvar(PedidoDTO dto) {
        Integer idCliente = dto.getCliente();
        Cliente cliente = clientesRepository
                .findById(idCliente)
                .orElseThrow(() -> new RegraNegocioException("Código de cliente inválido."));

        Pedido pedido = new Pedido();
        pedido.setTotal(dto.getTotal());
        pedido.setDataPedido(LocalDate.now());
        pedido.setCliente(cliente);
        pedido.setStatus(StatusPedido.REALIZADO);

        List<ItemPedido> itemsPedido = converterItems(pedido, dto.getItems());
        repository.save(pedido);
        itemsPedidoRepository.saveAll(itemsPedido);
        pedido.setItens(itemsPedido);
        return pedido;
    }

    @Override
    public Optional<Pedido> obterPedidoCompleto(Integer id) {
        return repository.findByIdaFetchItens(id);
    }


    private List<ItemPedido> converterItems (Pedido pedido, List<ItemPedidoDTO> items) {
        if(items.isEmpty()) {
            throw new RegraNegocioException("Não é possível realizar um pedido dem items.");
        }

        return items
                .stream().map(dto -> {
                    Integer idProduto = dto.getProduto();
                    Produto produto = produtosRepository
                            .findById(idProduto)
                            .orElseThrow(() -> new RegraNegocioException("Código de produto inválido." + idProduto));

                    ItemPedido itemPedido = new ItemPedido();
                    itemPedido.setQuantidade(dto.getQuantidade());
                    itemPedido.setPedido(pedido);
                    itemPedido.setProduto(produto);
                    return  itemPedido;
                }).collect(Collectors.toList());
    }
}
