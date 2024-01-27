package io.githob.cleiton.rest.controller;

import io.githob.cleiton.Service.PedidoService;
import io.githob.cleiton.domain.entity.Pedido;
import io.githob.cleiton.rest.dto.PedidoDTO;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    private PedidoService service;

    public PedidoController(PedidoService service) {
        this.service = service;
    }
    @PostMapping
    @ResponseStatus(CREATED)
    public Integer save (@RequestBody PedidoDTO dto) {
        Pedido pedido = service.salvar(dto);
        return pedido.getId();
    }
}
