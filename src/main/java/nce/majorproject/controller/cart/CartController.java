package nce.majorproject.controller.cart;


import lombok.extern.slf4j.Slf4j;
import nce.majorproject.constant.Route;
import nce.majorproject.dto.Response;
import nce.majorproject.dto.cart.CartAdd;
import nce.majorproject.dto.cart.CartRemove;
import nce.majorproject.dto.cart.CartRequest;
import nce.majorproject.dto.cart.ShowInCartById;
import nce.majorproject.repositories.CartRepository;
import nce.majorproject.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    @PostMapping(value="/show")
    public List<ShowInCartById> showCart(@Valid @RequestBody CartRequest reqUserId){

        return cartService.showCart(reqUserId);
    }
    @PostMapping(value="/remove")
    public Response removeCart(@RequestBody CartRemove cartRemove){
        return cartService.removeFromCart(cartRemove);
    }
    @PostMapping(value="/add")
    public Response addToCart(@RequestBody CartAdd addInCart){
        return cartService.addToCart(addInCart);
    }
}

