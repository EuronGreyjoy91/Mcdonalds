package repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import model.UsuarioTipo;

@Repository
@Transactional
public interface UsuarioTipoRepository extends JpaRepository<UsuarioTipo, Integer>, JpaSpecificationExecutor<UsuarioTipo>{
	List<UsuarioTipo> findByActivoOrderByIdAsc(Integer activo);
}
