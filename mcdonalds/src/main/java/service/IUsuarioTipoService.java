package service;

import java.util.List;

import model.UsuarioTipo;

public interface IUsuarioTipoService {
	List<UsuarioTipo> obtenerUsuarioTipos();
	UsuarioTipo obtenerUsuarioTipo(Integer id);
}
