package com.app.nearme.repository;

import com.app.nearme.model.Place;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PlaceRepository extends JpaRepository<Place, Long> {

    Page<Place> findByTitleContainingIgnoreCase(String title, Pageable pageable);
    Page<Place> findByTagsIn(List<String> tags, Pageable pageable);
    Page<Place> findByTitleContainingIgnoreCaseAndTagsIn(String title, List<String> tags, Pageable pageable);
}

