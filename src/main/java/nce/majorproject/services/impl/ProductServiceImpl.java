package nce.majorproject.services.impl;

import nce.majorproject.context.ContextHolderServices;
import nce.majorproject.dto.Response;
import nce.majorproject.dto.product.AddRequest;
import nce.majorproject.dto.product.LatestAddedProductResponse;
import nce.majorproject.entities.Product.Category;
import nce.majorproject.entities.Product.Product;
import nce.majorproject.entities.Product.SubCategory;
import nce.majorproject.entities.User;
import nce.majorproject.exception.RestException;
import nce.majorproject.repositories.product.CategoryRepository;
import nce.majorproject.repositories.product.ProductRepository;
import nce.majorproject.repositories.product.SubCategoryRepository;
import nce.majorproject.services.CategoryService;
import nce.majorproject.services.ProductService;
import nce.majorproject.services.SubCategoryService;
import nce.majorproject.util.ImageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    public Response addProducts(AddRequest request) throws IOException {
        Product product=prepareToAddProduct(request);
        Product response=productRepository.save(product);
        return Response.builder().id(response.getId()).build();
    }

    @Override
    public List<LatestAddedProductResponse> showLatestAdded() {
        List<Product> productList=productRepository.findLatestAddedProduct();
        List<LatestAddedProductResponse> productResponseList=new ArrayList<>();
        productList.forEach(latestAddedProductResponse -> {
            LatestAddedProductResponse productResponse=prepareToShowLatestAddedProduct(latestAddedProductResponse);
            productResponseList.add(productResponse);
        });
        return productResponseList;
    }

    private LatestAddedProductResponse prepareToShowLatestAddedProduct(Product product){
        LatestAddedProductResponse response=new LatestAddedProductResponse();
        response.setId(product.getId());
        response.setName(product.getProductName());
        response.setPrice(product.getPrice());
        response.setQuantity(product.getQuantity());
        response.setPhoto(ImageUtil.decompressBytes(product.getPhoto()));
        return response;
    }

    private Product prepareToAddProduct(AddRequest request) throws IOException {
       byte[] image=request.getProductImage().getBytes();
        Product product=new Product();
        product.setAddedBy("sandip");
        product.setAddedDate(LocalDateTime.now());
        Category category=categoryService.validateCategoryId(request.getCategoryId());
        SubCategory subCategory=subCategoryService.validateSubCategoryById(request.getSubCategoryId());;
        product.setSubCategory(subCategory);
        product.setCategory(category);
        product.setColour(request.getColour());
        product.setPrice(request.getPrice());
        product.setPhoto(ImageUtil.compressBytes(image));
        product.setQuantity(request.getQuantity());
        return product;
    }
    public Product validateProduct(Long productId){
        Optional<Product> validate = productRepository.validateProductById(productId);
        Product product=validate.orElseThrow(()->new RestException("invalid product id"));
        return product;
    }
}
