package com.italo.waiter.model.builder;

import com.italo.waiter.model.CommandItem;
import com.italo.waiter.model.ConsumingUnit;
import com.italo.waiter.model.Product;

import java.util.Arrays;

public class ConsumingUnitBuilder {
    public static ConsumingUnit generateConsumingUnit(){
        ConsumingUnit unit = new ConsumingUnit();
        Product p = new Product();
        p.setSaleCost(10.0);
        CommandItem item1 = new CommandItem();
        item1.setProduct(p);
        item1.setQuantity(1);

        CommandItem item2 = new CommandItem();
        item2.setProduct(p);
        item2.setQuantity(2);

        CommandItem item3 = new CommandItem();
        item3.setProduct(p);
        item3.setQuantity(3);

        unit.getCommandItems().addAll(Arrays.asList(item1, item2, item3));

        return unit;
    }
}
