package com.harvest.infrastructure.services.mappers;

public interface IMapper<T, TDto> {
    T mapFrom(TDto dto);
}
