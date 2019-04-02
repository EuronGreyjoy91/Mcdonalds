package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import model.Ingrediente;
import repository.IngredienteRepository;

@Service
public class IIngredienteServiceImpl implements IIngredienteService{
	
	@Autowired
	private IngredienteRepository ingredienteRepository;

	@Override
	public List<Ingrediente> obtenerIngredientes(Integer pagina, Integer cantidad, String nombre) {
		Pageable pageable = PageRequest.of(pagina, cantidad, Sort.by("id").descending());
		return ingredienteRepository.obtenerIngredientes(nombre, pageable);
	}

	@Override
	public Ingrediente obtenerIngrediente(Integer id) {
		return ingredienteRepository.findById(id).get();
	}

	@Override
	public void save(Ingrediente ingrediente) {
		ingredienteRepository.save(ingrediente);
	}

	@Override
	public Long contarIngredientes(String nombre) {
		return ingredienteRepository.contarIngredientes(nombre);
	}

	@Override
	public List<Ingrediente> obtenerTodosLosIngredientes() {
		return ingredienteRepository.findAll(Sort.by("id").ascending());
	}
}
