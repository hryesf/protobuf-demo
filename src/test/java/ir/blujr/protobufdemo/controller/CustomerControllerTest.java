package ir.blujr.protobufdemo.controller;

import ir.blujr.protobufdemo.common.protobuf.customer.CustomerProto;
import ir.blujr.protobufdemo.model.Customer;
import ir.blujr.protobufdemo.service.CustomerService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CustomerControllerTest {

    @Mock
    private CustomerService customerService;

    @InjectMocks
    private CustomerController customerController;

    @Test
    public void testGetAllCustomers() {
        // given
        List<Customer> customers = new ArrayList<>();
        customers.add(new Customer(1L, "sara", "sara@gmail.com"));

        // when
        when(customerService.getAllCustomers()).thenReturn(customers);
        ResponseEntity<List<Customer>> responseEntity = customerController.getAllCustomers();

        // then
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(MediaType.APPLICATION_JSON, responseEntity.getHeaders().getContentType());
        assertEquals(customers, responseEntity.getBody());

        verify(customerService).getAllCustomers();
    }

    @Test
    public void testGetAllCustomersProtoBuf() {
        // given
        List<Customer> customers = Arrays.asList(
                new Customer(1L, "sara", "sara@gmail.com"),
                new Customer(2L, "mina", "mina@gmail.com")
        );

        // when
        when(customerService.getAllCustomers()).thenReturn(customers);
        ResponseEntity<CustomerProto.Customers> responseEntity = customerController.getAllCustomersProtobuf();


        // then
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(MediaType.APPLICATION_PROTOBUF, responseEntity.getHeaders().getContentType());

        CustomerProto.Customers protobufCustomers = responseEntity.getBody();
        assertNotNull(protobufCustomers);

        // Assert individual customers
        List<CustomerProto.Customer> protobufCustomerList = protobufCustomers.getCustomerList();
        assertEquals(customers.size(), protobufCustomerList.size());

        // Assert the content of each customer
        for (int i = 0; i < customers.size(); i++) {
            CustomerProto.Customer protobufCustomer = protobufCustomerList.get(i);
            Customer customer = customers.get(i);

            assertEquals(customer.getId(), protobufCustomer.getId());
            assertEquals(customer.getName(), protobufCustomer.getName());
            assertEquals(customer.getEmail(), protobufCustomer.getEmail());
        }


        verify(customerService).getAllCustomers();
    }

    @Test
    public void createCustomer_success() {
        // given
        Customer customer = new Customer(1L, "sara", "sara@gmail.com");
        // Message customerProto = ProtobufUtil.fromJson(customer.toString());
        CustomerProto.Customer customerProto = CustomerProto.Customer.newBuilder()
                .setId(1L)
                .setName("sara")
                .setEmail("sara@gmail.com")
                .build();

        // when
        when(customerService.createCustomer(any())).thenReturn(customer);
        ResponseEntity<CustomerProto.Customer> responseEntity = customerController.createCustomer(customer);

        // then
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(MediaType.APPLICATION_PROTOBUF, responseEntity.getHeaders().getContentType());
        assertEquals(customerProto, responseEntity.getBody());

        verify(customerService).createCustomer(customer);

    }

    @Test
    public void createCustomerProtobuf_success() {
        // given
        Customer customer = new Customer(1L, "sara", "sara@gmail.com");
        CustomerProto.Customer customerProto = CustomerProto.Customer.newBuilder()
                .setId(1L)
                .setName("sara")
                .setEmail("sara@gmail.com")
                .build();

        // when
        when(customerService.createCustomer(any())).thenReturn(customer);
        ResponseEntity<CustomerProto.Customer> responseEntity = customerController.createCustomerProtobuf(customerProto);

        // then
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(MediaType.APPLICATION_PROTOBUF, responseEntity.getHeaders().getContentType());
        assertEquals(customerProto, responseEntity.getBody());

        verify(customerService).createCustomer(customer);
    }
}