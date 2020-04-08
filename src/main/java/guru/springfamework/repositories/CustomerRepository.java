package guru.springfamework.repositories;

import guru.springfamework.domain.Customer;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/**
 * Created by jt on 9/24/17.
 */

//public interface CustomerRepository extends JpaRepository<Customer, Long> {
public interface CustomerRepository extends CrudRepository<Customer, Long> {
    Optional<Customer> findById(Long id);
    Optional<Customer> findByFirstname(String name);
}
