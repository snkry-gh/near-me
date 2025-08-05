package com.app.nearme.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Response payload for place search")
public class ResponseDTO {
    private Long id;
    private String title;
    private double latitude;
    private double longitude;
    private List<String> tags;
    private double distance;
}

