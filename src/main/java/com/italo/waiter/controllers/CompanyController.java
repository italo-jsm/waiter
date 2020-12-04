package com.italo.waiter.controllers;

import com.italo.waiter.model.Company;
import com.italo.waiter.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/companies")
@CrossOrigin("*")
public class CompanyController {
    private final CompanyService companyService;

    @Autowired @Lazy
    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @PostMapping
    public ResponseEntity<?> createCompany(@RequestBody Company company) throws URISyntaxException {
        return ResponseEntity.created(new URI("/companies" + companyService.saveCompany(company).getId())).build();
    }
}
