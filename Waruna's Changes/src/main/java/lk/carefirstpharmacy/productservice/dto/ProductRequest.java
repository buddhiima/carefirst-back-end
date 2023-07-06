package lk.carefirstpharmacy.productservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.mongodb.core.mapping.DBRef;

import lk.carefirstpharmacy.productservice.model.Stock;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ProductRequest {

    private String id;
    private String name;
    private String description;
    private BigDecimal price;
    private Boolean isDeleted;
    private Boolean prescription_drug;
    @DBRef
    private List<Stock> stock;
}
