package br.edu.unidavi.trabalhofinal.controllers;

import br.edu.unidavi.trabalhofinal.assemblers.ClienteResourceAssembler;
import br.edu.unidavi.trabalhofinal.entitys.Cliente;
import br.edu.unidavi.trabalhofinal.repositorys.ClienteRepository;
import br.edu.unidavi.trabalhofinal.resources.ClienteResource;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api("EndPoint de Cliente")
@RestController
@RequestMapping("/clientes")
public class ClienteRestController {

    @Autowired
    ClienteRepository repository;

    ClienteResourceAssembler assembler = new ClienteResourceAssembler();

    @Secured("ROLE_USER")
    @ApiOperation("Retorna todos os Clientes")
    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<List<ClienteResource>> getAll() {
        return new ResponseEntity<>(assembler.toResources(repository.findAll()), HttpStatus.OK);
    }

    @Secured("ROLE_USER")
    @ApiOperation("Retorna um Cliente especifico")
    @GetMapping("/{id}")
    public ResponseEntity<ClienteResource> get(@PathVariable Long id) {
        Cliente cliente = repository.findOne(id);
        if (cliente != null) {
            return new ResponseEntity<>(assembler.toResource(cliente), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Secured("ROLE_MANAGER")
    @ApiOperation("Cria um novo Cliente")
    @PostMapping
    public ResponseEntity<ClienteResource> create(@RequestBody Cliente cliente) {
        cliente = repository.save(cliente);
        if (cliente != null) {
            return new ResponseEntity<>(assembler.toResource(cliente), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @Secured("ROLE_MANAGER")
    @ApiOperation("Altera os dados de um Cliente")
    @PutMapping("/{id}")
    public ResponseEntity<ClienteResource> update(@PathVariable Long id, @RequestBody Cliente cliente) {
        if (cliente != null) {
            cliente.setId(id);
            cliente = repository.save(cliente);
            return new ResponseEntity<>(assembler.toResource(cliente), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @Secured("ROLE_ADMIN")
    @ApiOperation("Deleta um Cliente")
    @DeleteMapping("/{id}")
    public ResponseEntity<ClienteResource> delete(@PathVariable Long id) {
        Cliente cliente = repository.findOne(id);
        if (cliente != null) {
            repository.delete(cliente);
            return new ResponseEntity<>(assembler.toResource(cliente), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @Secured("ROLE_USER")
    @ApiOperation("Busca Clientes por NOME")
    @GetMapping("/nome/{nome}")
    public ResponseEntity<List<ClienteResource>> findByNome(@PathVariable String nome) {
        return new ResponseEntity<>(assembler.toResources(repository.findByNomeContaining(nome)), HttpStatus.OK);
    }

    @Secured("ROLE_USER")
    @ApiOperation("Busca Clientes por RUA")
    @GetMapping("/rua/{rua}")
    public ResponseEntity<List<ClienteResource>> findByRua(@PathVariable String rua) {
        return new ResponseEntity<>(assembler.toResources(repository.findClienteByRua("%"+rua+"%")), HttpStatus.OK);
    }

    @Secured("ROLE_USER")
    @ApiOperation("Busca Clientes por CIDADE")
    @GetMapping("/cidade/{cidade}")
    public ResponseEntity<List<ClienteResource>> findByCidade(@PathVariable String cidade) {
        return new ResponseEntity<>(assembler.toResources(repository.findClienteByCidade("%"+cidade+"%")), HttpStatus.OK);
    }

    @Secured("ROLE_USER")
    @ApiOperation("Busca Clientes por ESTADO")
    @GetMapping("/estado/{estado}")
    public ResponseEntity<List<ClienteResource>> findByEstado(@PathVariable String estado) {
        return new ResponseEntity<>(assembler.toResources(repository.findClienteByEstado("%"+estado+"%")), HttpStatus.OK);
    }
}