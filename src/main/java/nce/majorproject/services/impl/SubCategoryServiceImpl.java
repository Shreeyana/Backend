package nce.majorproject.services.impl;

import nce.majorproject.context.ContextHolderServices;
import nce.majorproject.dto.Response;
import nce.majorproject.dto.product.SubCategoryRequest;
import nce.majorproject.entities.Product.Category;
import nce.majorproject.entities.Product.SubCategory;
import nce.majorproject.exception.RestException;
import nce.majorproject.repositories.product.SubCategoryRepository;
import nce.majorproject.services.CategoryService;
import nce.majorproject.services.SubCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class SubCategoryServiceImpl  implements SubCategoryService {
  private SubCategoryRepository subCategoryRepository;
  private ContextHolderServices contextHolderServices;
  private CategoryService categoryService;

    @Autowired
    public SubCategoryServiceImpl(SubCategoryRepository subCategoryRepository,CategoryService categoryService,
                                  ContextHolderServices contextHolderServices) {
        this.subCategoryRepository = subCategoryRepository;
        this.contextHolderServices = contextHolderServices;
        this.categoryService=categoryService;
    }

    @Override
    public SubCategory validateSubCategoryById(Long id) {
        Optional<SubCategory> optionalSubCategory=subCategoryRepository.validateSubCategoryById(id);
        SubCategory subCategory=optionalSubCategory.orElseThrow(()->new RestException("invalid sub category!!"));
        return subCategory;
    }

    @Override
    public Response addSubCategory(SubCategoryRequest request) {
        SubCategory subCategory=prepareSubCategoryAddData(request);
        SubCategory response=subCategoryRepository.save(subCategory);
        return Response.builder().id(response.getId()).build();
    }
    private SubCategory prepareSubCategoryAddData(SubCategoryRequest request){
        SubCategory subCategory=new SubCategory();
        Category category=categoryService.validateCategoryId(request.getCategoryId());
        subCategory.setAddedBy(contextHolderServices.getContext().getFullName());
        subCategory.setAddedDate(LocalDateTime.now());
        subCategory.setName(request.getName());
        subCategory.setCategory(category);
        return subCategory;
    }
}
