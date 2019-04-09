package repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import model.Pedido;
import model.Usuario;

@Repository
@Transactional
public interface PedidoRepository extends JpaRepository<Pedido, Integer>,  JpaSpecificationExecutor<Pedido>{
	List<Pedido> findPedidoByVendedor(Usuario vendedor, Pageable pageable);
	List<Pedido> findPedidoByCocineroIsNull(Pageable pageable);
}
