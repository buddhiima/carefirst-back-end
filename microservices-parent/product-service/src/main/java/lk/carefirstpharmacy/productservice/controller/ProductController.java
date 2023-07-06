package lk.carefirstpharmacy.productservice.controller;

import lk.carefirstpharmacy.productservice.dto.ProductRequest;
import lk.carefirstpharmacy.productservice.dto.ProductResponse;
import lk.carefirstpharmacy.productservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/product")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createProduct(ProductRequest productRequest) {
        System.out.println("productRequest "+productRequest);

        // generating a shareable google drive link to image
        String imageURL = productRequest.getImageURL();
        String convertedImageURL1 = imageURL.replace("https://drive.google.com/file/d/", "https://drive.google.com/uc?export=view&id=");
        String convertedImageURL2 = convertedImageURL1.replace("/view?usp=drive_link", "");
        System.out.println("convertedImageURL2 "+convertedImageURL2);
        productRequest.setImageURL(convertedImageURL2);

        productService.createProduct(productRequest);
    }


    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProductResponse> GetAllProducts() {
        return productService.getAllProducts();
    }

}
