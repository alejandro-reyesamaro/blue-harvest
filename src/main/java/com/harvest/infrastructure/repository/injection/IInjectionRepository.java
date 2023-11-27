package com.harvest.infrastructure.repository.injection;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IInjectionRepository extends JpaRepository<InjectionDto, Long> {
    List<InjectionDto> findByCostumerId(int id);
}
