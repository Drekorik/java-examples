package org.fireplume.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class SecondDto {
    private String firstField;
    private Integer secondField;

    private AnotherNestedDto anotherNestedDto;
}
