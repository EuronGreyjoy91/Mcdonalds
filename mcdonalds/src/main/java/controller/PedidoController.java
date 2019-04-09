/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import model.Pedido;
import model.PedidoSpecification;
import model.Usuario;
import repository.PedidoRepository;
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
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@GetMapping(value = "/listar/{pagina}")
	public String listarPedidos(@ModelAttribute Pedido pedido, BindingResult bindingResult, Model model, 
			@PathVariable Integer pagina, Authentication authentication) {
		
		Specification<Pedido> spec = new PedidoSpecification(pedido);
		
		Pageable pageable = PageRequest.of(pagina, 10, Sort.by("id").descending());
//	    pedidoRepository.findAll(spec, pageable);
		
		model.addAttribute("pedidos", pedidoRepository.findAll(spec, pageable).getContent());
//		model.addAttribute("pedidos", pedidoService.searchPedidos(searchCriteria));
		model.addAttribute("pagina", pagina);
		model.addAttribute("paginas", ((pedidoService.contarPedidos() - 1) / 10));
		model.addAttribute("vendedores", usuarioService.obtenerUsuariosVendedor());
		model.addAttribute("cocineros", usuarioService.obtenerUsuariosCocinero());
		
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
