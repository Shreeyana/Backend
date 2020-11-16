package nce.majorproject.dto.product;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LatestAddedProductResponse {
    private Long id;
    private String name;
    private int quantity;
    private double price;
    private byte[] img;
    private String company;
    private String info;
    private String inCart;
    private String count;
    private String total;
    private String category;
    private String subSubCategory;
}
