package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import model.ItemIngrediente;
import model.Utilities;
import service.IIngredienteService;
import service.IItemIngredienteService;
import service.IItemService;

/**
 *
 * @author Federico
 */
@Controller
@RequestMapping("/itemIngredientes")
public class ItemIngredienteController{
	
	@Autowired
	private IIngredienteService ingredienteService;

	@Autowired
	private IItemService itemService;
	
	@Autowired
	private IItemIngredienteService itemIngredienteService;
	
	@GetMapping(value = "/item/{id}/listar/{pagina}")
	public String listarItemIngredientes(@PathVariable Integer id, Model model, @PathVariable Integer pagina) {
		model.addAttribute("item", itemService.obtenerItem(id));
		model.addAttribute("itemIngredientes", itemIngredienteService.obtenerItemIngredientes(itemService.obtenerItem(id), pagina, Utilities.REGISTROS_POR_PAGINA));
		model.addAttribute("pagina", pagina);
		model.addAttribute("paginas", ((itemIngredienteService.contarItemIngredientes() - 1) / Utilities.REGISTROS_POR_PAGINA));
		return "itemIngrediente/abmItemIngrediente";
	}
	
	@GetMapping(value = "/item/{idItem}/nuevo")
	public String nuevoItemIngrediente(@PathVariable Integer idItem, @ModelAttribute ItemIngrediente itemIngrediente, Model model) {
		model.addAttribute("idItem", idItem);
		model.addAttribute("ingredientes", ingredienteService.obtenerTodosLosIngredientes());
		return "itemIngrediente/itemIngredienteForm";
	}
   
	@GetMapping(value = "/itemIngrediente/{id}/editar")
	public String editarItemIngrediente(@PathVariable Integer id, Model model) {
		model.addAttribute("itemIngrediente", itemIngredienteService.obtenerItemIngrediente(id));
		model.addAttribute("ingredientes", ingredienteService.obtenerTodosLosIngredientes());
		model.addAttribute("idItem", itemIngredienteService.obtenerItemIngrediente(id).getItem().getId());
		return "itemIngrediente/itemIngredienteForm";
	}
	
	@PostMapping(value = "/guardar")
	public String guardarItemIngrediente(@ModelAttribute ItemIngrediente itemIngrediente, RedirectAttributes attributes) {
		
		if(itemIngrediente.getId() == null)
			itemIngrediente.setActivo(1);
		
		itemIngredienteService.save(itemIngrediente);
		attributes.addFlashAttribute("item", itemService.obtenerItem(itemIngrediente.getItem().getId()));
		attributes.addFlashAttribute("response", "Item ingrediente guardado con exito");
		return "redirect:/itemIngredientes/item/"+itemIngrediente.getItem().getId()+"/listar/0";
	}
}
