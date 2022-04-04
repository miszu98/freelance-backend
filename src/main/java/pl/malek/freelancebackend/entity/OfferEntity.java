package pl.malek.freelancebackend.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user_services", schema = "services")
public class OfferEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "modified_at")
    private LocalDateTime modifiedAt;

    @Column(name = "title", columnDefinition = "TEXT")
    private String title;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "offerEntity")
    @JsonProperty("packageTypes")
    private List<PackageTypeEntity> packageTypeEntities;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserEntity userEntity;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sub_category_id", referencedColumnName = "id")
    private OfferSubCategoryEntity offerSubCategoryEntity;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "offerEntity")
    private List<CommentEntity> comments;

}
