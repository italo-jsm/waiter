package com.italo.waiter.controllers;

import com.italo.waiter.model.ConsumingUnit;
import com.italo.waiter.service.ConsumingUnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("consuming-units")
public class ConsumingUnitController {

    private final ConsumingUnitService consumingUnitService;

    @Autowired @Lazy
    public ConsumingUnitController(ConsumingUnitService consumingUnitService) {
        this.consumingUnitService = consumingUnitService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOneConsumingUnit(@PathVariable Long id){
        return consumingUnitService.getConsumingUnitById(id).map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
