package com.italo.waiter.service;

import com.italo.waiter.model.CommandItem;
import com.italo.waiter.model.ConsumingUnit;
import com.italo.waiter.repository.CommandItemRepository;
import com.italo.waiter.repository.ConsumingUnitRepository;
import com.italo.waiter.service.builder.ConsumingUnitServiceTestBuilder;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;

@RunWith(SpringRunner.class)
public class CommandItemServiceTest {

    @Mock
    private CommandItemRepository commandItemRepository;

    @Mock
    private ConsumingUnitRepository consumingUnitRepository;

    @InjectMocks
    private CommandItemService commandItemService;

    @Test
    public void addCommandItemToConsumingUnitShouldReturnCommandItem(){
        Mockito.when(consumingUnitRepository.findByNumber(any())).thenReturn(Optional.of(ConsumingUnitServiceTestBuilder.generateConsumingUnit()));
        Mockito.when(commandItemRepository.save(any())).thenReturn(ConsumingUnitServiceTestBuilder.generateCommandItem());
        Optional<CommandItem> commandItem = commandItemService.addCommandItemToConsumingUnit(1, ConsumingUnitServiceTestBuilder.generateCommandItem());
        Assert.assertTrue(commandItem.isPresent());
        ArgumentCaptor<CommandItem> commandItemArgumentCaptor = ArgumentCaptor.forClass(CommandItem.class);
        Mockito.verify(commandItemRepository).save(commandItemArgumentCaptor.capture());
        CommandItem capturedCommandItem = commandItemArgumentCaptor.getValue();
        Assert.assertEquals(2, (int) capturedCommandItem.getConsumingUnit().getNumber());
        Assert.assertEquals(2, (int) capturedCommandItem.getConsumingUnit().getPeoples());
    }

    @Test
    public void addCommandItemToConsumingUnitShouldThrowEntityNotFound(){
        Mockito.when(consumingUnitRepository.findById(any())).thenReturn(Optional.empty());
        try{
            commandItemService.addCommandItemToConsumingUnit(1, ConsumingUnitServiceTestBuilder.generateCommandItem());
            Assert.fail("Should throw exception");
        }catch (Exception e){
            Assert.assertTrue(e instanceof EntityNotFoundException);
        }
    }

    @Test
    public void shouldRemoveCommandItemFromConsumingUnit(){
        Mockito.when(consumingUnitRepository.findById(any())).thenReturn(Optional.of(ConsumingUnitServiceTestBuilder.generateConsumingUnit()));
        Mockito.when(commandItemRepository.save(any())).thenReturn(ConsumingUnitServiceTestBuilder.generateCommandItem());
        Optional <CommandItem> commandItem = commandItemService.removeCommandItemFromConsumingUnit(1L, ConsumingUnitServiceTestBuilder.generateCommandItem());
        Assert.assertTrue(commandItem.isPresent());
        ArgumentCaptor<CommandItem> commandItemArgumentCaptor = ArgumentCaptor.forClass(CommandItem.class);
        Mockito.verify(commandItemRepository).save(commandItemArgumentCaptor.capture());
        CommandItem capturedCommandItem = commandItemArgumentCaptor.getValue();
        Assert.assertNull(capturedCommandItem.getConsumingUnit());
    }

    @Test
    public void shouldSetConsumingUnitToNull(){
        CommandItem commandItemWithNullConsumingUnit = commandItemService.removeConsumingUnit(ConsumingUnitServiceTestBuilder.generateCommandItem());
        Assert.assertNull(commandItemWithNullConsumingUnit.getConsumingUnit());
    }

    @Test
    public void shouldAddConsumingUnit(){
        CommandItem commandItem = commandItemService.addConsumingUnit(ConsumingUnitServiceTestBuilder.generateCommandItem(), ConsumingUnitServiceTestBuilder.generateConsumingUnit());
        Assert.assertNotNull(commandItem.getConsumingUnit());
    }

    @Test
    public void shouldThrowExceptionWhenCommandItemIsNotFromConsumingUnit(){
        Mockito.when(consumingUnitRepository.findById(any())).thenReturn(Optional.of(new ConsumingUnit()));
        Mockito.when(commandItemRepository.save(any())).thenReturn(ConsumingUnitServiceTestBuilder.generateCommandItem());
        try{
            Optional <CommandItem> commandItem = commandItemService.removeCommandItemFromConsumingUnit(1L, ConsumingUnitServiceTestBuilder.generateCommandItem());
            Assert.fail("Should not get here");
        }catch (Exception e){
            Assert.assertTrue(e instanceof RuntimeException);
        }
    }
}
