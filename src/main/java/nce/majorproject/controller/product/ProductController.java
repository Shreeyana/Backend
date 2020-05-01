package nce.majorproject.controller.product;

import lombok.extern.slf4j.Slf4j;
import nce.majorproject.constant.Route;
import nce.majorproject.dto.Response;
import nce.majorproject.dto.product.AddRequest;
import nce.majorproject.dto.product.CategoryRequest;
import nce.majorproject.dto.product.LatestAddedProductResponse;
import nce.majorproject.dto.product.SubCategoryRequest;
import nce.majorproject.services.CategoryService;
import nce.majorproject.services.ProductService;
import nce.majorproject.services.SubCategoryService;
import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(Route.PRODUCT)
@Slf4j
public class ProductController {
    private ProductService productService;
    private CategoryService categoryService;
    private SubCategoryService subCategoryService;

    @Autowired
    public ProductController(ProductService productService, CategoryService categoryService, SubCategoryService subCategoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
        this.subCategoryService = subCategoryService;
    }

    @PostMapping(value = "/add-product")
    public Response addProduct(@MultipartForm AddRequest request){
        log.info("adding product::{}",request.getName());
        return productService.addProducts(request);
    }
    @PostMapping(value ="/add-category")
    public Response addCategory(@Valid @RequestBody CategoryRequest request){
        log.info("adding category::{}",request.getName());
        return categoryService.addCategory(request);
    }
    @PostMapping(value ="/add-subCategory")
    public Response addSubCategory(@Valid @RequestBody SubCategoryRequest request){
        log.info("adding category::{}",request.getName());
        return subCategoryService.addSubCategory(request);
    }
    @GetMapping(value = "/latest-added")
    public List<LatestAddedProductResponse> getLatest(){
        log.info("latest added::");
        return productService.showLatestAdded();
    }
}
