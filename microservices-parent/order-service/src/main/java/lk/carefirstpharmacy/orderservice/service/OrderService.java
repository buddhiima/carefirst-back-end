package lk.carefirstpharmacy.orderservice.service;

import lk.carefirstpharmacy.orderservice.dto.OrderNewRequest;
import lk.carefirstpharmacy.orderservice.dto.OrderToPlace;
import lk.carefirstpharmacy.orderservice.dto.OrdersResponse;
import lk.carefirstpharmacy.orderservice.model.Order;
import lk.carefirstpharmacy.orderservice.model.OrderItems;
import lk.carefirstpharmacy.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {

    private final OrderRepository orderRepository;

    public String createOrder(OrderNewRequest orderNewRequest) {

        // building the object of type Order
        Order order = Order.builder()
                .orderID(orderNewRequest.getOrderID())
                .status(orderNewRequest.getStatus())
                .customerID(orderNewRequest.getCustomerID())
                .build();

        orderRepository.save(order);

        return order.getOrderID();
    }

    public void placeOrder(Order order) {

        Order order1 = orderRepository.findById(order.getOrderID()).get();

        BigDecimal final_total = BigDecimal.valueOf(0.0);

        if(order1 != null) {
            List<OrderItems> orderItems = order1.getItems();

            BigDecimal net_total = BigDecimal.valueOf(0.0);

            for(OrderItems orderItem : orderItems) {
                net_total = net_total.add(orderItem.getTotal());
            }
            final_total = net_total;

            order.setNet_total(final_total);
            order.setStatus(order1.getStatus());
            order.setItems(order1.getItems());
            order.setCustomerID(order1.getCustomerID());

            orderRepository.save(order);
        }
    }

    public Order getOrderByID(String orderID) {
        Order order = orderRepository.findById(orderID).get();
        return  order;
    }

    public List<OrdersResponse> getOrders() {
        List<Order> orders = orderRepository.findAll();

        // mapping Orders class into OrdersResponse class
        return orders.stream().map(this::mapToProductResponse).toList();
    }

    private OrdersResponse mapToProductResponse(Order order) {
        return OrdersResponse.builder()
                .orderID(order.getOrderID())
                .status(order.getStatus())
                .timestamp(order.getTimestamp())
                .net_total(order.getNet_total())
                .items(order.getItems())
                .customerID(order.getCustomerID())
                .build();
    }

    public Order findByStatusAndCustomerID(String status, String customerID) {
        Order order = orderRepository.findByStatusAndCustomerID(status, customerID);

        System.out.println("order "+order);
        return order;
    }
}
