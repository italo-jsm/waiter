package com.italo.waiter.service;

import com.italo.waiter.model.ConsumingUnit;
import com.italo.waiter.model.SystemUser;
import com.italo.waiter.repository.CommandItemRepository;
import com.italo.waiter.repository.ConsumingUnitRepository;
import com.italo.waiter.service.builder.ConsumingUnitServiceTestBuilder;
import com.italo.waiter.utils.enums.UnitStatus;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;


@RunWith(SpringRunner.class)
public class ConsumingUnitServiceTest {

    @Mock
    ConsumingUnitRepository consumingUnitRepository;

    @Mock
    CommandItemRepository commandItemRepository;

    @InjectMocks
    ConsumingUnitService consumingUnitService;

    @Test
    public void getConsumingUnitByIdShouldReturnConsumingUnit(){
        Mockito.doReturn(Optional.of(ConsumingUnitServiceTestBuilder.generateConsumingUnit())).when(consumingUnitRepository).findById(any());
        Mockito.when(commandItemRepository.findByConsumingUnit(any())).thenReturn(Collections.singletonList(ConsumingUnitServiceTestBuilder.generateCommandItem()));
        Optional<ConsumingUnit> consumingUnitById = consumingUnitService.getConsumingUnitById(1L);
        Assert.assertTrue(consumingUnitById.isPresent());
    }

    @Test
    public void shouldOpenConsumingUnit(){
        Mockito.doReturn(Optional.of(ConsumingUnitServiceTestBuilder.generateConsumingUnit())).when(consumingUnitRepository).findById(any());
        final Optional<ConsumingUnit> consumingUnit = consumingUnitService.openConsumingUnit(1L, new SystemUser());
        Assert.assertTrue(consumingUnit.isPresent());
        Assert.assertEquals(consumingUnit.get().getStatus(), UnitStatus.OPENED);
    }

    @Test
    public void shouldCreateConsumingUnit(){
        ConsumingUnit consumingUnit = new ConsumingUnit();
        ReflectionTestUtils.setField(consumingUnit, "id", 1L);
        Mockito.doReturn(consumingUnit).when(consumingUnitRepository).save(any());
        Long consumingUnitId = consumingUnitService.createConsumingUnit(ConsumingUnitServiceTestBuilder.generateConsumingUnitDto());
        Assert.assertEquals(consumingUnitId, Long.valueOf(1L));
    }

    @Test
    public void shouldGetAllConsumingUnits(){
        Mockito.doReturn(ConsumingUnitServiceTestBuilder.generateConsumingUnitList()).when(consumingUnitRepository).findAll();
        final List<ConsumingUnit> allConsumingUnits = consumingUnitService.getAllConsumingUnits();
        Assert.assertFalse(allConsumingUnits.isEmpty());
        Assert.assertEquals(allConsumingUnits.size(), 3);
    }
}
