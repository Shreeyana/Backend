package nce.majorproject.services;

import nce.majorproject.dto.Response;
import nce.majorproject.dto.product.AddRequest;

public interface ProductService {

    Response addProducts(AddRequest request);
//    Response addCategory()
}
