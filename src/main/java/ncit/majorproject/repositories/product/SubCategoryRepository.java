package ncit.majorproject.repositories.product;

import ncit.majorproject.entities.Product.SubCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SubCategoryRepository extends JpaRepository<SubCategory,Long> {

    @Query(value = "select sc from SubCategory sc where sc.id=?1")
    Optional<SubCategory> validateSubCategoryById(Long id);
}
