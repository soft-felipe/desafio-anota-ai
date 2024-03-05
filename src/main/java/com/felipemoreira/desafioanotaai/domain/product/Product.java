package com.felipemoreira.desafioanotaai.domain.product;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.json.JSONObject;
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
    private String categoryId;

    public Product(ProductDTO data) {
        this.title = data.title();
        this.description = data.description();
        this.ownerId = data.ownerId();
        this.price = data.price();
        this.categoryId = data.categoryId();
    }

    @Override
    public String toString() {
        JSONObject json = new JSONObject();
        json.put("id", this.id);
        json.put("title", this.title);
        json.put("description", this.description);
        json.put("ownerId", this.ownerId);
        json.put("price", this.price);
        json.put("categoryId", this.categoryId);
        json.put("type", "product");
        return json.toString();
    }
}
