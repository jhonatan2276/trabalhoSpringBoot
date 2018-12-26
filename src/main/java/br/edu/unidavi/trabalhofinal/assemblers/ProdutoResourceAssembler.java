package br.edu.unidavi.trabalhofinal.assemblers;

import br.edu.unidavi.trabalhofinal.controllers.ProdutoRestController;
import br.edu.unidavi.trabalhofinal.entitys.Produto;
import br.edu.unidavi.trabalhofinal.resources.ProdutoResource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

public class ProdutoResourceAssembler extends ResourceAssemblerSupport<Produto, ProdutoResource> {

    public ProdutoResourceAssembler() {
        super(Produto.class, ProdutoResource.class);
    }

    @Override
    public ProdutoResource toResource(Produto produto) {
        return new ProdutoResource(produto, ControllerLinkBuilder.linkTo(methodOn(ProdutoRestController.class).get(produto.getId())).withSelfRel());
    }

    @Override
    protected ProdutoResource instantiateResource(Produto produto) {
        return new ProdutoResource(produto);
    }
}