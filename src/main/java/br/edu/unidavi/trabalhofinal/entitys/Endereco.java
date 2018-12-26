package br.edu.unidavi.trabalhofinal.entitys;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@ApiModel("Endereço")
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    String rua;
    String cidade;
    String estado;
    String cep;

    @OneToOne
    @JoinColumn(name = "cliente_id")
    Cliente cliente;
}