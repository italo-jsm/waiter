package com.italo.waiter.controllers;

import com.italo.waiter.model.CommandItem;
import com.italo.waiter.service.CommandItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/command-items")
@CrossOrigin("*")
public class CommandItemController {

    private final CommandItemService commandItemService;

    @Autowired @Lazy
    public CommandItemController(CommandItemService commandItemService){
        this.commandItemService = commandItemService;
    }

    @PostMapping("/consumingUnit/{id}")
    public ResponseEntity<?> addCommandItemToConsumingUnit(@PathVariable Integer id, @RequestBody CommandItem commandItem){
        return commandItemService.addCommandItemToConsumingUnit(id, commandItem)
                .map(item -> ResponseEntity.ok().build())
                .orElse(ResponseEntity.badRequest().build());
    }
}
