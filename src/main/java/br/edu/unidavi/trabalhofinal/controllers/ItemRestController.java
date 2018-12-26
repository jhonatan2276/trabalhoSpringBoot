package br.edu.unidavi.trabalhofinal.controllers;

import br.edu.unidavi.trabalhofinal.assemblers.ItemAssembler;
import br.edu.unidavi.trabalhofinal.entitys.Item;
import br.edu.unidavi.trabalhofinal.repositorys.ItemRepository;
import br.edu.unidavi.trabalhofinal.resources.ItemResource;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api("EndPoint de Item")
@RestController
@RequestMapping("/itens")
public class ItemRestController {

    @Autowired
    ItemRepository repository;

    ItemAssembler assembler = new ItemAssembler();

    @Secured("ROLE_ADMIN")
    @ApiOperation("Retorna todos os Itens cadastrados")
    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<List<ItemResource>> getAll() {
        return new ResponseEntity<>(assembler.toResources(repository.findAll()), HttpStatus.OK);
    }

    @Secured("ROLE_ADMIN")
    @ApiOperation("Retorna um Item especifico")
    @GetMapping("/{id}")
    public ResponseEntity<ItemResource> get(@PathVariable Long id) {
        Item item = repository.findOne(id);
        if (item != null) {
            return new ResponseEntity<>(assembler.toResource(item), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Secured("ROLE_ADMIN")
    @ApiOperation("Cria um novo Item")
    @PostMapping
    public ResponseEntity<ItemResource> create(@RequestBody Item item) {
        item = repository.save(item);
        if (item != null) {
            return new ResponseEntity<>(assembler.toResource(item), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @Secured("ROLE_ADMIN")
    @ApiOperation("Altera os dados de um Item")
    @PutMapping("/{id}")
    public ResponseEntity<ItemResource> update(@PathVariable Long id, @RequestBody Item item) {
        if (item != null) {
            item.setId(id);
            item = repository.save(item);
            return new ResponseEntity<>(assembler.toResource(item), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @Secured("ROLE_ADMIN")
    @ApiOperation("Deleta um Item")
    @DeleteMapping("/{id}")
    public ResponseEntity<ItemResource> delete(@PathVariable Long id) {
        Item item = repository.findOne(id);
        if (item != null) {
            repository.delete(item);
            return new ResponseEntity<>(assembler.toResource(item), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }
}