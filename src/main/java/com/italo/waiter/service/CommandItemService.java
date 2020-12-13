package com.italo.waiter.service;

import com.italo.waiter.model.CommandItem;
import com.italo.waiter.model.ConsumingUnit;
import com.italo.waiter.repository.CommandItemRepository;
import com.italo.waiter.repository.ConsumingUnitRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CommandItemService {

    private final CommandItemRepository commandItemRepository;

    private final ConsumingUnitRepository consumingUnitRepository;

    public Optional<CommandItem> addCommandItemToConsumingUnit(final Integer consumingUnitNumber, final CommandItem commandItem){
        return consumingUnitRepository.findByNumber(consumingUnitNumber)
                .map(consumingUnit -> Optional.of(commandItemRepository.save(addConsumingUnit(commandItem, consumingUnit))))
                .orElseThrow(() -> new EntityNotFoundException("Consuming unit not found"));
    }

    public Optional<CommandItem> removeCommandItemFromConsumingUnit(long consumingUnitId, CommandItem commandItem) {
        return consumingUnitRepository.findById((consumingUnitId))
                .map(consumingUnit -> {
                    verifyCommandItem(commandItem, consumingUnit);
                    commandItem.setConsumingUnit(null);
                    return Optional.of(commandItemRepository.save(commandItem));
                })
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

    void verifyCommandItem(CommandItem commandItem, ConsumingUnit consumingUnit){
        if(!commandItem.getConsumingUnit().equals(consumingUnit)){
            throw new RuntimeException("CommandItem is not from this consuming unit!");
        }
    }
}
