package lk.carefirstpharmacy.productservice.controller;

import lk.carefirstpharmacy.productservice.dto.ProductRequest;
import lk.carefirstpharmacy.productservice.dto.ProductResponse;
import lk.carefirstpharmacy.productservice.model.Product;
import lk.carefirstpharmacy.productservice.model.Stock;
import lk.carefirstpharmacy.productservice.service.ProductService;
import lombok.RequiredArgsConstructor;

import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import jakarta.websocket.server.PathParam;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/product/stocks")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequiredArgsConstructor
public class StockController {

    private final ProductService productService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Product createStock(@RequestBody Stock stockreq) {

        stockreq.setSold(BigDecimal.valueOf(0));
        stockreq.setRemaining(stockreq.getQty());
        return productService.addStock(stockreq);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void updateStock(@RequestBody Stock stockreq) {
        productService.updateStock(stockreq);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<Stock> getStock(@PathVariable("id") String product_id) {

        return productService.getStock(product_id);
    }
}
