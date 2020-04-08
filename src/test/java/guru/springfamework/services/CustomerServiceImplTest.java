package guru.springfamework.services;

import guru.springfamework.api.v1.mapper.CustomerMapper;
import guru.springfamework.api.v1.model.CustomerDTO;
import guru.springfamework.bootstrap.Bootstrap;
import guru.springfamework.domain.Customer;
import guru.springfamework.repositories.CategoryRepository;
import guru.springfamework.repositories.CustomerRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;


//IntegrationTest
@RunWith(SpringRunner.class)
@DataJpaTest
public class CustomerServiceImplTest {
    public static final String TEST_NAME_0 = "Jan";
    public static final String TEST_NAME_1 = "TEST_NAME_1";
    public static Long ID_1;
    public static final Long ID_EMPTY = 999L;
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    CategoryRepository categoryRepository;

    CustomerService sut;

    @Before
    public void setUp() throws Exception{
        Bootstrap bootstrap = new Bootstrap(categoryRepository, customerRepository);
        bootstrap.run();
        sut = new CustomerServiceImpl(customerRepository, CustomerMapper.CUSTOMER_MAPPER);
        ID_1 = customerRepository.findByFirstname(TEST_NAME_0).get().getId();
    }

    @Test
    public void patchCustomerShouldUpdateOnlyFirstname(){
        CustomerDTO dtoToPatch = new CustomerDTO();
        dtoToPatch.setFirstname(TEST_NAME_1);
        Customer customerBeforePatch = customerRepository.findById(ID_1).get();

        sut.patchCustomer(ID_1, dtoToPatch);

        Customer updatedCustomer = customerRepository.findById(ID_1).get();
        assertEquals(updatedCustomer.getFirstname(), dtoToPatch.getFirstname());
        assertEquals(updatedCustomer.getLastname(), customerBeforePatch.getLastname());
    }

    @Test
    public void patchCustomerShouldUpdateOnlyLastname(){
        CustomerDTO dtoToPatch = new CustomerDTO();
        dtoToPatch.setLastname(TEST_NAME_1);
        Customer customerBeforePatch = customerRepository.findById(ID_1).get();

        sut.patchCustomer(ID_1, dtoToPatch);

        Customer updatedCustomer = customerRepository.findById(ID_1).get();
        assertEquals(updatedCustomer.getLastname(), dtoToPatch.getLastname());
        assertEquals(updatedCustomer.getFirstname(), customerBeforePatch.getFirstname());
    }

    @Test
    public void patchCustomerShouldReturnNullIfNoCustomerWithId(){
        CustomerDTO dtoToPatch = new CustomerDTO();
        assertEquals(sut.patchCustomer(ID_EMPTY, dtoToPatch), null);
    }
}