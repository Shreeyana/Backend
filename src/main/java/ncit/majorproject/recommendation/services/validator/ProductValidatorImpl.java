//package ncit.majorproject.recommendation.services.validator;
//
//import ncit.majorproject.entities.Product.Product;
//import ncit.majorproject.exception.RestException;
//import ncit.majorproject.repositories.product.ProductRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//@Service
//public class ProductValidatorImpl implements ProductValidator {
//
//    private ProductRepository productRepository;
//    @Autowired
//    public ProductValidatorImpl(ProductRepository productRepository){
//        this.productRepository = productRepository;
//    }
//    @Override
//    public Product validateProduct(Long id) {
//        return productRepository.validateProductById(id)
//                .orElseThrow(() -> new RestException("Not a valid product Id"));
//    }
//}
