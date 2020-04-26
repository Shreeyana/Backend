package nce.majorproject.services.impl;

import nce.majorproject.context.ContextHolderServices;
import nce.majorproject.entities.Product.SubCategory;
import nce.majorproject.exception.RestException;
import nce.majorproject.repositories.product.SubCategoryRepository;
import nce.majorproject.services.SubCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SubCategoryServiceImpl  implements SubCategoryService {
  private SubCategoryRepository subCategoryRepository;
  private ContextHolderServices contextHolderServices;

    @Autowired
    public SubCategoryServiceImpl(SubCategoryRepository subCategoryRepository, ContextHolderServices contextHolderServices) {
        this.subCategoryRepository = subCategoryRepository;
        this.contextHolderServices = contextHolderServices;
    }

    @Override
    public SubCategory validateSubCategoryById(Long id) {
        Optional<SubCategory> optionalSubCategory=subCategoryRepository.validateSubCategoryById(id);
        SubCategory subCategory=optionalSubCategory.orElseThrow(()->new RestException("invalid sub category!!"));
        return subCategory;
    }
}
