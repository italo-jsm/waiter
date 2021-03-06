package com.italo.waiter.controllers;

import com.italo.waiter.model.CommandItem;
import com.italo.waiter.service.CommandItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/command-items")
@CrossOrigin("*")
public class CommandItemController {

    private final CommandItemService commandItemService;

    @PostMapping("/consumingUnit/{id}") @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> addCommandItemToConsumingUnit(@PathVariable Integer id, @RequestBody CommandItem commandItem){
        return commandItemService.addCommandItemToConsumingUnit(id, commandItem)
                .map(item -> ResponseEntity.ok().build())
                .orElse(ResponseEntity.badRequest().build());
    }
}
