package nce.majorproject.recommendation.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "recommendation_repos")
public class DataSetReferer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long sn;

    @Column(name = "clothing_id")
    private String clothingId;

    @Column(name = "age")
    private Long age;

    @Column(name = "rating")
    private String rating;

    @Column(name = "sub_sub_category")
    private String subSubCategory;
}
