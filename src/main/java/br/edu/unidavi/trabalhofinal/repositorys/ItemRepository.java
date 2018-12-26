package br.edu.unidavi.trabalhofinal.repositorys;

import br.edu.unidavi.trabalhofinal.entitys.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(exported = false)
public interface ItemRepository extends JpaRepository<Item, Long> {
}