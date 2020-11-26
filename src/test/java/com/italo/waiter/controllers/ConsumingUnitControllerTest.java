package com.italo.waiter.controllers;

import com.italo.waiter.service.ConsumingUnitService;
import com.italo.waiter.service.builder.ConsumingUnitServiceTestBuilder;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.URISyntaxException;
import java.util.Collections;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;

@RunWith(SpringRunner.class)
public class ConsumingUnitControllerTest {

    @Mock
    private ConsumingUnitService consumingUnitService;

    @InjectMocks
    @Spy
    private ConsumingUnitController consumingUnitController;


    @Test
    public void testGetByIdShouldReturnOk(){
        Mockito.when(consumingUnitService.getConsumingUnitById(any())).thenReturn(Optional.of(ConsumingUnitServiceTestBuilder.generateConsumingUnit()));
        ResponseEntity<?> oneConsumingUnit = consumingUnitController.getOneConsumingUnit(1L);
        Assert.assertEquals(oneConsumingUnit.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void testGetByIdShouldReturnNotFound(){
        Mockito.when(consumingUnitService.getConsumingUnitById(any())).thenReturn(Optional.empty());
        ResponseEntity<?> oneConsumingUnit = consumingUnitController.getOneConsumingUnit(1L);
        Assert.assertEquals(oneConsumingUnit.getStatusCode(), HttpStatus.NOT_FOUND);
    }

    @Test
    public void testGetAllConsumingUnitShouldReturnOk(){
        Mockito.when(consumingUnitService.getAllConsumingUnits()).thenReturn(Collections.singletonList(ConsumingUnitServiceTestBuilder.generateConsumingUnit()));
        ResponseEntity<?> allConsumingUnit = consumingUnitController.getAllConsumingUnit();
        Assert.assertEquals(allConsumingUnit.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void testCreateConsumingUnitShouldReturnOk() throws URISyntaxException {
        Mockito.when(consumingUnitService.createConsumingUnit(any())).thenReturn(1L);
        ResponseEntity<?> consumingUnit = consumingUnitController.createConsumingUnit(ConsumingUnitServiceTestBuilder.generateConsumingUnitDto());
        Assert.assertEquals(consumingUnit.getStatusCode(), HttpStatus.CREATED);
    }

    @Test
    public void testCreateConsumingUnitShouldThrowURIException() throws URISyntaxException {
        Mockito.doThrow(new URISyntaxException("s1", "s2")).when(consumingUnitController).createConsumingUnit(any());
        try{
            consumingUnitController.createConsumingUnit(ConsumingUnitServiceTestBuilder.generateConsumingUnitDto());
            Assert.fail("Should throw exception");
        }catch (Exception ex){
            Assert.assertTrue(ex instanceof URISyntaxException);
        }
    }
}
