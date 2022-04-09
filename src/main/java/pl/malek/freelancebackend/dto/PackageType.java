package pl.malek.freelancebackend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.List;

@Data
public class PackageType {

    private pl.malek.freelancebackend.enums.PackageType packageType;

    @Min(value = 0, message = "Field packageType.price -> should not be negative number")
    private BigDecimal price;

    @Length(min = 50,
            max = 100,
            message = "Field packageType.shortDescription -> length must be between 50 and 100 characters")
    private String shortDescription;

    @Min(value = 0, message = "Field packageType.deliveryTime -> should not be negative number")
    private long deliveryTime;

    @Valid
    @JsonProperty("packageDetails")
    @Size(min = 3, max = 3, message = "Field packageType.packageDetails -> length should be 3")
    private List<PackageDetail> packageDetails;

}
