package repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import model.ItemPedido;

@Repository
@Transactional
public interface ItemPedidoRepository extends JpaRepository<ItemPedido, Integer>{

	@Query("SELECT ip FROM ItemPedido ip WHERE ip.pedido.id = :pedido ORDER BY ip.id ASC")
	List<ItemPedido> findByPedido(@Param("pedido") Integer pedido);
	
}
