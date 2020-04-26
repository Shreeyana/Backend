package nce.majorproject.dto.product;

import lombok.Getter;
import lombok.Setter;
import org.jboss.resteasy.annotations.providers.multipart.PartType;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.ws.rs.FormParam;

@Getter
@Setter
public class AddRequest {
    @NotBlank(message = "Name is mandatory")
    @FormParam("Name")
    @PartType("text/plain")
    private String name;

    @FormParam("colour")
    @PartType("text/plain")
    private String colour;

    @NotBlank(message = "Price is mandatory")
    @FormParam("price")
    @PartType("text/plain")
    private double price;

    @NotBlank(message = "Category Id is mandatory")
    @FormParam("categoryId")
    @PartType("text/plain")
    private Long categoryId;

    @NotBlank(message = "Sub Category Id is mandatory")
    @FormParam("subCategoryId")
    @PartType("text/plain")
    private Long subCategoryId;

    @FormParam("productImage")
    @PartType("image/jpg")
    private byte[] productImage;
}
