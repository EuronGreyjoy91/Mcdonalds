package service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import model.Pedido;
import model.Usuario;

public interface IPedidoService {
	Pedido obtenerPedido(Integer id);
	List<Pedido> obtenerPedidos(Specification<Pedido> pedidoSpecification, Pageable pageable);
	Long contarPedidos(Specification<Pedido> pedidoSpecification);
	void despacharPedido(Usuario cocinero, Pedido pedido);
	void save(String items, BigDecimal monto, Usuario vendedor);
}
