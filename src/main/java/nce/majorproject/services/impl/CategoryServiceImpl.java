package nce.majorproject.services.impl;

import nce.majorproject.context.ContextHolderServices;
import nce.majorproject.entities.Product.Category;
import nce.majorproject.exception.RestException;
import nce.majorproject.repositories.product.CategoryRepository;
import nce.majorproject.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class CategoryServiceImpl implements CategoryService {
    private CategoryRepository categoryRepository;
    private ContextHolderServices contextHolderServices;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository, ContextHolderServices contextHolderServices) {
        this.categoryRepository = categoryRepository;
        this.contextHolderServices = contextHolderServices;
    }


    @Override
    public Category validateCategoryId(Long id) {
        Optional<Category> optionalCategory=categoryRepository.validateById(id);
        Category category=optionalCategory.orElseThrow(()->new RestException("invalid product id"));
        return category;
    }
}
