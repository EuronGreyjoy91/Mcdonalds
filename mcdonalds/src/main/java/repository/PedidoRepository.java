package repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import model.Pedido;

@Repository
@Transactional
public interface PedidoRepository extends JpaRepository<Pedido, Integer>, JpaSpecificationExecutor<Pedido>{

}
