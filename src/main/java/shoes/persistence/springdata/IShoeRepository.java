package shoes.persistence.springdata;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import shoes.model.Shoe;

@Repository
public interface IShoeRepository extends JpaRepository<Shoe, Long>{
	List<Shoe> findById(String id);
}
