package pl.malek.freelancebackend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class Offer {

    private String title;

    private String description;

    @JsonProperty("packageTypes")
    private List<PackageType> packageTypes;

    private long subCategoryId;

}
