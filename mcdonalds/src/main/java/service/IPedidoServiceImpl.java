package service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

import model.ItemPedido;
import model.ItemPedidoHelper;
import model.Pedido;
import model.Usuario;
import repository.ItemRepository;
import repository.PedidoRepository;
import repository.UsuarioRepository;

@Service
public class IPedidoServiceImpl implements IPedidoService{
	
	@Autowired
	private ItemRepository itemRepository;

	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired 
	private UsuarioRepository usuarioRepository;	
	
	@Override
	public List<Pedido> obtenerPedidos(Integer pagina, Integer cantidad) {
		return pedidoRepository.findAll(PageRequest.of(pagina, cantidad, Sort.by("id").descending())).getContent();
	}

	@Override
	public void save(String items, BigDecimal monto) {
		Pedido pedido = new Pedido();
		pedido.setFechaIngreso(new Timestamp(new Date().getTime()));
		pedido.setMonto(monto);
		pedido.setVendedor(usuarioRepository.findById(2).get());
		
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
	public List<Pedido> obtenerPedidosPorVendedor(Usuario vendedor, Integer pagina, Integer cantidad) {
		Pageable pageable = PageRequest.of(pagina, cantidad, Sort.by("id").descending());
		return pedidoRepository.findPedidoByVendedor(vendedor, pageable);
	}

	@Override
	public Long contarPedidos() {
		return pedidoRepository.count();
	}

	@Override
	public List<Pedido> obtenerPedidosSinDespachar(Integer pagina, Integer cantidad) {
		Pageable pageable = PageRequest.of(pagina, cantidad, Sort.by("id").descending());
		return pedidoRepository.findPedidoByCocineroIsNull(pageable);
	}

	@Override
	public void despacharPedido(Usuario cocinero, Pedido pedido) {
		pedido.setCocinero(cocinero);
		pedidoRepository.save(pedido);
	}

	@Override
	public Pedido obtenerPedido(Integer id) {
		return pedidoRepository.findById(id).get();
	}
}
