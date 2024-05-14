package com.example.license.model;

import jakarta.persistence.Id;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import org.springframework.hateoas.RepresentationModel;

@Getter
@Setter
@ToString
public class Organization extends RepresentationModel<Organization> {
	@Id
	String id;
    String name;
    String contactName;
    String contactEmail;
    String contactPhone;
}
