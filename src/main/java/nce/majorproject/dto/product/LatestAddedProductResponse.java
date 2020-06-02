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
}
