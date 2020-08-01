package com.italo.waiter.controllers;

import com.italo.waiter.controllers.CommandItemController;
import com.italo.waiter.service.builder.ConsumingUnitServiceTestBuilder;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.ArgumentMatchers.any;

@RunWith(SpringRunner.class)
public class CommandItemControllerTest {

    @Mock
    private CommandItemController commandItemController;

    @Test
    public void shouldAddCommandItemToConsumingUnit(){

    }
}
