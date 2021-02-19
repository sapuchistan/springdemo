package com.cfg.demo.core.website;
import com.cfg.demo.core.country.Country;
import com.cfg.demo.core.rating.Rating;
import com.cfg.demo.core.technology.Technology;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "ws_website")
public class WebSite {

    @Id
    @Column(name = "id")
    @GenericGenerator(
            name = "ws_website_id_seq",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "sequence_name", value = "ws_website_id_seq"),
                    @org.hibernate.annotations.Parameter(name= "INCREMENT", value = "1"),
                    @org.hibernate.annotations.Parameter(name = "MINVALUE", value = "1"),
                    @org.hibernate.annotations.Parameter(name = "MAXVALUE", value = "2147483647"),
                    @org.hibernate.annotations.Parameter(name = "CACHE", value = "1")
            }
    )

    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ws_website_id_seq")
    private long id;

    @NotEmpty
    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    @JoinTable(name = "ws_site_technology",
            joinColumns = { @JoinColumn(name = "id_site") },
            inverseJoinColumns = { @JoinColumn(name = "id_technology") })
    private Set<Technology> technologies = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY,mappedBy ="webSite")
    private Set<Rating> ratings = new HashSet<>();

    @Column(name = "name")
    private String name;

    @Column(name = "url")
    private String url;

    @Column(name = "rating")
    private int rating;

    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "id")
    private Country country;

    @Column(name="ratingSum")
    private Long ratingSum;

    @Column(name="ratingCount")
    private Long ratingCount;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public Set<Technology> getTechnologies() {
        return technologies;
    }
    public void setTechnologies(Set<Technology> technologies) {
        this.technologies = technologies;
    }

    public Set<Rating> getRatings() {
        return ratings;
    }
    public void setRatings(Set<Rating> ratings) {
        this.ratings = ratings;
    }

    public Long getRatingSum() {
        return ratingSum;
    }

    public void setRatingSum(Long ratingSum) {
        this.ratingSum = ratingSum;
    }

    public Long getRatingCount() {
        return ratingCount;
    }

    public void setRatingCount(Long ratingCount) {
        this.ratingCount = ratingCount;
    }
    public void addRating(Rating rating) {
        ratings.add(rating);
        setRatings(ratings);
    }

    public void removeRating(Rating rating) {
        ratings.remove(rating);
        setRatings(ratings);
    }
}
