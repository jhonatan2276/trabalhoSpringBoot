package br.edu.unidavi.trabalhofinal.repositorys;

import br.edu.unidavi.trabalhofinal.entitys.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(exported = false)
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    List<Produto> findByNomeContaining(String nome);

    List<Produto> findByMarcaContaining(String marca);
}