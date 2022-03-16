package ru.honorzor.manifestservice.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDTO {
    public String message;
    private int code;
}
