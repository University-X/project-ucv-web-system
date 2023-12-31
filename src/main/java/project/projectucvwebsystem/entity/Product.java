package project.projectucvwebsystem.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "PRODUCT")
@Getter
@Setter
public class Product {
    @Id
    private Long id_product;
    private Long id_category;
    private String name_large;
    private int quantity;
    private double unit_price;
}
