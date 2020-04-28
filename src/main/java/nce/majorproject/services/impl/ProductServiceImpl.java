package nce.majorproject.services.impl;

import nce.majorproject.context.ContextHolderServices;
import nce.majorproject.dto.Response;
import nce.majorproject.dto.product.AddRequest;
import nce.majorproject.entities.Product.Category;
import nce.majorproject.entities.Product.Product;
import nce.majorproject.entities.Product.SubCategory;
import nce.majorproject.repositories.product.CategoryRepository;
import nce.majorproject.repositories.product.ProductRepository;
import nce.majorproject.repositories.product.SubCategoryRepository;
import nce.majorproject.services.CategoryService;
import nce.majorproject.services.ProductService;
import nce.majorproject.services.SubCategoryService;
import nce.majorproject.util.ImageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ProductServiceImpl  implements ProductService {
    private ProductRepository productRepository;
    private CategoryService categoryService;
    private SubCategoryService subCategoryService;
    private ContextHolderServices contextHolderServices;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, CategoryService categoryService,
                              SubCategoryService subCategoryService, ContextHolderServices contextHolderServices) {
        this.productRepository = productRepository;
        this.categoryService = categoryService;
        this.subCategoryService = subCategoryService;
        this.contextHolderServices = contextHolderServices;
    }

    @Override
    public Response addProducts(AddRequest request) {
        Product product=prepareToAddProduct(request);
        Product response=productRepository.save(product);
        return Response.builder().id(response.getId()).build();
    }

    private Product prepareToAddProduct(AddRequest request){
        Product product=new Product();
        product.setAddedBy(contextHolderServices.getContext().getUserName());
        product.setAddedDate(LocalDateTime.now());
        Category category=categoryService.validateCategoryId(request.getCategoryId());
        SubCategory subCategory=subCategoryService.validateSubCategoryById(request.getSubCategoryId());;
        product.setSubCategory(subCategory);
        product.setCategory(category);
        product.setColour(request.getColour());
        product.setPrice(request.getPrice());
        product.setPhoto(ImageUtil.compressBytes(request.getProductImage()));
        product.setQuantity(request.getQuantity());
        return product;
    }
}
