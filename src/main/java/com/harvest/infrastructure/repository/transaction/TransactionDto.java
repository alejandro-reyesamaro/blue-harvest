package com.harvest.infrastructure.repository.transaction;

import com.harvest.infrastructure.repository.injection.InjectionDto;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "`Transaction`")
@Data
@EqualsAndHashCode(callSuper=false)
public class TransactionDto extends InjectionDto {

    @Column(name = "TargetAccountId")
    private int targetAccountId;

    public TransactionDto() {}

    public TransactionDto(int costumerId, int costumerAccountId, int targetAccountId, double amount) {
        super(costumerId, costumerAccountId, amount);
        this.targetAccountId = targetAccountId;
    }

    @Override
    public String toString() {
        return "Transaction [id=" + id + "]";
    }
}
