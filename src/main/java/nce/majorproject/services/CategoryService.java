package nce.majorproject.services;

import nce.majorproject.dto.Response;
import nce.majorproject.dto.product.CategoryRequest;
import nce.majorproject.entities.Product.Category;

public interface CategoryService {
    Category validateCategoryId(Long id);
    Response addCategory(CategoryRequest request);
}
