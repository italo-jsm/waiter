package com.italo.waiter.service;

import com.italo.waiter.model.CommandItem;
import com.italo.waiter.model.ConsumingUnit;
import com.italo.waiter.repository.CommandItemRepository;
import com.italo.waiter.repository.ConsumintUnitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
public class CommandItemService {

    private final CommandItemRepository commandItemRepository;

    private final ConsumintUnitRepository consumintUnitRepository;

    @Autowired @Lazy
    public CommandItemService(CommandItemRepository commandItemRepository, ConsumintUnitRepository consumintUnitRepository) {
        this.commandItemRepository = commandItemRepository;
        this.consumintUnitRepository = consumintUnitRepository;
    }


    public Optional<CommandItem> addCommandItemToConsumingUnit(final Long consumingUnitId, final CommandItem commandItem){
        return consumintUnitRepository.findById(consumingUnitId)
                .map(consumingUnit -> Optional.of(commandItemRepository.save(addConsumingUnit(commandItem, consumingUnit)))).orElseThrow(() -> new EntityNotFoundException("Consuming unit not found"));
    }

    public Optional<CommandItem> removeCommandItemFromConsumingUnit(long consumingUnitId, CommandItem commandItem) {
        return consumintUnitRepository.findById((consumingUnitId))
                .map(consumingUnit -> Optional.of(commandItemRepository.save(removeConsumingUnit(commandItem))))
                .orElseThrow(() -> new EntityNotFoundException("Consuming unit not found"));
    }

    CommandItem removeConsumingUnit(CommandItem commandItem) {
        commandItem.setConsumingUnit(null);
        return commandItem;
    }

    CommandItem addConsumingUnit(CommandItem commandItem, ConsumingUnit consumingUnit) {
        commandItem.setConsumingUnit(consumingUnit);
        return commandItem;
    }
}
