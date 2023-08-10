package lk.carefirstpharmacy.orderservice.controller;

import lk.carefirstpharmacy.orderservice.dto.OrderNewRequest;
import lk.carefirstpharmacy.orderservice.dto.OrderToPlace;
import lk.carefirstpharmacy.orderservice.dto.OrdersResponse;
import lk.carefirstpharmacy.orderservice.model.Order;
import lk.carefirstpharmacy.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/order")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/new")
    @ResponseStatus(HttpStatus.CREATED)
    public String createOrder(OrderNewRequest orderNewRequest) {

        // setting the order status as created
        orderNewRequest.setStatus("created");

        return orderService.createOrder(orderNewRequest);
    }


    @PutMapping("/placeorder")
    @ResponseStatus(HttpStatus.OK)
    public void placeOrder(Order order) {

        // setting the timestamp of order placed
        Date date = new Date();
        order.setTimestamp(String.valueOf(date));

        orderService.placeOrder(order);
    }

    @GetMapping("/{orderID}")
    @ResponseStatus(HttpStatus.OK)
    public Order getOrderByID(@PathVariable("orderID") String orderID) {

        return orderService.getOrderByID(orderID);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<OrdersResponse> getOrders() {

        return orderService.getOrders();
    }

    @GetMapping("/{status}/{customerID}")
    @ResponseStatus(HttpStatus.OK)
    public Order findByStatusAndCustomerID(@PathVariable("status") String status, @PathVariable("customerID") String customerID) {
        return orderService.findByStatusAndCustomerID(status, customerID);
    }
}
