package org.fireplume.service;

import ma.glasnost.orika.MapperFacade;
import org.fireplume.dto.FirstDto;
import org.fireplume.dto.NestedDto;
import org.fireplume.dto.SecondDto;
import org.fireplume.mapper.mapstruct.MapStructFirstDtoToSecondDtoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SomeService {

    @Autowired
    private MapperFacade mapperFacade;

    @Autowired
    private MapStructFirstDtoToSecondDtoMapper mapStructFirstDtoToSecondDtoMapper;

    public void run1() {
        final FirstDto firstDto = new FirstDto("1", 2, new NestedDto("3", 4));
        final SecondDto secondDto = mapperFacade.map(firstDto, SecondDto.class);

        System.out.println(firstDto.toString());
        System.out.println(secondDto.toString());
    }

    public void run2() {
        final FirstDto firstDto = new FirstDto("1", 2, new NestedDto("3", 4));
        final SecondDto secondDto = mapStructFirstDtoToSecondDtoMapper.map(firstDto);

        System.out.println(firstDto.toString());
        System.out.println(secondDto.toString());
    }
}
