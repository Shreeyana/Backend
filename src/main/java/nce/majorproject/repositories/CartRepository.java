package nce.majorproject.repositories;

import nce.majorproject.entities.Cart;
import nce.majorproject.entities.Product.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart,Long>{
        @Query(value = "select product_id,quantity,added_date from Cart where user_id=?1 and isCheckout=false and isRemoved=false")
        List<Cart> findCartById(String user_id);

        @Query(value = "select * from products  where product_id=?1")
       List<Product> fetchInfoByProductId();
    }
