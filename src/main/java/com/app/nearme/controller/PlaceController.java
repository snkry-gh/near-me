package com.app.nearme.controller;

import com.app.nearme.dto.RequestDTO;
import com.app.nearme.dto.ResponseDTO;
import com.app.nearme.service.PlaceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/nearme")
@Tag(name = "Place Search", description = "Endpoints for finding nearby places")
public class PlaceController {

    @Autowired
    private PlaceService placeService;

    @PostMapping("/search")
    @Operation(
            summary = "Search places near user location",
            description = "Returns a list of places filtered by title and/or tags, sorted by Haversine distance."
    )
    public ResponseEntity<Page<ResponseDTO>> search(@RequestBody RequestDTO request) {
        Page<ResponseDTO> results = placeService.search(request);
        return ResponseEntity.ok(results);
    }
}
