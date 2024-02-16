package ir.blujr.protobufdemo.controller;

import ir.blujr.protobufdemo.model.Product;
import ir.blujr.protobufdemo.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping()
    public ResponseEntity<List<Product>> getAllCustomers() {
        return ResponseEntity.status(HttpStatus.OK).body(productService.getAllProducts());
    }

    @PostMapping(path ="/create-products")
    String saveDoctor() {
        productService.createProduct(new Product("backpack", new BigDecimal("1000000")));
        productService.createProduct(new Product("purse", new BigDecimal("500000")));
        productService.createProduct(new Product("wallet", new BigDecimal("300000")));
        return "Products saved!";
    }
}