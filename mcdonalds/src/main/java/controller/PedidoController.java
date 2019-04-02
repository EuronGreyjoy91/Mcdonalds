/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import model.Pedido;
import model.Usuario;
import service.IItemService;
import service.IPedidoService;
import service.IUsuarioService;

/**
 *
 * @author Federico
 */
@Controller
@RequestMapping("/pedidos")
public class PedidoController{
	
	@Autowired
	private IItemService itemService;
	
	@Autowired
	private IPedidoService pedidoService;
	
	@Autowired
	private IUsuarioService usuarioService;
	
	@GetMapping(value = "/listar/{pagina}")
	public String listarPedidos(Model model, @PathVariable Integer pagina, Authentication authentication) {
		
		model.addAttribute("pedidos", pedidosPorUsuario(authentication, pagina));
		model.addAttribute("pagina", pagina);
		model.addAttribute("paginas", ((pedidoService.contarPedidos() - 1) / 10));
		
		return "pedido/abmPedido";
	}
	
	public List<Pedido> pedidosPorUsuario(Authentication authentication, Integer pagina){
		List<Pedido> pedidos = new ArrayList<Pedido>();
		
		//OBTENEMOS EL ROL DEL USUARIO Y DEPENDIENDO EL MISMO MOSTRAMOS CIERTOS PEDIDOS
		for(GrantedAuthority rol : authentication.getAuthorities()){
			if(rol.getAuthority().equals("ADMINISTRADOR"))
				pedidos = pedidoService.obtenerPedidos(pagina, 10);
			else if(rol.getAuthority().equals("VENDEDOR"))
				pedidos = pedidoService.obtenerPedidosPorVendedor(usuarioService.obtenerUsuario(authentication.getName()), pagina, 10);
			else if(rol.getAuthority().equals("COCINERO"))
				pedidos = pedidoService.obtenerPedidosSinDespachar(pagina, 10);
		}
		
		return pedidos;
	}
	
	@GetMapping(value = "/nuevo")
	public String nuevoPedido(Model model) {
		model.addAttribute("items", itemService.obtenerTodosLosItems());
		return "pedido/pedidoForm";
	}
	
	@GetMapping(value = "/despachar/{id}")
	public String despacharPedido(@PathVariable Integer id, Authentication authentication, RedirectAttributes attributes) {
		Usuario cocinero = usuarioService.obtenerUsuario(authentication.getName());
		Pedido pedido = pedidoService.obtenerPedido(id);
		pedidoService.despacharPedido(cocinero, pedido);
		attributes.addFlashAttribute("response", "Pedido despachado con exito");
		return "redirect:/pedidos/listar/0";
	}
	
	@PostMapping(value = "/guardar")
	public String guardarPedido(String itemsPedido, BigDecimal montoPedido, RedirectAttributes attributes) {
		pedidoService.save(itemsPedido, montoPedido);
		attributes.addFlashAttribute("response", "Pedido guardado con exito");
		return "redirect:/pedidos/listar/0";
	}
	
	@GetMapping(value = "/redirectToListar")
	public String redirectToListar(RedirectAttributes attributes) {
		attributes.addFlashAttribute("response", "Pedido guardado con exito");
		return "redirect:/pedidos/listar/0";
	}
}
