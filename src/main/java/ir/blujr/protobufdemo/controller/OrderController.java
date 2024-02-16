package ir.blujr.protobufdemo.controller;

import ir.blujr.protobufdemo.model.Order;
import ir.blujr.protobufdemo.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }


    @GetMapping()
    public ResponseEntity<List<Order>> getAllCustomers() {
        return ResponseEntity.status(HttpStatus.OK).body(orderService.getAllOrders());
    }

    /*@PostMapping(path ="/protobuf-create", consumes = "application/json",produces = "application/x-protobuf")
    public ResponseEntity<Message> createOrder(@RequestBody Order order) throws InvalidProtocolBufferException {

        // save new customer
        Order savedOrder = orderService.createOrder(order);

        // Convert saved Customer object to CustomerProto message
        String orderJson = savedOrder.toString();

        // CustomerProto.Customer responseCustomer = (CustomerProto.Customer)ProtobufUtil.fromJson(customerJson);
        Message responseOrder = ProtobufUtil.fromJson(orderJson);

        return ResponseEntity.status(HttpStatus.CREATED).body(responseOrder);
    }*/

    @PostMapping(path ="/json-create")
    public ResponseEntity<Order> createOrder(@RequestBody Order order) {

        // save new order
        Order savedOrder = orderService.createOrder(order);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedOrder);
    }
}
