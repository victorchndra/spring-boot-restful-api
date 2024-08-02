package victor_chandra.spring_restful_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import victor_chandra.spring_restful_api.entity.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, String> {
}
