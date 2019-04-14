package service;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import model.Usuario;

public interface IUsuarioService {
	Usuario validarUsuario(String user, String password);
	Usuario obtenerUsuario(String user);
	Usuario obtenerUsuario(Integer id);
	List<Usuario> obtenerUsuarios(Specification<Usuario> usuarioSpecification, Pageable pageable);
	List<Usuario> obtenerUsuarios(Specification<Usuario> usuarioSpecification);
	Long contarUsuarios(Specification<Usuario> usuarioSpecification);
	void save(Usuario usuario);
	void reasignarUsuario(Integer id);
}
