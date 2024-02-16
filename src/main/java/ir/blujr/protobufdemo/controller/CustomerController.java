package ir.blujr.protobufdemo.controller;

import com.google.gson.Gson;
import com.google.protobuf.InvalidProtocolBufferException;
import ir.blujr.protobufdemo.common.protobuf.customer.CustomerProto;
import ir.blujr.protobufdemo.model.Customer;
import ir.blujr.protobufdemo.service.CustomerService;
import ir.blujr.protobufdemo.util.ProtobufUtil;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/customers")
public class CustomerController {

    private final CustomerService customerService;


    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping()
    public ResponseEntity<List<Customer>> getAllCustomers() {
        final HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        return ResponseEntity.status(HttpStatus.OK).headers(httpHeaders).body(customerService.getAllCustomers());
    }

    @GetMapping(path = "/protobuf-customer-list", produces = "application/x-protobuf")
    public ResponseEntity<CustomerProto.Customers> getAllCustomersProtobuf() {

        final HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_PROTOBUF);

        List<Customer> customers = customerService.getAllCustomers();

        CustomerProto.Customers.Builder customersBuilder = CustomerProto.Customers.newBuilder();
        for (Customer customer : customers) {
            CustomerProto.Customer.Builder customerBuilder = CustomerProto.Customer.newBuilder()
                    .setId(customer.getId())
                    .setName(customer.getName())
                    .setEmail(customer.getEmail());
            customersBuilder.addCustomer(customerBuilder);
        }

        return ResponseEntity.status(HttpStatus.OK).headers(httpHeaders).body(customersBuilder.build());
    }

    @PostMapping(path = "/protobuf-create", consumes = "application/x-protobuf", produces = "application/x-protobuf")
    public ResponseEntity<CustomerProto.Customer> createCustomerProtobuf(@RequestBody CustomerProto.Customer customerProto) {

        final HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_PROTOBUF);

        // Convert CustomerProto message to Customer object
        String customerJson;
        try {
            customerJson = ProtobufUtil.toJson(customerProto);
        } catch (InvalidProtocolBufferException e) {
            throw new RuntimeException(e);
        }

        // convert Json to object with Gson
        Gson gson = new Gson();
        Customer customer = gson.fromJson(customerJson, Customer.class);

        // save new customer
        Customer savedCustomer = customerService.createCustomer(customer);

        // Convert saved Customer object to CustomerProto message
        CustomerProto.Customer responseCustomer = CustomerProto.Customer.newBuilder()
                .setId(savedCustomer.getId())
                .setName(savedCustomer.getName())
                .setEmail(savedCustomer.getEmail())
                .build();


        return ResponseEntity.status(HttpStatus.CREATED).headers(httpHeaders).body(responseCustomer);

    }


    @PostMapping(path = "/create", consumes = "application/json", produces = "application/x-protobuf")
    public ResponseEntity<CustomerProto.Customer> createCustomer(@RequestBody Customer customer) {

        final HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_PROTOBUF);
        // save new customer
        Customer savedCustomer = customerService.createCustomer(customer);

        // Convert saved Customer object to CustomerProto message
        CustomerProto.Customer responseCustomer = CustomerProto.Customer.newBuilder()
                .setId(savedCustomer.getId())
                .setName(savedCustomer.getName())
                .setEmail(savedCustomer.getEmail())
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).headers(httpHeaders).body(responseCustomer);
    }

}
