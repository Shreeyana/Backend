package nce.majorproject.services.impl;

import nce.majorproject.dto.cart.showInCartById;
import nce.majorproject.entities.Cart;
import nce.majorproject.entities.Product.Product;
import nce.majorproject.repositories.CartRepository;
import nce.majorproject.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class CartServiceImpl implements CartService {
    private CartRepository cartRepository;
    @Autowired
    public CartServiceImpl(CartRepository cartRepository){
        this.cartRepository=cartRepository;
    }
    @Override
    public List<showInCartById> showCart(String user_id) {
        List<Cart> getCart=cartRepository.findCartById(user_id);
        List<Product> getProductInfoInCart=cartRepository.fetchInfoByProductId();
        List<showInCartById> mergeRepos= mergeCartandProduct(getCart,getProductInfoInCart);
        return null;
    }

    @Override
    public List<showInCartById> mergeCartandProduct(List<Cart> cart, List<Product> product) {
        List<showInCartById> merge=new ArrayList<>();
        showInCartById retParameters=new showInCartById();
        //retParameters.setAddedDate();
        return merge;
    }

}
