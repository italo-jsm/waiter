package com.italo.waiter.service;

import com.italo.waiter.model.CommandItem;
import com.italo.waiter.repository.CommandItemRepository;
import com.italo.waiter.repository.ConsumintUnitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
public class CommandItemService {

    private CommandItemRepository commandItemRepository;

    private ConsumintUnitRepository consumintUnitRepository;

    @Autowired @Lazy
    public CommandItemService(CommandItemRepository commandItemRepository, ConsumintUnitRepository consumintUnitRepository) {
        this.commandItemRepository = commandItemRepository;
        this.consumintUnitRepository = consumintUnitRepository;
    }


    public Optional<CommandItem> addCommandItemToConsumingUnit(Long consumingUnitId, CommandItem commandItem){
        return consumintUnitRepository.findById(consumingUnitId)
                .map(consumingUnit -> {
                    commandItem.setConsumingUnit(consumingUnit);
                    return Optional.of(commandItemRepository.save(commandItem));
                }).orElseThrow(() -> new EntityNotFoundException("Consuming unit not found"));
    }
}
