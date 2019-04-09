package service;

import java.util.List;

import model.Usuario;

public interface IUsuarioService {
	Usuario validarUsuario(String user, String password);
	Usuario obtenerUsuario(String user);
	Usuario obtenerUsuario(Integer id);
	List<Usuario> obtenerUsuarios(Integer pagina, Integer cantidad, String nombre, String apellido, String documento, Integer usuarioTipo);
	List<Usuario> obtenerUsuariosVendedor();
	List<Usuario> obtenerUsuariosCocinero();
	Long contarUsuarios(String nombre, String apellido, String documento, Integer usuarioTipo);
	void save(Usuario usuario);
	void reasignarUsuario(Integer id);
}
