package ru.honorzor.manifestservice.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class CellDTO {
    private Long id;

    private Long count;

    private Long code;
}
