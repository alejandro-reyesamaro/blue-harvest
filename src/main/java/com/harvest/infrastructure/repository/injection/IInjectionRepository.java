package com.harvest.infrastructure.repository.injection;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IInjectionRepository extends JpaRepository<InjectionDto, Long> {
    
}
