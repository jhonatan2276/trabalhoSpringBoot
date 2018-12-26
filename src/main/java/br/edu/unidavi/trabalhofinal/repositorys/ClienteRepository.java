package br.edu.unidavi.trabalhofinal.repositorys;

import br.edu.unidavi.trabalhofinal.entitys.Cliente;
import io.swagger.annotations.ApiParam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(exported = false)
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    List<Cliente> findByNomeContaining(String nome);

    @ApiParam("Traz informações conforme o parâmetro RUA")
    @Query("SELECT c FROM Cliente c, Endereco e WHERE e.rua like :rua and c.id = e.cliente.id")
    List<Cliente> findClienteByRua(@Param("rua") String rua);

    @ApiParam("Traz informações conforme o parâmetro CIDADE")
    @Query("SELECT c FROM Cliente c, Endereco e WHERE e.cidade like :cidade and c.id = e.cliente.id")
    List<Cliente> findClienteByCidade(@Param("cidade") String cidade);

    @ApiParam("Traz informações conforme o parâmetro ESTADO")
    @Query("SELECT c FROM Cliente c, Endereco e WHERE e.estado like :estado and c.id = e.cliente.id")
    List<Cliente> findClienteByEstado(@Param("estado") String estado);
}