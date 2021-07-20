package ncit.majorproject.services;



import ncit.majorproject.dto.Response;
import ncit.majorproject.dto.cart.*;
import ncit.majorproject.dto.cart.CartAdd;
import ncit.majorproject.dto.cart.CartRemove;
import ncit.majorproject.dto.cart.ShowInCartById;
import ncit.majorproject.entities.Cart;

import java.util.List;

public interface CartService {

    List<ShowInCartById> showCart();

    Response addToCart(CartAdd addInCart);

    Response removeFromCart(CartRemove removeInCart);

    Response removeAllFromCart();

    List<ShowInCartById> findPopularProducts();

    Response checkOutAllCart();

    Response checkOutByCartId(Long cartId);

    List<Cart> listCheckout();
}
