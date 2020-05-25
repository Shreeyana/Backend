package nce.majorproject.repositories.product;

import nce.majorproject.entities.Product.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {

    @Query(value = "select p from Product p order by p.addedDate desc")
    List<Product> findLatestAddedProduct();
}
