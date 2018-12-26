package br.edu.unidavi.trabalhofinal.repositorys;

import br.edu.unidavi.trabalhofinal.entitys.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(exported = false)
public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
}