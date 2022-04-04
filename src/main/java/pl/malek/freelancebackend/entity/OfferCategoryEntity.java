package pl.malek.freelancebackend.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "service_category", schema = "services")
public class OfferCategoryEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "short_description", columnDefinition = "TEXT")
    private String shortDescription;

    @Column(name = "image")
    private String image; // todo

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "offerCategoryEntity")
    private List<OfferSubCategoryEntity> subCategories;



}
