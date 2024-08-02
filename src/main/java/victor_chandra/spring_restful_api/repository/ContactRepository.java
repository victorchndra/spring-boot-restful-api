package victor_chandra.spring_restful_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import victor_chandra.spring_restful_api.entity.Contact;

@Repository
public interface ContactRepository extends JpaRepository<Contact, String> {

}
