package service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

import model.ItemPedido;
import model.ItemPedidoHelper;
import model.Pedido;
import model.Usuario;
import repository.ItemRepository;
import repository.PedidoRepository;

@Service
public class IPedidoServiceImpl implements IPedidoService{
	
	@Autowired
	private ItemRepository itemRepository;

	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Override
	public List<Pedido> obtenerPedidos(Specification<Pedido> pedidoSpecification, Pageable pageable) {
		return pedidoRepository.findAll(pedidoSpecification, pageable).getContent();
	}

	@Override
	public void save(String items, BigDecimal monto, Usuario vendedor) {
		Pedido pedido = new Pedido();
		pedido.setFechaIngreso(new Date());
		pedido.setMonto(monto);
		pedido.setVendedor(vendedor);
		
		ItemPedidoHelper[] itemsPedido = new Gson().fromJson(items, ItemPedidoHelper[].class);
		
		Set<ItemPedido> setItemsPedido = new HashSet<ItemPedido>();
		ItemPedido itemPedido;
		
		for(ItemPedidoHelper itemPedidoHelper : itemsPedido) {
			itemPedido = new ItemPedido();
			itemPedido.setCantidad(Integer.valueOf(itemPedidoHelper.getCantidad()));
			itemPedido.setItem(itemRepository.findById(Integer.valueOf(itemPedidoHelper.getItem())).get());
			itemPedido.setMonto(itemPedidoHelper.getMonto());
			itemPedido.setPedido(pedido);
			setItemsPedido.add(itemPedido);
		}
		
		pedido.setItemsPedido(setItemsPedido);
		
		pedidoRepository.save(pedido);
	}

	@Override
	public Long contarPedidos(Specification<Pedido> pedidoSpecification) {
		return pedidoRepository.count(pedidoSpecification);
	}

	@Override
	public void despacharPedido(Usuario cocinero, Pedido pedido) {
		pedido.setCocinero(cocinero);
		pedido.setFechaDespacho(new Date());
		pedidoRepository.save(pedido);
	}

	@Override
	public Pedido obtenerPedido(Integer id) {
		return pedidoRepository.findById(id).get();
	}
}
