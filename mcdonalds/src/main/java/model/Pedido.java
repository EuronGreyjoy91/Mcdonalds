/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.format.annotation.DateTimeFormat;


/**
 *
 * @author Federico
 */

@Entity
@Table(name = "pedido")
public class Pedido {
    
    private Integer id;
    private BigDecimal monto;
    
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date fechaIngreso;
    
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date fechaDespacho;
    
    private Usuario vendedor;
    private Usuario cocinero;
    private Set<ItemPedido> itemsPedido;
    
    @Transient
    private Boolean listarPedidosSinDespachar;
    
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "monto")
    public BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }

    @Column(name = "fecha_ingreso")
    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    @Column(name = "fecha_despacho")
    public Date getFechaDespacho() {
        return fechaDespacho;
    }

    public void setFechaDespacho(Date fechaDespacho) {
        this.fechaDespacho = fechaDespacho;
    }

    @ManyToOne
    @JoinColumn(name = "id_usuario_vendedor")
    public Usuario getVendedor() {
        return vendedor;
    }

    public void setVendedor(Usuario vendedor) {
        this.vendedor = vendedor;
    }

    @ManyToOne
    @JoinColumn(name = "id_usuario_cocinero")
    public Usuario getCocinero() {
        return cocinero;
    }

    public void setCocinero(Usuario cocinero) {
        this.cocinero = cocinero;
    }

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
	public Set<ItemPedido> getItemsPedido() {
		return itemsPedido;
	}

	public void setItemsPedido(Set<ItemPedido> itemsPedido) {
		this.itemsPedido = itemsPedido;
	}

	@Transient
	public Boolean getListarPedidosSinDespachar() {
		return listarPedidosSinDespachar;
	}

	public void setListarPedidosSinDespachar(Boolean listarPedidosSinDespachar) {
		this.listarPedidosSinDespachar = listarPedidosSinDespachar;
	}
}
