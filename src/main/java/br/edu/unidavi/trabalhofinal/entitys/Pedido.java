package br.edu.unidavi.trabalhofinal.entitys;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@ApiModel("Pedido")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer numero;
    Double total;
    @JsonFormat(pattern = "dd-MM-yyyy")
    Date dataCriacao;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    Cliente cliente;
}