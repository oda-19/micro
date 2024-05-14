package com.example.license.service.client;

import brave.ScopedSpan;
import brave.Tracer;

import com.example.license.model.Organization;
import com.example.license.utils.UserContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class OrganizationRestTemplateClient {
	@Autowired
	RestTemplate restTemplate;

	public Organization getOrganization(String organizationId){
		ResponseEntity<Organization> restExchange =
				restTemplate.exchange(
						"http://organization-service/v1/organization/{organizationId}",
						HttpMethod.GET,
						null, Organization.class, organizationId);

		return restExchange.getBody();
	}
}
