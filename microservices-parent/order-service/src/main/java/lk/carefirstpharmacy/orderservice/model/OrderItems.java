package lk.carefirstpharmacy.orderservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Document(value="order")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class OrderItems {
    private String id;
    private String productID;
    private int qty;
    private BigDecimal unit_price;
    private BigDecimal total;
}
