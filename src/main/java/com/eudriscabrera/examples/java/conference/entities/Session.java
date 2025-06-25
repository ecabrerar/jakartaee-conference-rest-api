package com.eudriscabrera.examples.java.conference.entities;

import jakarta.json.bind.annotation.JsonbCreator;
import jakarta.json.bind.annotation.JsonbProperty;
import jakarta.persistence.*;

import java.io.Serializable;
import java.time.Duration;
import java.util.Objects;

/**
 * @author eudriscabrera
 *
 */
@Entity
@Table(name = "sessions")
public class Session  implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String title;
    private Duration duration;

    @ManyToOne
    @JoinColumn(name = "speaker_id")
    private Speaker speaker;
    private Boolean featured;

    public Session() {
    }

    @JsonbCreator
    public Session(@JsonbProperty("title") String title,
                   @JsonbProperty("duration") Duration duration,
                   @JsonbProperty("speaker") Speaker speaker ) {
        this.title = title;
        this.duration = duration;
        this.speaker = speaker;
        this.featured = false;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Duration getDuration() {
        return duration;
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }

    public Speaker getSpeaker() {
        return speaker;
    }

    public void setSpeaker(Speaker speaker) {
        this.speaker = speaker;
    }

    public void setFeatured(Boolean featured) {
        this.featured = featured;
    }

    public Boolean isFeatured() {
        return featured;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Session session = (Session) o;
        return Objects.equals(id, session.id) && Objects.equals(title, session.title) && Objects.equals(duration, session.duration) && Objects.equals(speaker, session.speaker) && Objects.equals(featured, session.featured);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, duration, speaker, featured);
    }
}
