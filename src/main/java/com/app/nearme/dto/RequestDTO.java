package com.app.nearme.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Request payload for place search")

public class RequestDTO {
    private double latitude;
    private double longitude;
    private String title;
    private List<String> tags;
    @Builder.Default
    private int page = 0;
    @Builder.Default
    private int size = 10;
}
