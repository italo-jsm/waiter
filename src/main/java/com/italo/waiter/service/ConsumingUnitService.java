package com.italo.waiter.service;

import com.italo.waiter.model.ConsumingUnit;
import com.italo.waiter.repository.CommandItemRepository;
import com.italo.waiter.repository.ConsumintUnitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ConsumingUnitService {

    private final ConsumintUnitRepository consumintUnitRepository;
    private final CommandItemRepository commandItemRepository;

    @Autowired @Lazy
    public ConsumingUnitService(ConsumintUnitRepository consumintUnitRepository, CommandItemRepository commandItemRepository) {
        this.consumintUnitRepository = consumintUnitRepository;
        this.commandItemRepository = commandItemRepository;
    }

    public Optional<ConsumingUnit> getConsumingUnitById(Long id){
        return consumintUnitRepository.findById(id)
            .map(consumingUnit -> {
                consumingUnit.setCommandItems(commandItemRepository.findByConsumingUnit(consumingUnit));
                return consumingUnit;
            });
    }
}
