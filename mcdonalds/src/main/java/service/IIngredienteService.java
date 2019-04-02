package service;

import java.util.List;

import model.Ingrediente;

public interface IIngredienteService {
	List<Ingrediente> obtenerIngredientes(Integer pagina, Integer cantidad, String nombre);
	List<Ingrediente> obtenerTodosLosIngredientes();
	Ingrediente obtenerIngrediente(Integer id);
	Long contarIngredientes(String nombre);
	void save(Ingrediente ingrediente);
}
