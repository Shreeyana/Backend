package nce.majorproject.dto.cart;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class cartAdd {
    private long product_id;
    private int quantity;
    private boolean isCheckout;
    private boolean isRemoved;
    private String userId;
}
