package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import service.IItemPedidoService;

@Controller
@RequestMapping(value = "/itemsPedido")
public class ItemPedidoController {
	
	@Autowired
	private IItemPedidoService itemPedidoService;
	
	@GetMapping(value = "/listar/{id}")
	public String listarItemsPedido(@PathVariable Integer id, Model model) {
		model.addAttribute("itemsPedido", itemPedidoService.obtenerItemsPedido(id));
		model.addAttribute("idPedido", id);
		return "itemPedido/abmItemPedido";
	}
	
}
