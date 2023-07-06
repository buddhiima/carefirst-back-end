package lk.carefirstpharmacy.productservice.service;

import lk.carefirstpharmacy.productservice.dto.ProductRequest;
import lk.carefirstpharmacy.productservice.dto.ProductResponse;
import lk.carefirstpharmacy.productservice.model.Product;
import lk.carefirstpharmacy.productservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;

    public void createProduct(ProductRequest productRequest) {

        // building the object of type Product
        Product product = Product.builder()
                .name(productRequest.getName())
                .description(productRequest.getDescription())
                .price(productRequest.getPrice())
                .prescription_drug(productRequest.getPrescription_drug())
                .imageURL(productRequest.getImageURL())
                .build();

        productRepository.save(product);
        log.info("Product {} is saved successfully.",product.getId());
    }


    public List<ProductResponse> getAllProducts() {
        List<Product> products = productRepository.findAll();

        // mapping Products class into ProductResponse class
        return products.stream().map(this::mapToProductResponse).toList();
    }

    private ProductResponse mapToProductResponse(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .prescription_drug(product.getPrescription_drug())
                .imageURL(product.getImageURL())
                .build();
    }
}
