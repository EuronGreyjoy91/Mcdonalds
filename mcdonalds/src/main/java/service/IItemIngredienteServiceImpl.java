package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import model.Item;
import model.ItemIngrediente;
import repository.ItemIngredienteRepository;

@Service
public class IItemIngredienteServiceImpl implements IItemIngredienteService{

	@Autowired
	private ItemIngredienteRepository itemIngredienteRepository;
	
	@Override
	public List<ItemIngrediente> obtenerItemIngredientes(Item item, Integer pagina, Integer cantidad) {
		Pageable pageable = PageRequest.of(pagina, cantidad, Sort.by("id").descending());
		return itemIngredienteRepository.findByItem(item, pageable);
	}

	@Override
	public ItemIngrediente obtenerItemIngrediente(Integer idItemIngrediente) {
		return itemIngredienteRepository.findById(idItemIngrediente).get();
	}

	@Override
	public void save(ItemIngrediente itemIngrediente) {
		itemIngredienteRepository.save(itemIngrediente);
	}

	@Override
	public Long contarItemIngredientes() {
		return itemIngredienteRepository.count();
	}

}
