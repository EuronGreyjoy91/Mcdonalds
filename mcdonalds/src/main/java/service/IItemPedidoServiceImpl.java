package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import model.ItemPedido;
import repository.ItemPedidoRepository;

@Service
public class IItemPedidoServiceImpl implements IItemPedidoService{

	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
	@Override
	public List<ItemPedido> obtenerItemsPedido(Integer idPedido) {
		return itemPedidoRepository.findByPedido(idPedido);
	}

}
