package ncit.majorproject.services;

import ncit.majorproject.dto.Response;
import ncit.majorproject.dto.product.CategoryRequest;
import ncit.majorproject.entities.Product.Category;

import java.util.List;

public interface CategoryService {
    Category validateCategoryId(Long id);
    Response addCategory(CategoryRequest request);
    List<Category> listCategory();
}
