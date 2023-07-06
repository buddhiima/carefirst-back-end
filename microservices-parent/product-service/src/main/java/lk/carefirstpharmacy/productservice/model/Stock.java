package lk.carefirstpharmacy.productservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.rmi.server.ObjID;

@Document
@AllArgsConstructor
@Builder
@Data
public class Stock {

    @Id
    private String id;
    private String batchnumber;
    private String productid;
    private String expiredate;
    private String menufacturedate;
    private BigDecimal price;
    private Boolean isDeleted;
    private BigDecimal qty;
    private String reason;
    private BigDecimal sold;
    private BigDecimal remaining;

    public Stock() {
        // id = new ObjectId().toString();
    }
}
