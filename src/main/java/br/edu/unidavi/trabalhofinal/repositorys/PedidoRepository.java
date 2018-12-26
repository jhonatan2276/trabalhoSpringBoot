package br.edu.unidavi.trabalhofinal.repositorys;

import br.edu.unidavi.trabalhofinal.entitys.Pedido;
import io.swagger.annotations.ApiParam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Date;
import java.util.List;

@RepositoryRestResource(exported = false)
public interface PedidoRepository extends JpaRepository<Pedido, Integer> {

    @ApiParam("Traz informações conforme o parâmetro DATA")
    @Query("SELECT p FROM Pedido p WHERE p.dataCriacao = :data")
    List<Pedido> findByDataCriacao(@Param("data") Date data);
}