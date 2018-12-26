package br.edu.unidavi.trabalhofinal.assemblers;

import br.edu.unidavi.trabalhofinal.controllers.EnderecoRestController;
import br.edu.unidavi.trabalhofinal.entitys.Endereco;
import br.edu.unidavi.trabalhofinal.resources.EnderecoResource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

public class EnderecoResourceAssembler extends ResourceAssemblerSupport<Endereco, EnderecoResource> {

    public EnderecoResourceAssembler() {
        super(Endereco.class, EnderecoResource.class);
    }

    @Override
    public EnderecoResource toResource(Endereco endereco) {
        return new EnderecoResource(endereco, ControllerLinkBuilder.linkTo(methodOn(EnderecoRestController.class).get(endereco.getId())).withSelfRel());
    }

    @Override
    protected EnderecoResource instantiateResource(Endereco endereco) {
        return new EnderecoResource(endereco);
    }
}