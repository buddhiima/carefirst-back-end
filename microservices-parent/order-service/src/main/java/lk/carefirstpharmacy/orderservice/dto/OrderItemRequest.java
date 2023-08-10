package lk.carefirstpharmacy.orderservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class OrderItemRequest {
    private String id;
    private String productID;
    private String name;
    private int qty;
    private BigDecimal unit_price;
    private BigDecimal total;
}
