package org.example.springframework.service;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.example.springframework.dto.CustomerDTO;
import org.example.springframework.entity.Customer;
import org.example.springframework.exception.CustomerNotFoundException;
import org.example.springframework.repository.CustomerRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CustomerServiceImpl implements CustomerService {
    CustomerRepository repo;

    public List<CustomerDTO> getAllCustomers() {
        List<Customer> customerList = repo.findAll();
        return customerList.stream().map(this::convertToCustomerDTO).toList();
    }

    public CustomerDTO getCustomerById(Long id) {
        Customer customer = repo.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found with id: " + id, HttpStatus.NOT_FOUND));

        return convertToCustomerDTO(customer);
    }

    public CustomerDTO addCustomer(CustomerDTO customerDTO) {
        Customer customer = new Customer();
        customer.setName(customerDTO.getName());
        customer.setPhoneNumber(customerDTO.getPhoneNumber());
        Customer savedCustomer = repo.save(customer);
        return convertToCustomerDTO(savedCustomer);
    }

    public CustomerDTO updateCustomer(Long id, CustomerDTO customerDTO) {
        CustomerDTO existingCustomerDTO = getCustomerById(id);
        if (existingCustomerDTO == null) {
            throw new CustomerNotFoundException("Customer not found with id: " + id, HttpStatus.NOT_FOUND);
        }

        existingCustomerDTO.setName(customerDTO.getName());
        existingCustomerDTO.setPhoneNumber(customerDTO.getPhoneNumber());

        Customer updatedCustomer = repo.save(convertToCustomerEntity(existingCustomerDTO));

        return convertToCustomerDTO(updatedCustomer);
    }

    public void deleteCustomer(Long id) {
        repo.deleteById(id);
    }

    private CustomerDTO convertToCustomerDTO(Customer customer) {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setId(customer.getId());
        customerDTO.setName(customer.getName());
        customerDTO.setPhoneNumber(customer.getPhoneNumber());
        return customerDTO;
    }

    private Customer convertToCustomerEntity(CustomerDTO customerDTO) {
        Customer customer = new Customer();
        customer.setId(customerDTO.getId());
        customer.setName(customerDTO.getName());
        customer.setPhoneNumber(customerDTO.getPhoneNumber());
        return customer;
    }
}