package nce.majorproject.dto.cart;

import lombok.Getter;
import lombok.Setter;
import nce.majorproject.entities.Product.Category;
import nce.majorproject.entities.Product.SubCategory;

import java.time.LocalDateTime;

@Getter
@Setter
public class showInCartById {
    private long product_id;
    private LocalDateTime addedDate;
    private int quantity;
    private String productName;
    private double price;
    private Category category;
    private SubCategory subCategory;
    private byte[] photo;
}
