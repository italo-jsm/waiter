package com.italo.waiter.controllers;

import com.italo.waiter.model.dto.ConsumingUnitDto;
import com.italo.waiter.service.ConsumingUnitService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

@RequiredArgsConstructor
@RestController
@RequestMapping("consuming-units")
@CrossOrigin("*")
public class ConsumingUnitController {

    private final ConsumingUnitService consumingUnitService;
    private final Logger logger = LoggerFactory.getLogger("ConsumingUnitController");

    @GetMapping("/{id}") @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> getOneConsumingUnit(@PathVariable Long id){
        return consumingUnitService.getConsumingUnitById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping() @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> getAllConsumingUnit(){
        return ResponseEntity.ok(consumingUnitService.getAllConsumingUnits());
    }

    @PostMapping() @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> createConsumingUnit(@RequestBody ConsumingUnitDto consumingUnit) throws URISyntaxException {
        return ResponseEntity.created(new URI("consuming-units/" + Long.toString(consumingUnitService.createConsumingUnit(consumingUnit)))).build();
    }
}
