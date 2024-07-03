package com.datum.services.ratingservice.reviews.dtos;

import com.datum.services.ratingservice.core.BaseDto;
import lombok.Data;

@Data
public class ClientDto extends BaseDto<Integer> {
    private String name;
    private String email;
}
