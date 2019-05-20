package repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import model.Item;
import model.ItemIngrediente;

@Repository
@Transactional
public interface ItemIngredienteRepository extends JpaRepository<ItemIngrediente, Integer>, JpaSpecificationExecutor<ItemIngrediente>{
	List<ItemIngrediente> findByItem(Item item, Pageable pageable);
}
