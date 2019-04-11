package model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

public class PedidoSpecification implements Specification<Pedido>{

	private static final long serialVersionUID = 1L;
	private final Pedido criteria;

    public PedidoSpecification(Pedido criteria) {
        this.criteria = criteria;
    }
    
	@Override
	public Predicate toPredicate(Root<Pedido> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		
		Calendar start;
		Calendar end;
		Predicate startPredicate;
		Predicate endPredicate;
		
	 	List<Predicate> predicates = new ArrayList<>();
	 	
        if(criteria.getCocinero() != null && criteria.getCocinero().getId() != null){
            Join<Pedido, Usuario> cocinero = root.join("cocinero");
            predicates.add(cb.equal(cocinero.get("id"), criteria.getCocinero().getId()));
        }
        
        if(criteria.getVendedor() != null && criteria.getVendedor().getId() != null){
            Join<Pedido, Usuario> vendedor = root.join("vendedor");
            predicates.add(cb.equal(vendedor.get("id"), criteria.getVendedor().getId()));
        }
        
        if(criteria.getFechaIngreso() != null){
        	start = Calendar.getInstance();
        	start.setTime(criteria.getFechaIngreso());
        	start.set(Calendar.HOUR_OF_DAY, 0);
        	start.set(Calendar.MINUTE, 0);
        	start.set(Calendar.SECOND, 0);
        	
        	end = Calendar.getInstance();
        	end.setTime(criteria.getFechaIngreso());
        	end.set(Calendar.HOUR_OF_DAY, 23);
        	end.set(Calendar.MINUTE, 59);
        	end.set(Calendar.SECOND, 59);
        	
        	startPredicate = cb.greaterThanOrEqualTo(root.get("fechaIngreso"), start.getTime());
        	endPredicate = cb.lessThanOrEqualTo(root.get("fechaIngreso"), end.getTime());

        	predicates.add(startPredicate);
        	predicates.add(endPredicate);
        }
        
        if(criteria.getFechaDespacho() != null){
        	start = Calendar.getInstance();
        	start.setTime(criteria.getFechaDespacho());
        	start.set(Calendar.HOUR_OF_DAY, 0);
        	start.set(Calendar.MINUTE, 0);
        	start.set(Calendar.SECOND, 0);
        	
        	end = Calendar.getInstance();
        	end.setTime(criteria.getFechaDespacho());
        	end.set(Calendar.HOUR_OF_DAY, 23);
        	end.set(Calendar.MINUTE, 59);
        	end.set(Calendar.SECOND, 59);
        	
        	startPredicate = cb.greaterThanOrEqualTo(root.get("fechaDespacho"), start.getTime());
        	endPredicate = cb.lessThanOrEqualTo(root.get("fechaDespacho"), end.getTime());

        	predicates.add(startPredicate);
        	predicates.add(endPredicate);
        }
        
        if(criteria.getListarPedidosSinDespachar())
        	predicates.add(cb.isNull(root.get("fechaDespacho")));
        
        return cb.and(predicates.toArray(new Predicate[predicates.size()]));
	}
}
