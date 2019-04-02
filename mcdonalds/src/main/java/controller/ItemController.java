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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import model.Item;
import service.IItemService;

/**
 *
 * @author Federico
 */

@Controller
@RequestMapping("/items")
public class ItemController{

	@Autowired
	private IItemService itemService;

    @GetMapping(value = "/listar/{pagina}")
    public String listarItems(Model model, @PathVariable Integer pagina) {
    	model.addAttribute("items", itemService.obtenerItems(pagina, 10));
		model.addAttribute("pagina", pagina);
		model.addAttribute("paginas", ((itemService.contarItems() - 1) / 10));
    	return "item/abmItem";
    }
    	
    @GetMapping(value = "/nuevo")
    public String nuevoItem(@ModelAttribute Item item) {
    	return "item/itemForm";
    }
    
    @GetMapping(value = "/editar/{id}")
    public String editarItem(@PathVariable Integer id, Model model) {
    	model.addAttribute("item", itemService.obtenerItem(id));
    	return "item/itemForm";
    }
    
    @PostMapping(value = "/guardar")
    public String guardarItem(@ModelAttribute Item item, BindingResult results, RedirectAttributes attributes) {
    	itemService.save(item);
    	attributes.addFlashAttribute("response", "Item guardado con exito");
    	return "redirect:/items/listar/0";
    }
}