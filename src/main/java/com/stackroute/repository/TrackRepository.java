package com.stackroute.repository;

import com.stackroute.domain.Track;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TrackRepository extends CrudRepository<Track, Integer> {

    Track save(Track track);

    List<Track> findAll();

    Track findById(int id);

    boolean existsByName(String name);

    @Query(value = "select * from Track t where t.name=?1", nativeQuery = true)
    Track trackByName(String name);
}
