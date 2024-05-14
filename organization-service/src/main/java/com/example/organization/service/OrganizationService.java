package com.example.organization.service;

import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.organization.model.Organization;
import com.example.organization.repository.OrganizationRepository;

@Service
public class OrganizationService {
    private static final Logger logger = LoggerFactory.getLogger(OrganizationService.class);

    @Autowired
    private OrganizationRepository repository;

    public Organization findById(String organizationId) {
        Optional<Organization> opt = repository.findById(organizationId);
        return (opt.isPresent()) ? opt.get() : null;
    }

    public Organization create(Organization organization){
        organization.setId( UUID.randomUUID().toString());
        organization = repository.save(organization);
        return organization;

    }

    public void update(Organization organization){
        repository.save(organization);
    }

    public void delete(Organization organization){
        repository.deleteById(organization.getId());
    }

    @SuppressWarnings("unused")
    private void sleep(){
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            logger.error(e.getMessage());
        }
    }
}