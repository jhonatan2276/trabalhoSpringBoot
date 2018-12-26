package br.edu.unidavi.trabalhofinal.controllers;

import br.edu.unidavi.trabalhofinal.assemblers.PedidoResourceAssembler;
import br.edu.unidavi.trabalhofinal.entitys.Pedido;
import br.edu.unidavi.trabalhofinal.repositorys.PedidoRepository;
import br.edu.unidavi.trabalhofinal.resources.PedidoResource;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Api("EndPoint de Pedido")
@RestController
@RequestMapping("/pedidos")
public class PedidoRestController {

    @Autowired
    PedidoRepository repository;

    PedidoResourceAssembler assembler = new PedidoResourceAssembler();

    @Secured("ROLE_MANAGER")
    @ApiOperation("Retorna todos os Pedidos")
    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<List<PedidoResource>> getAll() {
        return new ResponseEntity<>(assembler.toResources(repository.findAll()), HttpStatus.OK);
    }

    @Secured("ROLE_MANAGER")
    @ApiOperation("Retorna um pedido pelo número")
    @GetMapping("/{numero}")
    public ResponseEntity<PedidoResource> get(@PathVariable Integer numero) {
        Pedido pedido = repository.findOne(numero);
        if (pedido != null) {
            return new ResponseEntity<>(assembler.toResource(pedido), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Secured("ROLE_MANAGER")
    @ApiOperation("Cria um novo Pedido")
    @PostMapping
    public ResponseEntity<PedidoResource> create(@RequestBody Pedido pedido) {
        pedido = repository.save(pedido);
        if (pedido != null) {
            return new ResponseEntity<>(assembler.toResource(pedido), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @Secured("ROLE_MANAGER")
    @ApiOperation("Altera os dados de um Pedido pelo número")
    @PutMapping("/{numero}")
    public ResponseEntity<PedidoResource> update(@PathVariable Integer numero, @RequestBody Pedido pedido) {
        if (pedido != null) {
            pedido.setNumero(numero);
            pedido = repository.save(pedido);
            return new ResponseEntity<>(assembler.toResource(pedido), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @Secured("ROLE_ADMIN")
    @ApiOperation("Deleta um Pedido pelo número")
    @DeleteMapping("/{numero}")
    public ResponseEntity<PedidoResource> delete(@PathVariable Integer numero) {
        Pedido pedido = repository.findOne(numero);
        if (pedido != null) {
            repository.delete(pedido);
            return new ResponseEntity<>(assembler.toResource(pedido), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @Secured("ROLE_MANAGER")
    @ApiOperation("Busca Pedidos pela data")
    @GetMapping("/data/{data}")
    public ResponseEntity<List<PedidoResource>> findByData(@PathVariable String data) throws ParseException {
        SimpleDateFormat formatador = new SimpleDateFormat("dd-MM-yyyy");
        Date dataFormatada = formatador.parse(data);
        return new ResponseEntity<>(assembler.toResources(repository.findByDataCriacao(dataFormatada)), HttpStatus.OK);
    }
}