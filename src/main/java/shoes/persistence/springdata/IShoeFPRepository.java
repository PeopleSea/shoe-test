package shoes.persistence.springdata;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import shoes.model.ShoeFP;

@Repository
public interface IShoeFPRepository extends JpaRepository<ShoeFP, Long>{
}
