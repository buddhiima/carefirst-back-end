package lk.carefirstpharmacy.productservice.controller;

import lk.carefirstpharmacy.productservice.dto.ProductRequest;
import lk.carefirstpharmacy.productservice.dto.ProductResponse;
import lk.carefirstpharmacy.productservice.model.Product;
import lk.carefirstpharmacy.productservice.service.ProductService;
import lombok.RequiredArgsConstructor;

import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import jakarta.websocket.server.PathParam;

import java.util.List;

@RestController
@RequestMapping("/api/product")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createProduct(@RequestBody ProductRequest productRequest) {
        productService.createProduct(productRequest);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Product updateProduct(@RequestBody Product productRequest) {
        return productService.updateProduct(productRequest);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProductResponse> GetAllProducts() {
        return productService.getAllProducts();
    }

    @DeleteMapping("/delete/{delete}")
    @ResponseStatus(HttpStatus.OK)
    public void setDeleteState(@RequestBody List<String> ids, @PathVariable("delete") int delete) {

        System.out.println("Received data: " + ids);

        System.out.println("Received path variable: " + delete);
        productService.setDeleteProducts(ids, delete == 1 ? true : false);
    }
}
