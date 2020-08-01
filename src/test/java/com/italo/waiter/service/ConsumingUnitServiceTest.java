package com.italo.waiter.service;

import com.italo.waiter.model.ConsumingUnit;
import com.italo.waiter.repository.CommandItemRepository;
import com.italo.waiter.repository.ConsumintUnitRepository;
import com.italo.waiter.service.ConsumingUnitService;
import com.italo.waiter.service.builder.ConsumingUnitServiceTestBuilder;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;


@RunWith(SpringRunner.class)
public class ConsumingUnitServiceTest {

    @Mock
    ConsumintUnitRepository consumintUnitRepository;

    @Mock
    CommandItemRepository commandItemRepository;

    @InjectMocks
    ConsumingUnitService consumingUnitService;

    @Test
    public void getConsumingUnitByIdShouldReturnConsumingUnit(){
        Mockito.doReturn(Optional.of(ConsumingUnitServiceTestBuilder.generateConsumingUnit())).when(consumintUnitRepository).findById(any());
        Mockito.when(commandItemRepository.findByConsumingUnit(any())).thenReturn(Collections.singletonList(ConsumingUnitServiceTestBuilder.generateCommandItem()));
        Optional<ConsumingUnit> consumingUnitById = consumingUnitService.getConsumingUnitById(1L);
        Assert.assertTrue(consumingUnitById.isPresent());
    }
}