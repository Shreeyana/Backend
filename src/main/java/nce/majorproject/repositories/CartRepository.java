package nce.majorproject.repositories;

import nce.majorproject.entities.Cart;
import nce.majorproject.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart,Long>{

        @Query(value = "select c from Cart c where c.userId=?1 and c.isRemoved=false and c.isCheckout=false ")
        List<Cart> findCartById(User userId);

        @Modifying
        @Transactional
        @Query(value="update Cart set isRemoved=true where userId=?1 and id=?2")
        void removeFromCartDB(User userId, Long id);
    }