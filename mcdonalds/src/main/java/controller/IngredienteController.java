/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import model.Ingrediente;
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
	public String listarIngredientes(Model model, @PathVariable Integer pagina,
			@RequestParam (required = false, defaultValue = "") String nombre) {
		
		model.addAttribute("ingredientes", ingredienteService.obtenerIngredientes(pagina, 10, nombre));
		model.addAttribute("pagina", pagina);
		model.addAttribute("paginas", ((ingredienteService.contarIngredientes(nombre) - 1) / 10));
		
		//Filtros
		model.addAttribute("nombre", nombre);
		
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
		ingredienteService.save(ingrediente);
		attributes.addFlashAttribute("response", "Ingrediente guardado con exito");
		return "redirect:/ingredientes/listar/0";
	}
}
