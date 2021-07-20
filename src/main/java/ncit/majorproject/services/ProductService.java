package ncit.majorproject.services;

import ncit.majorproject.dto.FilterProduct;
import ncit.majorproject.dto.Response;
import ncit.majorproject.dto.product.AddRequest;
import ncit.majorproject.dto.product.LatestAddedProductResponse;
import ncit.majorproject.entities.Product.Product;

import java.io.IOException;
import java.util.List;

public interface ProductService {

    Response addProducts(AddRequest request) throws IOException;
    List<LatestAddedProductResponse> showLatestAdded();
    Product validateProduct(Long id);
    List<LatestAddedProductResponse> randomProduct();
    LatestAddedProductResponse getProductById(Long id);
    List<LatestAddedProductResponse> filter(FilterProduct filter);

    List<LatestAddedProductResponse> getTopFive();
}
