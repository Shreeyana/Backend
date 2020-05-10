package nce.majorproject.services;



import nce.majorproject.dto.cart.showInCartById;
import nce.majorproject.entities.Cart;
import nce.majorproject.entities.Product.Product;

import java.util.List;

public interface CartService {

    List<showInCartById> showCart(String user_id);
    public List<showInCartById> mergeCartandProduct(List<Cart> cart, List<Product> product);
}
