package br.edu.unidavi.trabalhofinal.assemblers;

import br.edu.unidavi.trabalhofinal.controllers.PedidoRestController;
import br.edu.unidavi.trabalhofinal.entitys.Pedido;
import br.edu.unidavi.trabalhofinal.resources.PedidoResource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

public class PedidoResourceAssembler extends ResourceAssemblerSupport<Pedido, PedidoResource> {

    public PedidoResourceAssembler() {
        super(Pedido.class, PedidoResource.class);
    }

    @Override
    public PedidoResource toResource(Pedido pedido) {
        return new PedidoResource(pedido, ControllerLinkBuilder.linkTo(methodOn(PedidoRestController.class).get(pedido.getNumero())).withSelfRel());
    }

    @Override
    protected PedidoResource instantiateResource(Pedido pedido) {
        return new PedidoResource(pedido);
    }
}