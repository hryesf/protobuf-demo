package ir.blujr.protobufdemo.service;

import ir.blujr.protobufdemo.model.Customer;
import ir.blujr.protobufdemo.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerService customerService;

    /*@BeforeEach
    void setUp() {
        customerService = new CustomerService(customerRepository);
    }*/

    @Test
    public void testGetAllCustomers() {

        // given
        List<Customer> expectedCustomers = new ArrayList<>();
        expectedCustomers.add(new Customer(1l, "sara", "sara@gmail.com"));
        expectedCustomers.add(new Customer(2l, "Mina", "Mina@gmail.com"));

        // when
        when(customerRepository.findAll()).thenReturn(expectedCustomers);

        // then
        List<Customer> actualCustomers = customerService.getAllCustomers();

        assertEquals(expectedCustomers, actualCustomers);
        verify(customerRepository).findAll();
    }

    @Test
    public void testCreateCustomer() {
        Customer customerToCreate = new Customer(1l, "sara", "sara@gmail.com");

        when(customerRepository.save((any(Customer.class)))).thenReturn(customerToCreate);

        Customer createdCustomer = customerService.createCustomer(customerToCreate);

        assertNotNull(createdCustomer);
        assertEquals("sara", createdCustomer.getName());
        verify(customerRepository).save(customerToCreate);
    }
}