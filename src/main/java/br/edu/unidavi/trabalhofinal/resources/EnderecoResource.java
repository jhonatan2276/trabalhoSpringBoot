package br.edu.unidavi.trabalhofinal.resources;

import br.edu.unidavi.trabalhofinal.entitys.Endereco;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;

public class EnderecoResource extends Resource<Endereco> {

    public EnderecoResource(Endereco endereco, Link... links) {
        super(endereco, links);
    }
}