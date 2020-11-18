package com.italo.waiter.service;

import com.italo.waiter.model.ConsumingUnit;
import com.italo.waiter.model.SystemUser;
import com.italo.waiter.model.dto.ConsumingUnitDto;
import com.italo.waiter.repository.CommandItemRepository;
import com.italo.waiter.repository.ConsumingUnitRepository;
import com.italo.waiter.repository.SystemUserRepository;
import com.italo.waiter.utils.enums.UnitStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ConsumingUnitService {

    private final ConsumingUnitRepository consumingUnitRepository;
    private final CommandItemRepository commandItemRepository;

    @Autowired @Lazy
    public ConsumingUnitService(ConsumingUnitRepository consumingUnitRepository, CommandItemRepository commandItemRepository, SystemUserRepository systemUserRepository) {
        this.consumingUnitRepository = consumingUnitRepository;
        this.commandItemRepository = commandItemRepository;
    }

    public Optional<ConsumingUnit> getConsumingUnitById(Long id){
        return consumingUnitRepository.findById(id)
            .map(consumingUnit -> {
                consumingUnit.setCommandItems(commandItemRepository.findByConsumingUnit(consumingUnit));
                return consumingUnit;
            });
    }

    public Optional<ConsumingUnit> openConsumingUnit(Long consumingUnitId, SystemUser systemUser){
        return consumingUnitRepository.findById(consumingUnitId)
                .flatMap(consumingUnit -> {
                    consumingUnit.setStatus(UnitStatus.OPENED);
                    return Optional.of(consumingUnit);
                });
    }

    public Long createConsumingUnit(ConsumingUnitDto consumingUnitDto) {
        return consumingUnitRepository.save(consumingUnitDto.toConsumingUnit()).getId();
    }

    public List<ConsumingUnit> getAllConsumingUnits(){
        return consumingUnitRepository.findAll();
    }
}
