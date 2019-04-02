package service;

import java.util.List;

import model.Item;
import model.ItemIngrediente;

public interface IItemIngredienteService {
	List<ItemIngrediente> obtenerItemIngredientes(Item item, Integer pagina, Integer cantidad);
	ItemIngrediente obtenerItemIngrediente(Integer idItemIngrediente);
	Long contarItemIngredientes();
	void save(ItemIngrediente itemIngrediente);
}
