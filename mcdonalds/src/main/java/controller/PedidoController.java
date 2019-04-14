/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import model.Pedido;
import model.PedidoSpecification;
import model.Usuario;
import model.UsuarioSpecification;
import model.UsuarioTipo;
import model.Utilities;
import service.IItemService;
import service.IPedidoService;
import service.IUsuarioService;
import service.IUsuarioTipoService;

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
	
	@Autowired
	private IUsuarioTipoService usuarioTipoService;
	
	@GetMapping(value = "/listar/{pagina}")
	public String listarPedidos(@ModelAttribute Pedido pedido, BindingResult bindingResult, Model model, 
			@PathVariable Integer pagina, Authentication authentication) {
		
		pedido.setListarPedidosSinDespachar(false);
		
		//OBTENEMOS EL ROL DEL USUARIO Y DEPENDIENDO EL MISMO MOSTRAMOS CIERTOS PEDIDOS
		for(GrantedAuthority rol : authentication.getAuthorities()){
			if(rol.getAuthority().equals("VENDEDOR"))
				pedido.setVendedor(usuarioService.obtenerUsuario(authentication.getName()));
			else if(rol.getAuthority().equals("COCINERO"))
				pedido.setListarPedidosSinDespachar(true);
		}
		
		Specification<Pedido> spec = new PedidoSpecification(pedido);
		Pageable pageable = PageRequest.of(pagina, Utilities.REGISTROS_POR_PAGINA, Sort.by("id").descending());
		
		model.addAttribute("pedidos", pedidoService.obtenerPedidos(spec, pageable));
		model.addAttribute("pagina", pagina);
		model.addAttribute("paginas", ((pedidoService.contarPedidos(spec)) - 1) / Utilities.REGISTROS_POR_PAGINA);
		
		Usuario usuario = new Usuario();
		usuario.setUsuarioTipo(usuarioTipoService.obtenerUsuarioTipo(UsuarioTipo.USUARIO_VENDEDOR));
		Specification<Usuario> usuarioSpec = new UsuarioSpecification(usuario);
		model.addAttribute("vendedores", usuarioService.obtenerUsuarios(usuarioSpec));
		
		usuario.setUsuarioTipo(usuarioTipoService.obtenerUsuarioTipo(UsuarioTipo.USUARIO_COCINERO));
		usuarioSpec = new UsuarioSpecification(usuario);
		model.addAttribute("cocineros", usuarioService.obtenerUsuarios(usuarioSpec));
		
		return "pedido/abmPedido";
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
