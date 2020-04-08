package guru.springfamework.services;

import guru.springfamework.api.v1.mapper.CustomerMapper;
import guru.springfamework.api.v1.model.CustomerDTO;
import guru.springfamework.domain.Customer;
import guru.springfamework.exceptions.ResourceNotFoundException;
import guru.springfamework.repositories.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    public CustomerServiceImpl(CustomerRepository customerRepository, CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }

    @Override
    public List<CustomerDTO> getAllCustomers() {
        List<CustomerDTO> customerDTOList = new ArrayList<>();
        customerRepository
                .findAll()
                .forEach(obj -> {
                    CustomerDTO objDTO = CustomerMapper.CUSTOMER_MAPPER.customerToCustomerDTO(obj);
                    objDTO.setUrl("/api/v1/customers/" + objDTO.getId());
                    customerDTOList.add(objDTO);
                });
        return customerDTOList;
    }

    @Override
    public CustomerDTO getCustomerById(Long id) {
        Optional<Customer> optCust = customerRepository.findById(id);
        if(!optCust.isPresent()){
            throw new ResourceNotFoundException();
        }
        CustomerDTO customerDTO = CustomerMapper.CUSTOMER_MAPPER.customerToCustomerDTO(optCust.get());
        customerDTO.setUrl("/api/v1/customers/" + customerDTO.getId());
        return customerDTO;
    }

    @Override
    public CustomerDTO createNewCustomer(CustomerDTO customerDTO){
        return saveCustomer(customerMapper.customerDTOToCustomer(customerDTO));
    }

    private CustomerDTO saveCustomer(Customer customer){
        Customer savedCustomer = customerRepository.save(customer);
        CustomerDTO savedCustomerDTO = customerMapper.customerToCustomerDTO(savedCustomer);
        return savedCustomerDTO.withUrl();
    }

    @Override
    public CustomerDTO saveCustomerByDTO(Long id, CustomerDTO customerDTO){
        Customer customer = customerMapper.customerDTOToCustomer(customerDTO);
        customer.setId(id);
        return saveCustomer(customer);
    }

    @Override
    public CustomerDTO patchCustomer(Long id, CustomerDTO customerDTO) {
        return customerRepository.findById(id).map(obj -> {
            if(customerDTO.getFirstname() != null){
                obj.setFirstname(customerDTO.getFirstname());
            }
            if(customerDTO.getLastname() != null){
                obj.setLastname(customerDTO.getLastname());
            }
            return customerMapper.customerToCustomerDTO(customerRepository.save(obj)).withUrl();
        }).orElse(null);
    }

    @Override
    public void deleteCustomerById(Long id) {
        customerRepository.deleteById(id);
    }
}
