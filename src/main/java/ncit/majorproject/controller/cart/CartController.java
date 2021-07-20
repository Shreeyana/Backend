package ncit.majorproject.controller.cart;


import lombok.extern.slf4j.Slf4j;
import ncit.majorproject.constant.Route;
import ncit.majorproject.dto.Response;
import ncit.majorproject.dto.cart.*;
import ncit.majorproject.dto.cart.CartAdd;
import ncit.majorproject.dto.cart.CartRemove;
import ncit.majorproject.dto.cart.ShowInCartById;
import ncit.majorproject.entities.Cart;
import ncit.majorproject.repositories.CartRepository;
import ncit.majorproject.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(Route.CART)
@Slf4j
public class CartController {
    private CartRepository cartRepository;
    private CartService cartService;
    @Autowired
    public CartController(CartRepository cartRepository,CartService cartService){
        this.cartRepository=cartRepository;
        this.cartService=cartService;
    }
    @GetMapping(value="/show")
    public List<ShowInCartById> showCart(){

        return cartService.showCart();
    }
    @PostMapping(value="/remove")
    public Response removeCart(@RequestBody CartRemove cartRemove){

        return cartService.removeFromCart(cartRemove);
    }
    @PostMapping(value="/add")
    public Response addToCart(@RequestBody CartAdd addInCart){

        return cartService.addToCart(addInCart);
    }
    @GetMapping(value="/remove-all")
    public Response removeAllFromCart(){

        return cartService.removeAllFromCart();
    }

    @GetMapping("/checkout-all")
    public Response checkOutFromCart(){

        log.info("Checkout from the cart");

        return cartService.checkOutAllCart();
    }
    @GetMapping("/checkout-by-id/{id}")
    public Response checkOutByCartId(@PathVariable("id")Long id){

        log.info("checkout this cart id::");

        return  cartService.checkOutByCartId(id);
    }
    @GetMapping("/checkout-list")
    public List<Cart> allCheckedOutList(){
        return  cartService.listCheckout();
    }
    @GetMapping
    public List<ShowInCartById> findPopularItem(){

        log.info("Showing most popular items");

        return cartService.findPopularProducts();
    }

}

