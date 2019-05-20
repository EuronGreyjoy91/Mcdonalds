/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import model.Ingrediente;
import model.IngredienteSpecification;
import model.Utilities;
import service.IIngredienteService;

/**
 *
 * @author Federico
 */
@Controller
@RequestMapping(value = "/ingredientes")
public class IngredienteController{
	
	@Autowired
	private IIngredienteService ingredienteService;
	
	@GetMapping(value = "/listar/{pagina}")
	public String listarIngredientes(@ModelAttribute Ingrediente ingrediente, Model model, @PathVariable Integer pagina) {
		
		Specification<Ingrediente> spec = new IngredienteSpecification(ingrediente);
		Pageable pageable = PageRequest.of(pagina, Utilities.REGISTROS_POR_PAGINA, Sort.by("id").descending());
		
		model.addAttribute("ingredientes", ingredienteService.obtenerIngredientes(spec, pageable));
		model.addAttribute("pagina", pagina);
		model.addAttribute("paginas", ((ingredienteService.contarIngredientes(spec) - 1) / Utilities.REGISTROS_POR_PAGINA));
		
		return "ingrediente/abmIngrediente";
	}
	
	@GetMapping(value = "/nuevo")
	public String nuevoIngrediente(@ModelAttribute Ingrediente ingrediente) {
		return "ingrediente/ingredienteForm";
	}
	
	@GetMapping(value = "/editar/{id}")
	public String editarIngrediente(@PathVariable Integer id, Model model) {
		model.addAttribute("ingrediente", ingredienteService.obtenerIngrediente(id));
		return "ingrediente/ingredienteForm";
	}
	
	@PostMapping(value = "/guardar")
	public String guardarIngrediente(@ModelAttribute Ingrediente ingrediente, BindingResult results,
			RedirectAttributes attributes) {
		
		if(ingrediente.getId() == null)
			ingrediente.setActivo(1);
		else
			ingrediente.setActivo(ingredienteService.obtenerIngrediente(ingrediente.getId()).getActivo());
			
		ingredienteService.save(ingrediente);
		attributes.addFlashAttribute("response", "Ingrediente guardado con exito");
		return "redirect:/ingredientes/listar/0";
	}
	
	@GetMapping(value = "/desactivar/{id}")
	public String desactivarIngrediente(@PathVariable Integer id, RedirectAttributes attributes) {
		ingredienteService.cambiarEstadoIngrediente(id, 0);
		attributes.addFlashAttribute("response", "Ingrediente desactivado con exito");
		return "redirect:/ingredientes/listar/0";
	}
	
	@GetMapping(value = "/activar/{id}")
	public String activarIngrediente(@PathVariable Integer id, RedirectAttributes attributes) {
		ingredienteService.cambiarEstadoIngrediente(id, 1);
		attributes.addFlashAttribute("response", "Ingrediente activado con exito");
		return "redirect:/ingredientes/listar/0";
	}
}
