package nce.majorproject.services;

import nce.majorproject.dto.Response;
import nce.majorproject.dto.product.AddRequest;
import nce.majorproject.dto.product.LatestAddedProductResponse;

import java.io.IOException;
import java.lang.invoke.LambdaConversionException;
import java.util.List;

public interface ProductService {

    Response addProducts(AddRequest request) throws IOException;
    List<LatestAddedProductResponse> showLatestAdded();
}
