package com.italo.waiter.repository;

import com.italo.waiter.model.ConsumingUnit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.stream.DoubleStream;

@Repository
public interface ConsumingUnitRepository extends JpaRepository<ConsumingUnit, Long> {
    Optional<ConsumingUnit> findByNumber(Integer consumingUnitNumber);
}
