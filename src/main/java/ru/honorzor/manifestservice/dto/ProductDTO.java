package ru.honorzor.manifestservice.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class ProductDTO {
    private Long cellId;

    private String name;

    private String description;

    private Long code;

    private Long count;
}
