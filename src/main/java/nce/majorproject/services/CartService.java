package nce.majorproject.services;



import nce.majorproject.dto.Response;
import nce.majorproject.dto.cart.CartAdd;
import nce.majorproject.dto.cart.CartRemove;
import nce.majorproject.dto.cart.CartRequest;
import nce.majorproject.dto.cart.ShowInCartById;
import nce.majorproject.dto.product.LatestAddedProductResponse;
import nce.majorproject.entities.Cart;

import java.util.List;

public interface CartService {

    List<ShowInCartById> showCart(CartRequest userId);

    Response addToCart(CartAdd addInCart);

    Response removeFromCart(CartRemove removeInCart);

    Response removeAllFromCart(CartRemove removeAllFromCart);

    List<ShowInCartById> findPopularProducts();
}
