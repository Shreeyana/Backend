package nce.majorproject.services;

import nce.majorproject.dto.Response;
import nce.majorproject.dto.product.SubCategoryRequest;
import nce.majorproject.entities.Product.SubCategory;

public interface SubCategoryService {

    SubCategory validateSubCategoryById(Long id);
    Response addSubCategory(SubCategoryRequest request);
}
