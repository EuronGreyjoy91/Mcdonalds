package model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

public class IngredienteSpecification implements Specification<Ingrediente>{

	private static final long serialVersionUID = 1L;
	private final Ingrediente criteria;

    public IngredienteSpecification(Ingrediente criteria) {
        this.criteria = criteria;
    }
    
	@Override
	public Predicate toPredicate(Root<Ingrediente> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		
	 	List<Predicate> predicates = new ArrayList<>();
        
        if(criteria.getNombre() != null && !criteria.getNombre().equals(""))
        	predicates.add(cb.like(root.get("nombre"), "%" + criteria.getNombre() + "%"));
        
        if(criteria.getActivo() != null)
        	predicates.add(cb.equal(root.get("activo"), criteria.getActivo()));
        
        return cb.and(predicates.toArray(new Predicate[predicates.size()]));
	}
}
