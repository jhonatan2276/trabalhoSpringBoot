package br.edu.unidavi.trabalhofinal.controllers;

import br.edu.unidavi.trabalhofinal.assemblers.EnderecoResourceAssembler;
import br.edu.unidavi.trabalhofinal.entitys.Endereco;
import br.edu.unidavi.trabalhofinal.repositorys.EnderecoRepository;
import br.edu.unidavi.trabalhofinal.resources.EnderecoResource;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api("EndPoint de Endereço")
@RestController
@RequestMapping("/enderecos")
public class EnderecoRestController {

    @Autowired
    EnderecoRepository repository;

    EnderecoResourceAssembler assembler = new EnderecoResourceAssembler();

    @Secured("ROLE_USER")
    @ApiOperation("Retorna todos os Endereços")
    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<List<EnderecoResource>> getAll() {
        return new ResponseEntity<>(assembler.toResources(repository.findAll()), HttpStatus.OK);
    }

    @Secured("ROLE_USER")
    @ApiOperation("Retorna um Endereço especifico")
    @GetMapping("/{id}")
    public ResponseEntity<EnderecoResource> get(@PathVariable Long id) {
        Endereco endereco = repository.findOne(id);
        if (endereco != null) {
            return new ResponseEntity<>(assembler.toResource(endereco), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Secured("ROLE_MANAGER")
    @ApiOperation("Cria um novo Endereço")
    @PostMapping
    public ResponseEntity<EnderecoResource> create(@RequestBody Endereco endereco) {
        endereco = repository.save(endereco);
        if (endereco != null) {
            return new ResponseEntity<>(assembler.toResource(endereco), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @Secured("ROLE_MANAGER")
    @ApiOperation("Altera os dados de um Endereço")
    @PutMapping("/{id}")
    public ResponseEntity<EnderecoResource> update(@PathVariable Long id, @RequestBody Endereco endereco) {
        if (endereco != null) {
            endereco.setId(id);
            endereco = repository.save(endereco);
            return new ResponseEntity<>(assembler.toResource(endereco), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @Secured("ROLE_ADMIN")
    @ApiOperation("Deleta um Endereço")
    @DeleteMapping("/{id}")
    public ResponseEntity<EnderecoResource> delete(@PathVariable Long id) {
        Endereco endereco = repository.findOne(id);
        if (endereco != null) {
            repository.delete(endereco);
            return new ResponseEntity<>(assembler.toResource(endereco), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }
}