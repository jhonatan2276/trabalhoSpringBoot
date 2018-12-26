package br.edu.unidavi.trabalhofinal.assemblers;

import br.edu.unidavi.trabalhofinal.controllers.ItemRestController;
import br.edu.unidavi.trabalhofinal.entitys.Item;
import br.edu.unidavi.trabalhofinal.resources.ItemResource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

public class ItemAssembler extends ResourceAssemblerSupport<Item, ItemResource> {

    public ItemAssembler() {
        super(Item.class, ItemResource.class);
    }

    @Override
    public ItemResource toResource(Item item) {
        return new ItemResource(item, ControllerLinkBuilder.linkTo(methodOn(ItemRestController.class).get(item.getId())).withSelfRel());
    }

    @Override
    protected ItemResource instantiateResource(Item item) {
        return new ItemResource(item);
    }
}