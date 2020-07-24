package com.italo.waiter.repository;

import com.italo.waiter.model.ConsumingUnit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsumintUnitRepository extends JpaRepository<ConsumingUnit, Long> {
}
