package ru.honorzor.manifestservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class OrderDTO {
    @JsonProperty(namespace = "products")
    private List<ProductDTO> products;
}
