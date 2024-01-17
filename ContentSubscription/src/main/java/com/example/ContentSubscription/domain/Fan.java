package com.example.ContentSubscription.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name="fan")
public class Fan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="fan_id")
    private Long fanId;

    private String preferences;

    @OneToMany(mappedBy = "fan")
    private List<SubscriptionType> subscriptionTypes;

    /*@ManyToMany(mappedBy = "fans", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Creator> subscribedCreators;*/

}
