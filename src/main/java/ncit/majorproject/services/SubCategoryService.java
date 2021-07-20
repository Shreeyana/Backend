package ncit.majorproject.services;

import ncit.majorproject.dto.Response;
import ncit.majorproject.dto.product.SubCategoryRequest;
import ncit.majorproject.dto.product.SubCategorySubRequest;
import ncit.majorproject.entities.Product.SubCategory;
import ncit.majorproject.entities.Product.SubSubCategory;

import java.util.List;

public interface SubCategoryService {

    Boolean validateSubSUbCategoryByName(String value);

    SubCategory validateSubCategoryById(Long id);
    Response addSubCategory(SubCategoryRequest request);
    List<SubCategory> listSubCategory();
    Response addSubCategorySubType(SubCategorySubRequest request);

    List<SubSubCategory> listSubSubCategory();

    SubSubCategory validateSubSubCategoryId(Long id);
}
