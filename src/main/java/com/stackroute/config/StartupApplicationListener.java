package com.stackroute.config;

import com.stackroute.domain.Track;
import com.stackroute.repository.TrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
@PropertySource(value = "startup.properties")
public class StartupApplicationListener implements ApplicationListener<ContextRefreshedEvent> {

    private TrackRepository trackRepository;

    @Autowired
    public StartupApplicationListener(TrackRepository trackRepository) {
        this.trackRepository = trackRepository;
    }

    @Value("${firstName}")
    private String name1;

    @Value("${firstComment}")
    private String comment1;

    @Autowired
    private Environment environment;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        Track track = new Track();
        track.setName(name1);
        track.setComment(comment1);
        trackRepository.save(track);

        track = new Track();
        track.setName(environment.getProperty("secondName"));
        track.setComment(environment.getProperty("secondComment"));
        trackRepository.save(track);
    }
}
