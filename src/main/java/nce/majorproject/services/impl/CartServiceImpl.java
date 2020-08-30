package nce.majorproject.services.impl;

import nce.majorproject.dto.Response;
import nce.majorproject.dto.cart.CartAdd;
import nce.majorproject.dto.cart.CartRemove;
import nce.majorproject.dto.cart.CartRequest;
import nce.majorproject.dto.cart.ShowInCartById;
import nce.majorproject.dto.product.LatestAddedProductResponse;
import nce.majorproject.entities.Cart;
import nce.majorproject.entities.Product.Product;
import nce.majorproject.entities.User;
import nce.majorproject.repositories.CartRepository;
import nce.majorproject.services.CartService;
import nce.majorproject.util.ImageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartServiceImpl implements CartService {
    private CartRepository cartRepository;
    private UserServiceImpl userService;
    private ProductServiceImpl productService;
    @Autowired
    public CartServiceImpl(CartRepository cartRepository,
                           ProductServiceImpl productService,
                           UserServiceImpl userService){
        this.cartRepository=cartRepository;
        this.userService=userService;
        this.productService=productService;
    }
    @Override
    public List<ShowInCartById> showCart(CartRequest cartShow) {
        User user=userService.validateUser(cartShow.getUserid());//validation
        List<Cart> getCart=cartRepository.findCartById(user);
        List<ShowInCartById> response=new ArrayList<>();
        getCart.forEach(itemInCart ->{
                    ShowInCartById showInCartResponse=setToShowInCart(itemInCart);
                    response.add(showInCartResponse);
                }
        );
        return response;
    }

    private ShowInCartById setToShowInCart(Cart cart) {
        ShowInCartById request=new ShowInCartById();
        request.setCartid(cart.getId());
        request.setQuantity(cart.getQuantity());
        request.setPrice(cart.getProductId().getPrice());
        request.setCategory(cart.getProductId().getCategory().getName());
        request.setAddedDate(cart.getAddedDate());
        request.setPhoto(ImageUtil.decompressBytes(cart.getProductId().getPhoto()));
        request.setSubCategory(cart.getProductId().getSubCategory().getName());
        request.setProduct_id(cart.getProductId().getId());
        request.setProductName(cart.getProductId().getProductName());
        return request;
    }

    @Override
    public Response addToCart(CartAdd addInCart) {
        Cart addCart=addDataInCart(addInCart);
        Cart response=cartRepository.save(addCart);
        return Response.builder().id(response.getId()).build();
    }

    private Cart addDataInCart(CartAdd cart) {
        Cart response=new Cart();
        Product product=productService.validateProduct(cart.getProduct_id());

        User user=userService.validateUser(cart.getUser_id());
        response.setAddedDate(cart.getAddedDate());
        response.setModifiedDate(cart.getAddedDate());
        response.setCheckout(false);
        response.setRemoved(false);
        response.setQuantity(cart.getQuantity());
        response.setProductId(product);
        response.setUserId(user);
        return response;
    }

    @Override
    public Response removeFromCart(CartRemove removeInCart) {
        User user=userService.validateUser(removeInCart.getUserid());//validate
        cartRepository.removeFromCartDB(user,removeInCart.getCartid());
        return Response.builder().id(removeInCart.getCartid()).build();// remove
    }

    @Override
    public Response removeAllFromCart(CartRemove removeAllCartData) {
     User user = userService.validateUser(removeAllCartData.getUserid());
     cartRepository.removeAllFromCartDB(user);
     return Response.builder().id(removeAllCartData.getUserid()).build();
    }

    @Override
    public List<ShowInCartById> findPopularProducts() {
        List<Cart> cartList=cartRepository.findPopular();
        List<ShowInCartById> response=new ArrayList<>();
        cartList.forEach(itemInCart ->{
                    ShowInCartById showInCartResponse=setToShowInCart(itemInCart);
                    response.add(showInCartResponse);
                }
        );
        return response;
    }


}
