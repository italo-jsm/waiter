package com.italo.waiter.service.builder;

import com.italo.waiter.model.CommandItem;
import com.italo.waiter.model.ConsumingUnit;
import com.italo.waiter.model.Product;
import com.italo.waiter.model.dto.ConsumingUnitDto;
import com.italo.waiter.utils.enums.UnitStatus;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

public class ConsumingUnitServiceTestBuilder {
    public static ConsumingUnit generateConsumingUnit() {
        ConsumingUnit unit = new ConsumingUnit();
        unit.setStatus(UnitStatus.CLOSED);
        unit.setNumber(2);
        unit.setPeoples(2);
        return unit;
    }

    public static CommandItem generateCommandItem() {
        CommandItem commandItem = new CommandItem();
        commandItem.setQuantity(3);
        commandItem.setProduct(generateProduct());
        commandItem.setConsumingUnit(generateConsumingUnit());
        return commandItem;
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

    public static List<ConsumingUnit> generateConsumingUnitList(){
        return Arrays.asList(new ConsumingUnit(), new ConsumingUnit(), new ConsumingUnit());
    }
}
