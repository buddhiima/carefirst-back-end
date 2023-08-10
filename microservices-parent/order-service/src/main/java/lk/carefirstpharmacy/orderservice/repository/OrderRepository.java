package lk.carefirstpharmacy.orderservice.repository;

import lk.carefirstpharmacy.orderservice.model.Order;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends MongoRepository<Order, String> {
    Order findByStatusAndCustomerID(String status, String customerID);
}
