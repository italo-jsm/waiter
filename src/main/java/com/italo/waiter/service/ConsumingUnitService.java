package com.italo.waiter.service;

import com.italo.waiter.model.ConsumingUnit;
import com.italo.waiter.model.SystemUser;
import com.italo.waiter.repository.CommandItemRepository;
import com.italo.waiter.repository.ConsumintUnitRepository;
import com.italo.waiter.repository.SystemUserRepository;
import com.italo.waiter.utils.enums.UnitStatus;
import com.italo.waiter.utils.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ConsumingUnitService {

    private final ConsumintUnitRepository consumintUnitRepository;
    private final CommandItemRepository commandItemRepository;

    @Autowired @Lazy
    public ConsumingUnitService(ConsumintUnitRepository consumintUnitRepository, CommandItemRepository commandItemRepository, SystemUserRepository systemUserRepository) {
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

    public Optional<ConsumingUnit> openConsumingUnit(Long consumingUnitId, SystemUser systemUser){
        return consumintUnitRepository.findById(consumingUnitId)
                .map(consumingUnit -> {
                    consumingUnit.setStatus(UnitStatus.OPENED);
                    consumingUnit.setOpenedBy(systemUser);
                    return Optional.of(consumingUnit);
                }).orElseGet(Optional::empty);
    }

    public Long createConsumingUnit(ConsumingUnit consumingUnit) {
        return consumintUnitRepository.save(consumingUnit).getId();
    }

    public List<ConsumingUnit> getAllConsumingUnits(){
        return consumintUnitRepository.findAll();
    }
}
