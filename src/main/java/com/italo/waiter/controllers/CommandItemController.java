package com.italo.waiter.controllers;

import com.italo.waiter.model.CommandItem;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/command-item")
public class CommandItemController {

    @PostMapping("/consumingUnit/{id}")
    public ResponseEntity<?> addCommandItemToConsumingUnit(@PathVariable Long consumingUnitId, CommandItem commandItem){
        return ResponseEntity.noContent().build();
    }
}
