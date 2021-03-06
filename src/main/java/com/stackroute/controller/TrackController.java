package com.stackroute.controller;

import com.stackroute.domain.Track;
import com.stackroute.exceptions.NoTracksExists;
import com.stackroute.exceptions.SameCommentExists;
import com.stackroute.exceptions.TrackAlreadyExists;
import com.stackroute.exceptions.TracksNotAvailable;
import com.stackroute.service.TrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v2")
public class TrackController {

    private TrackService trackService;

    @Autowired
    public TrackController(TrackService trackService) {
        this.trackService = trackService;
    }


    @PostMapping("track")
    public ResponseEntity<?> saveTrack(@RequestBody Track track){
        ResponseEntity responseEntity;
        try{
            Track track1 = trackService.saveTrack(track);
            responseEntity = new ResponseEntity<Track>(track1 , HttpStatus.OK);
        }
        catch (TrackAlreadyExists ex){
            responseEntity = new ResponseEntity<String>(ex.getMessage() , HttpStatus.CONFLICT);
        }
        return responseEntity;
    }

    @PutMapping("track/{id}")
    public ResponseEntity<?> updateTrack(@PathVariable("id") int id, @RequestBody String comment){
        ResponseEntity responseEntity;
        try{
            Track updatedTrack = trackService.updateTrack(id, comment);
            responseEntity = new ResponseEntity<Track>(updatedTrack , HttpStatus.OK);
        }
        catch (SameCommentExists ex){
            responseEntity = new ResponseEntity<String>(ex.getMessage() , HttpStatus.CONFLICT);
        }
        return responseEntity;
    }

    @DeleteMapping("track/{id}")
    public ResponseEntity<?> deleteTrack(@PathVariable("id") int id){
        ResponseEntity responseEntity;
        try{
            Track track = trackService.deleteTrack(id);
            responseEntity = new ResponseEntity<Track>(track, HttpStatus.OK);
        }
        catch (NoTracksExists ex){
            responseEntity = new ResponseEntity<String>(ex.getMessage() , HttpStatus.NOT_FOUND);
        }
        return responseEntity;
    }

    @GetMapping("tracks")
    public ResponseEntity<?> listOfUsers() {
        ResponseEntity<?> listResponseEntity;
        try{
            List<Track> allUsers = trackService.getAllTracks();
            listResponseEntity = new ResponseEntity<List<Track>>(allUsers, HttpStatus.OK);
        }
        catch (TracksNotAvailable ex){
            listResponseEntity = new ResponseEntity<String>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
        return listResponseEntity;
    }

    @GetMapping("track/{name}")
    public ResponseEntity<?> trackName(@PathVariable("name") String name){
        ResponseEntity responseEntity;
        try{
            Track track = trackService.trackByName(name);
            responseEntity = new ResponseEntity<Track>(track,HttpStatus.OK);
        }
        catch (NoTracksExists ex){
            responseEntity = new ResponseEntity<String>(ex.getMessage(),HttpStatus.NOT_FOUND);
        }
        return responseEntity;
    }
}
