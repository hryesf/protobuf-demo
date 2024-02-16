package ir.blujr.protobufdemo.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "tbl_product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private BigDecimal price;

    @ManyToMany(mappedBy = "products")
    private List<Order> orders;

    public Product(String name, BigDecimal price) {
        this.name = name;
        this.price = price;
    }
}
