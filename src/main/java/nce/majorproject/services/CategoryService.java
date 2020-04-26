package nce.majorproject.services;

import nce.majorproject.entities.Product.Category;

public interface CategoryService {
    Category validateCategoryId(Long id);
}
