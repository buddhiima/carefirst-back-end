package lk.carefirstpharmacy.orderservice.service;
import lk.carefirstpharmacy.orderservice.model.Order;

import lk.carefirstpharmacy.orderservice.dto.OrderItemRequest;
import lk.carefirstpharmacy.orderservice.model.OrderItems;
import lk.carefirstpharmacy.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderItemsService {

    private final OrderRepository orderRepository;

    public Order createOrderItem(OrderItemRequest orderItemRequest) {

        Order order = orderRepository.findById(orderItemRequest.getId()).get();

        List<OrderItems> orderItems = order.getItems();
        if(orderItems == null)
            orderItems = new ArrayList<>();

        orderItems.add(OrderItems.builder()
                .id(orderItemRequest.getId())
                .productID(orderItemRequest.getProductID())
                .qty(orderItemRequest.getQty())
                .unit_price(orderItemRequest.getUnit_price())
                .total(orderItemRequest.getTotal())
                .build()
        );

        System.out.println("serv" +orderItems);

        order.setItems(orderItems);
        orderRepository.save(order);

        return order;
    }
}
