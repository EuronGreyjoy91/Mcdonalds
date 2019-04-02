package repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import model.Item;
import model.ItemIngrediente;

@Repository
@Transactional
public interface ItemIngredienteRepository extends JpaRepository<ItemIngrediente, Integer>{

//	@Query("SELECT ii FROM ItemIngrediente ii WHERE ii.item.id = :item ORDER BY ii.id ASC")
//	List<ItemIngrediente> findByItem(@Param("item") Integer item);
	
	List<ItemIngrediente> findByItem(Item item, Pageable pageable);

}
