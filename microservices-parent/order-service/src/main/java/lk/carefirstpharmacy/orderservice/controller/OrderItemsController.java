package lk.carefirstpharmacy.orderservice.controller;

import lk.carefirstpharmacy.orderservice.dto.OrderItemRequest;
import lk.carefirstpharmacy.orderservice.model.Order;
import lk.carefirstpharmacy.orderservice.model.OrderItems;
import lk.carefirstpharmacy.orderservice.service.OrderItemsService;
import lk.carefirstpharmacy.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.math.*;

@RestController
@RequestMapping("/api/orderitems")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequiredArgsConstructor
public class OrderItemsController {

    private final OrderItemsService orderItemsService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Order createOrderItem(@RequestBody OrderItemRequest orderItemRequest) {

        // generating the total
        BigDecimal unit_price = orderItemRequest.getUnit_price();
        int qty = orderItemRequest.getQty();
        BigDecimal total = unit_price.multiply(BigDecimal.valueOf(qty));
        orderItemRequest.setTotal(total);

        return orderItemsService.createOrderItem(orderItemRequest);
    }
}
