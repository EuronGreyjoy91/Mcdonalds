package service;

import java.math.BigDecimal;
import java.util.List;

import model.Pedido;
import model.Usuario;

public interface IPedidoService {
	Pedido obtenerPedido(Integer id);
	List<Pedido> obtenerPedidos(Integer pagina, Integer cantidad);
	List<Pedido> obtenerPedidosPorVendedor(Usuario vendedor, Integer pagina, Integer cantidad);
	List<Pedido> obtenerPedidosSinDespachar(Integer pagina, Integer cantidad);
	Long contarPedidos();
	void despacharPedido(Usuario cocinero, Pedido pedido);
	void save(String items, BigDecimal monto);
}
