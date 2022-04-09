package pl.malek.freelancebackend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PackageDetail {

    @NotBlank(message = "Field packageType.packageDetail.definition -> must not be blank")
    @Length(min = 4,
            max = 35,
            message = "Field packageType.packageDetail.definition -> length should be between 4 and 15")
    private String definition;

    @NotNull(message = "Field packageType.packageDetail.availability -> should not be null")
    private boolean availability;

}
