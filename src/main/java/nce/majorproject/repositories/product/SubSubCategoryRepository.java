package nce.majorproject.repositories.product;

import nce.majorproject.entities.Product.SubSubCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubSubCategoryRepository extends JpaRepository<SubSubCategory,Long> {
}
