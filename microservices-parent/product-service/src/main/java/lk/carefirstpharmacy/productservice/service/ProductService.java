package lk.carefirstpharmacy.productservice.service;

import lk.carefirstpharmacy.productservice.dto.ProductRequest;
import lk.carefirstpharmacy.productservice.dto.ProductResponse;
import lk.carefirstpharmacy.productservice.model.Product;
import lk.carefirstpharmacy.productservice.model.Stock;
import lk.carefirstpharmacy.productservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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


    // [waruna start]
    public Product updateProduct(Product productReq) {
        Product product = productRepository.findById(productReq.getId()).get();
        product.setName(productReq.getName());
        product.setDescription(productReq.getDescription());
        product.setPrescription_drug(productReq.getPrescription_drug());
        product.setPrice(productReq.getPrice());

        productRepository.save(product);
        log.info("Product {} is updated successfully.", product.getId());
        return product;

    }

    public void setDeleteProducts(List<String> ids, boolean isDeleted) {
        List<Product> products = productRepository.findAllById(ids);
        for (Product product : products) {
            product.setIsDeleted(isDeleted);
        }

        productRepository.saveAll(products);

    }

    public Product addStock(Stock stockreq) {

        log.info(stockreq.getMenufacturedate());
        Product product = productRepository.findById(stockreq.getProductid()).get();

        List<Stock> stocks = product.getStock();
        if (stocks == null)
            stocks = new ArrayList<>();

        stocks.add(Stock.builder()
                .id(new ObjectId().toString())
                .isDeleted(false)
                .productid(stockreq.getProductid())
                .batchnumber(stockreq.getBatchnumber())
                .expiredate(stockreq.getExpiredate())
                .menufacturedate(stockreq.getMenufacturedate())
                .price(stockreq.getPrice())
                .qty(stockreq.getQty())
                .reason("")
                .build());
        product.setStock(stocks);

        productRepository.save(product);
        log.info("Stock {} is saved successfully.", stockreq.getProductid());
        return product;

    }

    public List<Stock> getStock(String id) {

        log.info(id);
        Product p = productRepository.findById(id).get();
        List<Stock> stocks = p.getStock();
        return stocks;
    }

    public void updateStock(Stock stockreq) {

        log.info(stockreq.getBatchnumber());
        log.info("stock id {}", stockreq.getId());
        log.info("reason is {}", stockreq.getReason());
        Product product = productRepository.findById(stockreq.getProductid()).get();

        if (product != null) {
            List<Stock> stocks = product.getStock();

            // Find the stock with the given ID
            for (Stock stock : stocks) {
                if (stock.getId().equals(stockreq.getId())) {
                    log.info("stock has been founded");
                    // Return the stock or perform any necessary update
                    stock.setIsDeleted(stockreq.getIsDeleted());
                    stock.setQty(stockreq.getQty());
                    stock.setReason(stockreq.getReason());
                    stock.builder().build();
                    productRepository.save(product);
                    log.info("stock has been updated");
                    break;
                }
            }
        } else {
            log.info("product is null");

        }
    }
    // [waruna end]
}
