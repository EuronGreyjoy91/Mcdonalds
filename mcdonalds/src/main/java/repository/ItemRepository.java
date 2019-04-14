package repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import model.Item;

@Repository
@Transactional
public interface ItemRepository extends JpaRepository<Item, Integer>, JpaSpecificationExecutor<Item>{

}
