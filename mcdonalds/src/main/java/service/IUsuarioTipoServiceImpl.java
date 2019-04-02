package service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import antlr.StringUtils;
import model.UsuarioTipo;
import repository.UsuarioTipoRepository;

@Service
public class IUsuarioTipoServiceImpl implements IUsuarioTipoService{
	
	@Autowired
	private UsuarioTipoRepository usuarioTipoRepository;

	@Override
	public List<UsuarioTipo> obtenerUsuarioTipos() {
		return usuarioTipoRepository.findAll(Sort.by("id").ascending());
	}

	@Override
	public UsuarioTipo obtenerUsuarioTipo(Integer id) {
		return usuarioTipoRepository.findById(id).get();
	}
}
