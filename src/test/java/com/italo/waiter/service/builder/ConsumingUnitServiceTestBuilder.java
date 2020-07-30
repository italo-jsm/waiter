package com.italo.waiter.service.builder;

import com.italo.waiter.model.CommandItem;
import com.italo.waiter.model.ConsumingUnit;
import com.italo.waiter.model.Product;
import com.italo.waiter.model.dto.ConsumingUnitDto;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class ConsumingUnitServiceTestBuilder {
    public static ConsumingUnit generateConsumingUnit() {
        ConsumingUnit unit = new ConsumingUnit();
        unit.setNumber(2);
        unit.setPeoples(2);
        return unit;
    }

    public static List<CommandItem> generateCommandItens() {
        CommandItem commandItem = new CommandItem();
        commandItem.setQuantity(3);
        commandItem.setProduct(generateProduct());
        return Collections.singletonList(commandItem);
    }

    private static Product generateProduct() {
        Product product = new Product();
        product.setDescription("Description");
        product.setCode("code");
        return product;
    }

    public static ConsumingUnitDto generateConsumingUnitDto() {
        ConsumingUnitDto consumingUnitDto = new ConsumingUnitDto();
        consumingUnitDto.setNumber(1);
        consumingUnitDto.setPeople(2);
        return consumingUnitDto;
    }
}
