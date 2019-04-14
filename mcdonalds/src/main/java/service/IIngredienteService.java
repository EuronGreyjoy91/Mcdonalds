package service;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import model.Ingrediente;

public interface IIngredienteService {
	List<Ingrediente> obtenerIngredientes(Specification<Ingrediente> itemSpecification, Pageable pageable);
	List<Ingrediente> obtenerTodosLosIngredientes();
	Ingrediente obtenerIngrediente(Integer id);
	Long contarIngredientes(Specification<Ingrediente> ingredienteSpecification);
	void save(Ingrediente ingrediente);
}
