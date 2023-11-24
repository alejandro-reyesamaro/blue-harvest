package com.harvest.infrastructure.repository;

import com.harvest.infrastructure.repository.dto.CostumerDto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICostumerRepository extends JpaRepository<CostumerDto, Long>{
    
}
