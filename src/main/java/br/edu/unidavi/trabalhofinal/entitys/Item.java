package br.edu.unidavi.trabalhofinal.entitys;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@ApiModel("Item")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    Integer quantidade;
    Double total;

    @ManyToOne
    @JoinColumn(name = "pedido_numero")
    Pedido pedido;

    @OneToOne
    @JoinColumn(name = "produto_id")
    Produto produto;
}