package com.app.nearme.service;

import com.app.nearme.dao.PlaceDAO;
import com.app.nearme.dto.ResponseDTO;
import com.app.nearme.dto.RequestDTO;
import com.app.nearme.exception.InvalidSearchException;
import com.app.nearme.model.Place;
import com.app.nearme.util.DistanceCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class PlaceService {

    @Autowired
    private PlaceDAO placeDao;

    public Page<ResponseDTO> search(RequestDTO request) {
        String title = request.getTitle();
        List<String> tags = request.getTags();

        double latitude = request.getLatitude();
        double longitude = request.getLongitude();

        int page = request.getPage();
        int size = request.getSize();

        Pageable pageable = PageRequest.of(page, size);

        Page<Place> places;

        if (title != null && !title.isBlank()) {
            if (tags != null && !tags.isEmpty()) {
                places = placeDao.findByTitleAndTags(title, tags, pageable);
            } else {
                places = placeDao.findByTitle(title, pageable);
            }
        } else if (tags != null && !tags.isEmpty()) {
            places = placeDao.findByTags(tags, pageable);
        } else {
            throw new InvalidSearchException("Title/Tags : NULL");
        }

        List<ResponseDTO> response = places.getContent().stream()
                .map(place -> {
                    double distance = DistanceCalculator.calculate(
                            latitude, longitude, place.getLatitude(), place.getLongitude()
                    );
                    return ResponseDTO.builder()
                            .id(place.getId())
                            .title(place.getTitle())
                            .latitude(place.getLatitude())
                            .longitude(place.getLongitude())
                            .tags(place.getTags())
                            .distance(distance)
                            .build();
                })
                .sorted(Comparator.comparingDouble(ResponseDTO::getDistance))
                .collect(Collectors.toList());

        return new PageImpl<>(response, pageable, places.getTotalElements());
    }
}
