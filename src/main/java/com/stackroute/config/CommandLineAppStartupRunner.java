package com.stackroute.config;

import com.stackroute.domain.Track;
import com.stackroute.repository.TrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CommandLineAppStartupRunner implements CommandLineRunner {

    private TrackRepository trackRepository;

    @Autowired
    public CommandLineAppStartupRunner(TrackRepository trackRepository) {
        this.trackRepository = trackRepository;
    }
    /*Similar to CommandLineRunner, Spring boot also provides an ApplicationRunner interface with a run() method
    to be invoked at application startup.
    However, instead of raw String arguments passed to the callback method,
    we have an instance of the ApplicationArguments class.

    The ApplicationArguments interface has methods to get argument values that are options and plain argument values.
    An argument that is prefixed with – – is an option argument.*/

    @Override
    public void run(String... args) throws Exception {
        Track track = new Track();
        track.setName("Gully Boy");
        track.setComment("First Song as seed from CommandLineRunner");
        trackRepository.save(track);

        track = new Track();
        track.setName("Dil Dhadkane Do");
        track.setComment("Second Song as seed from CommandLineRunner");
        trackRepository.save(track);
    }
}
