package com.example.ContentSubscription.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="fan")
public class Fan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="fan_id")
    private Long fanId;

    private String preferences;

    @ManyToMany(mappedBy = "fans", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Creator> subscribedCreators;

}
