package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import model.Item;
import repository.ItemRepository;

@Service
public class IItemServiceImpl implements IItemService{

	@Autowired
	private ItemRepository itemRepository;
	
	@Override
	public List<Item> obtenerItems(Integer pagina, Integer cantidad) {
		return itemRepository.findAll(PageRequest.of(pagina, cantidad, Sort.by("id").ascending())).getContent();
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
	public Long contarItems() {
		return itemRepository.count();
	}

	@Override
	public List<Item> obtenerTodosLosItems() {
		return itemRepository.findAll(Sort.by("id").ascending());
	}
}
