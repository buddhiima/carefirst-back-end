package lk.carefirstpharmacy.orderservice.repository;

import lk.carefirstpharmacy.orderservice.model.Order;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends MongoRepository<Order, String> {
}
