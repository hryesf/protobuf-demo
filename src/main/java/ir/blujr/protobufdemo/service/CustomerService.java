package ir.blujr.protobufdemo.service;

import ir.blujr.protobufdemo.model.Customer;
import ir.blujr.protobufdemo.repository.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CustomerService {

    private static final Logger log = LoggerFactory.getLogger(CustomerService.class);
    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<Customer> getAllCustomers() {
        log.info("Retrieving all customers");
        List<Customer> customers = customerRepository.findAll();
        log.info("Retrieved {} customers", customers.size());
        return customers;
    }

    public Customer createCustomer(Customer customer) {
        log.info("Creating customer: {}", customer);
        Customer createdCustomer = customerRepository.save(customer);
        log.info("Customer created successfully with ID: {}", createdCustomer.getId());
        return createdCustomer;
    }

}
