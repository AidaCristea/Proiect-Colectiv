package com.example.ContentSubscription.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="subscriptionType")
public class SubscriptionType {


    public enum Type {
        ULTIMATE,
        PRO,
        LITE
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="subscription_type_id")
    private Long subscriptionTypeId;

    //private String type;
    private double price;
    private String description;

    @Enumerated(EnumType.STRING)
    private Type type;


    /*@OneToMany(mappedBy = "subscriptionType", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Post> posts;*/


    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "creator_id")
    @ToString.Exclude
    private Creator creator;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "fan_id")
    private Fan fan;

    @OneToMany(mappedBy = "subscriptionType")
    @JsonIgnore
    private List<Post> posts;

    @Override
    public String toString() {
        return "SubscriptionType{" +
                "subscriptionTypeId=" + subscriptionTypeId +
                ", type='" + type + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                // Exclude other related entities from the toString representation
                '}';
    }

}
