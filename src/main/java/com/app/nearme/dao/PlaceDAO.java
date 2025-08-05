package com.app.nearme.dao;

import com.app.nearme.model.Place;
import com.app.nearme.repository.PlaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PlaceDAO {

    @Autowired
    private PlaceRepository placeRepository;

    public Page<Place> findByTitle(String title, Pageable pageable) {
        return placeRepository.findByTitleContainingIgnoreCase(title, pageable);
    }

    public Page<Place> findByTags(List<String> tags, Pageable pageable) {
        return placeRepository.findByTagsIn(tags, pageable);
    }

    public Page<Place> findByTitleAndTags(String title, List<String> tags, Pageable pageable) {
        return placeRepository.findByTitleContainingIgnoreCaseAndTagsIn(title, tags, pageable);
    }
}
