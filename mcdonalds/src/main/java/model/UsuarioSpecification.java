package model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

public class UsuarioSpecification implements Specification<Usuario>{

	private static final long serialVersionUID = 1L;
	private final Usuario criteria;

    public UsuarioSpecification(Usuario criteria) {
        this.criteria = criteria;
    }
    
	@Override
	public Predicate toPredicate(Root<Usuario> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		
	 	List<Predicate> predicates = new ArrayList<>();
	 	Join<Usuario, UsuarioTipo> join;
	 	
        if(criteria.getUsuarioTipo() != null && criteria.getUsuarioTipo().getId() != null){
            join = root.join("usuarioTipo");
            predicates.add(cb.equal(join.get("id"), criteria.getUsuarioTipo().getId()));
        }
        
        if(criteria.getNombre() != null && !criteria.getNombre().equals(""))
        	predicates.add(cb.like(root.get("nombre"), "%" + criteria.getNombre() + "%"));
        
        if(criteria.getApellido() != null && !criteria.getApellido().equals(""))
        	predicates.add(cb.like(root.get("apellido"), "%" + criteria.getApellido() + "%"));
        
        if(criteria.getDocumento() != null && !criteria.getDocumento().equals(""))
        	predicates.add(cb.like(root.get("documento"), "%" + criteria.getDocumento() + "%"));
        
        if(criteria.getActivo() != null)
        	predicates.add(cb.equal(root.get("activo"), criteria.getActivo()));
        
        return cb.and(predicates.toArray(new Predicate[predicates.size()]));
	}
}
