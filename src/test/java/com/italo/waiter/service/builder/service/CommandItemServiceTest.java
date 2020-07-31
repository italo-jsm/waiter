package com.italo.waiter.service.builder.service;

import com.italo.waiter.model.CommandItem;
import com.italo.waiter.repository.CommandItemRepository;
import com.italo.waiter.repository.ConsumintUnitRepository;
import com.italo.waiter.service.CommandItemService;
import com.italo.waiter.service.builder.ConsumingUnitServiceTestBuilder;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;

@RunWith(SpringRunner.class)
public class CommandItemServiceTest {

    @Mock
    private CommandItemRepository commandItemRepository;

    @Mock
    private ConsumintUnitRepository consumintUnitRepository;

    @InjectMocks
    @Spy
    private CommandItemService commandItemService;

    @Test
    public void addCommandItemToConsumingUnitShouldReturnCommandItem(){
        Mockito.when(consumintUnitRepository.findById(any())).thenReturn(Optional.of(ConsumingUnitServiceTestBuilder.generateConsumingUnit()));
        Mockito.when(commandItemRepository.save(any())).thenReturn(ConsumingUnitServiceTestBuilder.generateCommandItem());
        Optional<CommandItem> commandItem = commandItemService.addCommandItemToConsumingUnit(1L, ConsumingUnitServiceTestBuilder.generateCommandItem());
        Assert.assertTrue(commandItem.isPresent());
    }

    @Test
    public void addCommandItemToConsumingUnitShouldThrowEntityNotFound(){
        Mockito.when(consumintUnitRepository.findById(any())).thenReturn(Optional.empty());
        try{
            commandItemService.addCommandItemToConsumingUnit(1L, ConsumingUnitServiceTestBuilder.generateCommandItem());
            Assert.fail("Should throw exception");
        }catch (Exception e){
            Assert.assertTrue(e instanceof EntityNotFoundException);
        }
    }
}
