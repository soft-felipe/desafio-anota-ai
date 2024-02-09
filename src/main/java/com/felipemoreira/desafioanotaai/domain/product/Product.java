package com.felipemoreira.desafioanotaai.domain.product;

import com.felipemoreira.desafioanotaai.domain.category.Category;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "products")
@Getter @Setter
@NoArgsConstructor
public class Product {

    @Id private String id;
    private String title;
    private String description;
    private String ownerId;
    private Integer price; /* Salvando sem vírgulas, armezando o número multiplicado por 100 e extraindo dividindo por 100 */
    private Category category;

    public Product(ProductDTO data) {
        this.title = data.title();
        this.description = data.description();
        this.ownerId = data.ownerId();
        this.price = data.price();
    }
}
