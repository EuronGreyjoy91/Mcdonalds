package service;

import java.util.List;

import model.Item;

public interface IItemService {
	List<Item> obtenerItems(Integer pagina, Integer cantidad);
	List<Item> obtenerTodosLosItems();
	Item obtenerItem(Integer id);
	Long contarItems();
	void save(Item item);
}
