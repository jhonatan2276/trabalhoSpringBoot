package br.edu.unidavi.trabalhofinal.resources;

import br.edu.unidavi.trabalhofinal.entitys.Cliente;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;

public class ClienteResource extends Resource<Cliente> {

    public ClienteResource(Cliente cliente, Link... links) {
        super(cliente, links);
    }
}