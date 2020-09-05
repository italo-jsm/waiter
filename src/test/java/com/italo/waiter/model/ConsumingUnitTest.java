package com.italo.waiter.model;

import com.italo.waiter.model.builder.ConsumingUnitBuilder;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

@RunWith(SpringRunner.class)
public class ConsumingUnitTest {

    @Test
    public void shouldReturnTotalBill(){
        ConsumingUnit unit = ConsumingUnitBuilder.generateConsumingUnit();
        Optional<Double> total = unit.getCommandItems()
                .stream()
                .map(commandItem -> commandItem.getProduct().getSaleCost() * commandItem.getQuantity())
                .reduce(Double::sum);
        Assert.assertTrue(total.isPresent());
        Assert.assertEquals(total.get(), unit.getTotalBill());
    }
}
