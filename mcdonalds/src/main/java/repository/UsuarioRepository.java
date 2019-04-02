package repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import model.Usuario;

@Repository
@Transactional
public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{
	Usuario findByUsuarioAndContrasenia(String usuario, String contrasenia);
	Optional<Usuario> findById(Integer id);
	Usuario findByUsuario(String usuario);
	
	@Query("SELECT u FROM Usuario u WHERE u.nombre LIKE CONCAT('%',:nombre,'%') "
			+ "AND u.apellido LIKE CONCAT('%',:apellido,'%') "
			+ "AND u.documento LIKE CONCAT('%',:documento,'%') "
			+ "ORDER BY id ASC")
	List<Usuario> obtenerUsuarios(@Param("nombre") String nombre, @Param("apellido") String apellido,
			@Param("documento") String documento, Pageable pageable);
	
	@Query("SELECT COUNT(u) FROM Usuario u WHERE u.nombre LIKE CONCAT('%',:nombre,'%') "
			+ "AND u.apellido LIKE CONCAT('%',:apellido,'%') "
			+ "AND u.documento LIKE CONCAT('%',:documento,'%')")
	Long contarUsuarios(@Param("nombre") String nombre, @Param("apellido") String apellido,
			@Param("documento") String documento);
	
	@Query("SELECT u FROM Usuario u WHERE u.nombre LIKE CONCAT('%',:nombre,'%') "
			+ "AND u.apellido LIKE CONCAT('%',:apellido,'%') "
			+ "AND u.documento LIKE CONCAT('%',:documento,'%') "
			+ "AND u.usuarioTipo.id = :usuarioTipo "
			+ "ORDER BY id ASC")
	List<Usuario> obtenerUsuarios(@Param("nombre") String nombre, @Param("apellido") String apellido,
			@Param("documento") String documento, @Param("usuarioTipo") Integer usuarioTipo, Pageable pageable);
	
	@Query("SELECT COUNT(u) FROM Usuario u WHERE u.nombre LIKE CONCAT('%',:nombre,'%') "
			+ "AND u.apellido LIKE CONCAT('%',:apellido,'%') "
			+ "AND u.documento LIKE CONCAT('%',:documento,'%') "
			+ "AND u.usuarioTipo.id = :usuarioTipo")
	Long contarUsuarios(@Param("nombre") String nombre, @Param("apellido") String apellido,
			@Param("documento") String documento, @Param("usuarioTipo") Integer usuarioTipo);
}
