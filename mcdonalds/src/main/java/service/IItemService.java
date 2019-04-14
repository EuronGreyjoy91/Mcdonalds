package service;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import model.Item;

public interface IItemService {
	List<Item> obtenerItems(Specification<Item> itemSpecification, Pageable pageable);
	List<Item> obtenerTodosLosItems();
	Item obtenerItem(Integer id);
	Long contarItems(Specification<Item> itemSpecification);
	void save(Item item);
}
