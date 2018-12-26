package br.edu.unidavi.trabalhofinal.controllers;

import br.edu.unidavi.trabalhofinal.assemblers.ProdutoResourceAssembler;
import br.edu.unidavi.trabalhofinal.entitys.Produto;
import br.edu.unidavi.trabalhofinal.repositorys.ProdutoRepository;
import br.edu.unidavi.trabalhofinal.resources.ProdutoResource;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api("EndPoint de Produto")
@RestController
@RequestMapping("/produtos")
public class ProdutoRestController {

    @Autowired
    ProdutoRepository repository;

    ProdutoResourceAssembler assembler = new ProdutoResourceAssembler();

    @Secured("ROLE_USER")
    @ApiOperation("Retorna todos os Produtos")
    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<List<ProdutoResource>> getAll() {
        return new ResponseEntity<>(assembler.toResources(repository.findAll()), HttpStatus.OK);
    }

    @Secured("ROLE_USER")
    @ApiOperation("Retorna um Produto especifico")
    @GetMapping("/{id}")
    public ResponseEntity<ProdutoResource> get(@PathVariable Long id) {
        Produto produto = repository.findOne(id);
        if (produto != null) {
            return new ResponseEntity<>(assembler.toResource(produto), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Secured("ROLE_MANAGER")
    @ApiOperation("Cria um novo Produto")
    @PostMapping
    public ResponseEntity<ProdutoResource> create(@RequestBody Produto produto) {
        produto = repository.save(produto);
        if (produto != null) {
            return new ResponseEntity<>(assembler.toResource(produto), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @Secured("ROLE_MANAGER")
    @ApiOperation("Altera os dados de um Produto")
    @PutMapping("/{id}")
    public ResponseEntity<ProdutoResource> update(@PathVariable Long id, @RequestBody Produto produto) {
        if (produto != null) {
            produto.setId(id);
            produto = repository.save(produto);
            return new ResponseEntity<>(assembler.toResource(produto), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @Secured("ROLE_ADMIN")
    @ApiOperation("Deleta um Produto")
    @DeleteMapping("/{id}")
    public ResponseEntity<ProdutoResource> delete(@PathVariable Long id) {
        Produto produto = repository.findOne(id);
        if (produto != null) {
            repository.delete(produto);
            return new ResponseEntity<>(assembler.toResource(produto), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @Secured("ROLE_USER")
    @ApiOperation("Retorna Produtos pelo nome")
    @GetMapping("/nome/{nome}")
    public ResponseEntity<List<ProdutoResource>> findByNome(@PathVariable String nome) {
        return new ResponseEntity<>(assembler.toResources(repository.findByNomeContaining(nome)), HttpStatus.OK);
    }

    @Secured("ROLE_USER")
    @ApiOperation("Retorna todos os Produtos de determinada marca")
    @GetMapping("/marca/{marca}")
    public ResponseEntity<List<ProdutoResource>> findByMarca(@PathVariable String marca) {
        return new ResponseEntity<>(assembler.toResources(repository.findByMarcaContaining(marca)), HttpStatus.OK);
    }
}