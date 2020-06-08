package shoes.persistence.springdata;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import shoes.model.ShoeBox;

@Repository
public interface IShoeBoxRepository extends JpaRepository<ShoeBox, Long>{
}
