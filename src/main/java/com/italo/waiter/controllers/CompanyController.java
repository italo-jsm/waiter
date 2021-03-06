package com.italo.waiter.controllers;

import com.italo.waiter.model.Company;
import com.italo.waiter.model.dto.CompanyDto;
import com.italo.waiter.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

@RequiredArgsConstructor
@RestController
@RequestMapping("/companies")
@CrossOrigin("*")
public class CompanyController {
    private final CompanyService companyService;

    private final ModelMapper modelMapper;

    @PostMapping
    public ResponseEntity<?> createCompany(@RequestBody CompanyDto company) throws URISyntaxException {
        return ResponseEntity.created(new URI("/companies" + companyService.saveCompany(modelMapper.map(company, Company.class)).getId())).build();
    }
}
