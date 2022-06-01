package ru.honorzor.manifestservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class ProductDTO {
    @JsonProperty("cell_id")
    private Long cellId;

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    @JsonProperty("code")
    private Long code;

    @JsonProperty("count")
    private Long count;
}
