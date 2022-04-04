package pl.malek.freelancebackend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.math.BigDecimal;
import java.util.List;

@Data
public class PackageType {

    private pl.malek.freelancebackend.enums.PackageType packageType;

    private BigDecimal price;

    private String shortDescription;

    private long deliveryTime;

    @JsonProperty("packageDetails")
    private List<PackageDetail> packageDetails;

}
