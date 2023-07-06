package lk.carefirstpharmacy.orderservice.dto;

import lk.carefirstpharmacy.orderservice.model.OrderItems;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class OrderToPlace {
//    private String id;
//    private BigDecimal net_total;
//    private String timestamp;

    private String orderID;
    private String customerID;
    private String status;
    String timestamp;
    private BigDecimal net_total;
    private List<OrderItems> items;
}
