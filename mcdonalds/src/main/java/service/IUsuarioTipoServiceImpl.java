package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import model.UsuarioTipo;
import repository.UsuarioTipoRepository;

@Service
public class IUsuarioTipoServiceImpl implements IUsuarioTipoService{
	
	@Autowired
	private UsuarioTipoRepository usuarioTipoRepository;

	@Override
	public List<UsuarioTipo> obtenerUsuarioTipos() {
		return usuarioTipoRepository.findByActivoOrderByIdAsc(1);
	}

	@Override
	public UsuarioTipo obtenerUsuarioTipo(Integer id) {
		return usuarioTipoRepository.findById(id).get();
	}
}
