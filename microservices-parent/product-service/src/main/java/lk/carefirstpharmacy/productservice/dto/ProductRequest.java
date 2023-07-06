package lk.carefirstpharmacy.productservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ProductRequest {

    private String name;
    private String description;
    private BigDecimal price;
    private Boolean prescription_drug;
    private String imageURL;
    private Boolean isDeleted;
}
