package project.projectucvwebsystem.dto;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductDTO {
    
    private Long id_product;
    private String alias;
    private String name_large;
    private int quantity;
    private double unit_price;

}