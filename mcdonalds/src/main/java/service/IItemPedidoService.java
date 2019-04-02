package service;

import java.util.List;

import model.ItemPedido;

public interface IItemPedidoService {
	List<ItemPedido> obtenerItemsPedido(Integer idPedido);
}
