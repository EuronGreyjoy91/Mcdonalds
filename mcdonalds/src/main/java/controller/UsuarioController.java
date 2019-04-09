/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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

import model.Usuario;
import service.IUsuarioService;
import service.IUsuarioTipoService;

/**
 *
 * @author Federico
 */
@Controller
@RequestMapping("/usuarios")
public class UsuarioController{
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private IUsuarioService usuarioService;
	
	@Autowired
	private IUsuarioTipoService usuarioTipoService;
	
	@GetMapping(value = "/listar/{pagina}")
	public String listarUsuarios(Model model, @PathVariable Integer pagina,
			@RequestParam (required = false, defaultValue = "") String nombre, 
			@RequestParam (required = false, defaultValue = "") String apellido,
			@RequestParam (required = false, defaultValue = "") String documento,
			@RequestParam (required = false) Integer usuarioTipo) {
		
		model.addAttribute("usuarios", usuarioService.obtenerUsuarios(pagina, 10, nombre, apellido, documento, usuarioTipo));
		model.addAttribute("pagina", pagina);
		model.addAttribute("paginas", ((usuarioService.contarUsuarios(nombre, apellido, documento, usuarioTipo) - 1) / 10));
		model.addAttribute("usuarioTipos", usuarioTipoService.obtenerUsuarioTipos());
		
		//Filtros
		model.addAttribute("nombre", nombre);
		model.addAttribute("apellido", apellido);
		model.addAttribute("documento", documento);
		model.addAttribute("usuarioTipoSeleccionado", usuarioTipo);
		
		return "usuario/abmUsuario";
	}
	
	@GetMapping(value = "/nuevo")
	public String nuevoUsuario(@ModelAttribute Usuario usuario, Model model) {
		model.addAttribute("usuarioTipos", usuarioTipoService.obtenerUsuarioTipos());
		return "usuario/usuarioForm";
	}
	
	@PostMapping(value = "/guardar")
	public String guardarUsuario(@ModelAttribute Usuario usuario, BindingResult result, RedirectAttributes attributes) {
		
		if(usuario.getContrasenia() != null && !usuario.getContrasenia().equals("")){
			String passwordTextoPlano = usuario.getContrasenia();
			String nuevaPassword = passwordEncoder.encode(passwordTextoPlano);
			
			usuario.setContrasenia(nuevaPassword);
		}else
			usuario.setContrasenia(usuarioService.obtenerUsuario(usuario.getId()).getContrasenia());
		
		usuarioService.save(usuario);
		attributes.addFlashAttribute("response", "Usuario guardado con exito");
		return "redirect:/usuarios/listar/0";
	}
	
	@GetMapping(value = "/editar/{id}")
	public String editarUsuario(@PathVariable Integer id, Model model){
		model.addAttribute("usuario", usuarioService.obtenerUsuario(id));
		model.addAttribute("usuarioTipos", usuarioTipoService.obtenerUsuarioTipos());
		return "usuario/usuarioForm";
	}
	
	@GetMapping(value = "/reasignar/{id}")
	public String reasignarUsuario(@PathVariable Integer id, RedirectAttributes attributes) {
		usuarioService.reasignarUsuario(id);
		attributes.addFlashAttribute("response", "Usuario reasignado con exito");
		return "redirect:/usuarios/listar/0";
	}
	
}
