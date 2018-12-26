package br.edu.unidavi.trabalhofinal.resources;

import br.edu.unidavi.trabalhofinal.entitys.Pedido;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;

public class PedidoResource extends Resource<Pedido> {

    public PedidoResource(Pedido pedido, Link... links) {
        super(pedido, links);
    }
}