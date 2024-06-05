package com.example.license.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString(exclude = "licenses")
@Entity
@Table(name = "types")
public class Type {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

//    //    @JsonIgnore
//    @OneToMany(mappedBy = "idType", cascade = CascadeType.ALL)
//    private List<License> licenses = new ArrayList<>();

    @JsonManagedReference
    @OneToMany(mappedBy = "idType", cascade = CascadeType.ALL)
    private List<License> licenses = new ArrayList<>();
}
