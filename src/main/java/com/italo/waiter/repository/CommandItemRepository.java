package com.italo.waiter.repository;

import com.italo.waiter.model.CommandItem;
import com.italo.waiter.model.ConsumingUnit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommandItemRepository extends JpaRepository<CommandItem, Long> {
    public List<CommandItem> findByConsumingUnit(ConsumingUnit consumingUnit);
}
