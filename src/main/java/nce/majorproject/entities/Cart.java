package nce.majorproject.entities;

import javax.persistence.*;
import java.time.LocalDateTime;
@Table(name="Cart")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "added_date")
    private LocalDateTime addedDate;
    @Column(name="product_id")
    private Long productId;
    @Column(name = "quantity")
    private int quantity;
    @Column(name="user_id")
    private String userId;
    @Column(name="isCheckout")
    private boolean isCheckout;
    @Column(name="isRemoved")
    private boolean isRemoved;
}
