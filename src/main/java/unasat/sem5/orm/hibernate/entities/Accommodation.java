package unasat.sem5.orm.hibernate.entities;

import javax.persistence.*;

@Entity
@Table(name = "accomodation")
public class Accommodation {

    @Id
    @Column(name = "accomodation_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accommodationId;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "rating", nullable = false)
    private int rating;

    @ManyToOne
    @JoinColumn(name = "destination_id", nullable = false)
    private Destination destination;

    public Long getAccommodationId() {
        return accommodationId;
    }

    public void setAccommodationId(Long accommodationId) {
        this.accommodationId = accommodationId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "Accommodation{" +
                "accommodationId=" + accommodationId +
                ", name='" + name + '\'' +
                ", rating=" + rating +
//                ", destination=" + destination +
                "}\n";
    }
}
