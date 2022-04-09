package pl.malek.freelancebackend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Data
public class Offer {

    @Length(min = 10,
            max = 100,
            message = "Field title -> length must be between 10 and 100 characters")
    @NotBlank(message = "Field title -> must not be blank")
    private String title;

    @Length(min = 100,
            max = 500,
            message = "Field description -> length must be between 100 and 500 characters")
    @NotBlank(message = "Field description -> must not be blank")
    private String description;

    @Valid
    @JsonProperty("packageTypes")
    @Size(min = 3, max = 3, message = "Field packageTypes -> number of packages should be 3")
    private List<PackageType> packageTypes;

    @Min(value = 1, message = "Field subCategoryId -> should not be negative number")
    private long subCategoryId;

}
