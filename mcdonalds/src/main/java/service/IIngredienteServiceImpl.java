package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import model.Ingrediente;
import repository.IngredienteRepository;

@Service
public class IIngredienteServiceImpl implements IIngredienteService{
	
	@Autowired
	private IngredienteRepository ingredienteRepository;

	@Override
	public List<Ingrediente> obtenerIngredientes(Specification<Ingrediente> ingredienteSpecification, Pageable pageable) {
		return ingredienteRepository.findAll(ingredienteSpecification, pageable).getContent();
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
	public Long contarIngredientes(Specification<Ingrediente> ingredienteSpecification) {
		return ingredienteRepository.count(ingredienteSpecification);
	}

	@Override
	public List<Ingrediente> obtenerTodosLosIngredientes() {
		return ingredienteRepository.findAll(Sort.by("id").ascending());
	}
}
