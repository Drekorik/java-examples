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
public class AnotherNestedDto {
    private String anotherNestedFirstField;
    private Integer anotherNestedSecondField;
}
