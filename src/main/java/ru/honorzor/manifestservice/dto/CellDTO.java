package ru.honorzor.manifestservice.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class CellDTO {
    private Long cell_id;

    private Long count;

    private Long code;
}
