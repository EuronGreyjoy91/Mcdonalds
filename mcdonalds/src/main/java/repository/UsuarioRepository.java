package repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import model.Usuario;
import model.UsuarioTipo;

@Repository
@Transactional
public interface UsuarioRepository extends JpaRepository<Usuario, Integer>, JpaSpecificationExecutor<Usuario>{
	Usuario findByUsuarioAndContrasenia(String usuario, String contrasenia);
	Optional<Usuario> findById(Integer id);
	Usuario findByUsuario(String usuario);
	List<Usuario> findByUsuarioTipo(UsuarioTipo usuarioTipo);
}
