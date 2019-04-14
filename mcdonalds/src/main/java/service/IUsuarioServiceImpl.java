package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import model.Usuario;
import model.UsuarioTipo;
import repository.UsuarioRepository;
import repository.UsuarioTipoRepository;

@Service
public class IUsuarioServiceImpl implements IUsuarioService{

	@Autowired
	private UsuarioRepository usuarioRepository;	
	
	@Autowired
	private UsuarioTipoRepository usuarioTipoRepository;
	
	
	@Override
	public Usuario validarUsuario(String user, String password) {
		Usuario usuario = usuarioRepository.findByUsuarioAndContrasenia(user, password);
		return usuario;
	}
	
	@Override
	public void save(Usuario usuario) {
		usuarioRepository.save(usuario);
	}

	@Override
	public Usuario obtenerUsuario(String user) {
		return usuarioRepository.findByUsuario(user);
	}
	
	@Override
	public Usuario obtenerUsuario(Integer id) {
		return usuarioRepository.findById(id).get();
	}

	@Override
	public void reasignarUsuario(Integer id){
		Usuario usuario = usuarioRepository.findById(id).get();
		
		if(usuario.getUsuarioTipo().getId().equals(UsuarioTipo.USUARIO_COCINERO))
			usuario.setUsuarioTipo(usuarioTipoRepository.findById(UsuarioTipo.USUARIO_VENDEDOR).get());
		else
			usuario.setUsuarioTipo(usuarioTipoRepository.findById(UsuarioTipo.USUARIO_COCINERO).get());
		
		usuarioRepository.save(usuario);
	}

	@Override
	public List<Usuario> obtenerUsuarios(Specification<Usuario> usuarioSpecification, Pageable pageable) {
		return usuarioRepository.findAll(usuarioSpecification, pageable).getContent();
	}
	
	@Override
	public List<Usuario> obtenerUsuarios(Specification<Usuario> usuarioSpecification) {
		return usuarioRepository.findAll(usuarioSpecification);
	}

	@Override
	public Long contarUsuarios(Specification<Usuario> usuarioSpecification) {
		return usuarioRepository.count(usuarioSpecification);
	}
}
