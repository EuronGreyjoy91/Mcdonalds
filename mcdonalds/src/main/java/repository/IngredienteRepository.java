package repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import model.Ingrediente;

@Repository
@Transactional
public interface IngredienteRepository extends JpaRepository<Ingrediente, Integer>, JpaSpecificationExecutor<Ingrediente> {

	@Query("SELECT i FROM Ingrediente i WHERE i.nombre LIKE CONCAT('%',:nombre,'%') "
			+ "ORDER BY id DESC")
	List<Ingrediente> obtenerIngredientes(@Param("nombre") String nombre, Pageable pageable);
	
	@Query("SELECT COUNT(i) FROM Ingrediente i WHERE i.nombre LIKE CONCAT('%',:nombre,'%')")
	Long contarIngredientes(@Param("nombre") String nombre);
	
}
