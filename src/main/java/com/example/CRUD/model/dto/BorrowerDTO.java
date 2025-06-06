package com.example.CRUD.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Data
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BorrowerDTO {
    private int borrowerId;
    private String name;

}
