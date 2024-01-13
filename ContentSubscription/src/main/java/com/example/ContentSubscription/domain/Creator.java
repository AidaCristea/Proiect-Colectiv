package com.example.ContentSubscription.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="creator")
public class Creator {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "creator_id")
    private Long creatorId;

    private String bio;
    private Long priceUltimate;
    private Long priceLite;
    private Long pricePro;
    private String photoURL;

    /*@ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "fan_subscribed_creators",
            joinColumns = {
                    @JoinColumn(name = "creator_id", referencedColumnName = "creator_id",
                            nullable = false, updatable = false)},
            inverseJoinColumns = {
                    @JoinColumn(name = "fan_id", referencedColumnName = "fan_id",
                            nullable = false, updatable = false)})
    @JsonIgnore
    private List<Fan> fans;*/

    /*@OneToMany(mappedBy = "creator")
    private List<Post> posts;
*/
    @OneToMany(mappedBy = "creator")
    @JsonIgnore
    private List<SubscriptionType> subscriptionTypes;

   /* @Override
    public String toString() {
        return "Creator{" +
                "creatorId=" + creatorId +
                ", bio='" + bio + '\'' +
                // Exclude the subscriptionTypes from the toString representation
                '}';
    }*/


}
