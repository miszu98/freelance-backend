package pl.malek.freelancebackend.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.malek.freelancebackend.enums.PackageType;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "package_types", schema = "services")
public class PackageTypeEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "package_type")
    @Enumerated(EnumType.STRING)
    private PackageType packageType;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "short_description", columnDefinition = "TEXT")
    private String shortDescription;

    @Column(name = "delivery_time")
    private Long deliveryTime;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "packageTypeEntity")
    @JsonProperty("packageDetails")
    private List<PackageDetailsEntity> packageDetailsEntities;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "offer_id", referencedColumnName = "id")
    private OfferEntity offerEntity;

}
