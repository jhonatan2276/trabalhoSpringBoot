package br.edu.unidavi.trabalhofinal.entitys;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@ApiModel("Cliente")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    String nome;
    String email;
    String cpf;
    @JsonFormat(pattern = "dd-MM-yyyy")
    Date dataNascimento;
}