package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import model.Item;
import repository.ItemRepository;

@Service
public class IItemServiceImpl implements IItemService{

	@Autowired
	private ItemRepository itemRepository;
	
	@Override
	public List<Item> obtenerItems(Specification<Item> itemSpecification, Pageable pageable) {
		return itemRepository.findAll(itemSpecification, pageable).getContent();
	}

	@Override
	public Item obtenerItem(Integer id) {
		return itemRepository.findById(id).get();
	}

	@Override
	public void save(Item item) {
		itemRepository.save(item);
	}

	@Override
	public Long contarItems(Specification<Item> itemSpecification) {
		return itemRepository.count(itemSpecification);
	}

	@Override
	public List<Item> obtenerTodosLosItems() {
		return itemRepository.findByActivoOrderByNombreAsc(1);
	}
	
	@Override
	public void cambiarEstadoItem(Integer id, Integer estado) {
		Item item = itemRepository.findById(id).get();
		item.setActivo(estado);
		itemRepository.save(item);
	}
}
