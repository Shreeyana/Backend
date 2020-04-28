package nce.majorproject.controller.product;

import lombok.extern.slf4j.Slf4j;
import nce.majorproject.constant.Route;
import nce.majorproject.dto.Response;
import nce.majorproject.dto.product.AddRequest;
import nce.majorproject.entities.Product.SubCategory;
import nce.majorproject.services.CategoryService;
import nce.majorproject.services.ProductService;
import nce.majorproject.services.SubCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

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

    @PostMapping()
    public Response addProduct(@Valid @RequestBody AddRequest request){
        log.info("adding product::{}",request.getName());
        return productService.addProducts(request);
    }
}
