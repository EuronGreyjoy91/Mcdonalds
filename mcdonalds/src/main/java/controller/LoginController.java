/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import model.Utilities;

/**
 *
 * @author Federico
 */

@Controller
public class LoginController{
	
	@GetMapping(value = "/")
	public String defaultLogin() {
		return "formLogin";
	}
	
	@GetMapping(value = "/formLogin")
	public String formLogin(@RequestParam(value = "error", required = false) String error, Model model){
		
		if(error != null)
			model.addAttribute("errorMessage", Utilities.MENSAJE_ERROR_LOGUEO);
		
		return "formLogin";
	}
	
	@GetMapping(value = "/login/redirectTo")
	public String redirectTo(Authentication authentication) {
		String redirect = "formLogin";
		
		for(GrantedAuthority rol : authentication.getAuthorities()){
			if(rol.getAuthority().equals("ADMINISTRADOR"))
				redirect = "usuarios/listar/0";
			else if(rol.getAuthority().equals("VENDEDOR") || rol.getAuthority().equals("COCINERO"))
				redirect = "pedidos/listar/0";
		}
		
		return "redirect:/" + redirect;
	}
	
	@GetMapping(value = "/logout")
	public String logout(HttpServletRequest request) {
		
		SecurityContextLogoutHandler logoutHandler = new SecurityContextLogoutHandler();
		logoutHandler.logout(request, null, null);
		
		return "redirect:/formLogin";
	}
}
