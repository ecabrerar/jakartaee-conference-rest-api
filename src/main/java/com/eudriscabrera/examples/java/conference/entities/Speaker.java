package com.eudriscabrera.examples.java.conference.entities;


import jakarta.json.bind.annotation.JsonbCreator;
import jakarta.json.bind.annotation.JsonbProperty;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author eudriscabrera
 *
 */
@Entity
@Table(name = "speakers")
public class Speaker implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String name;
    private String organization;

    private String title;
    private String country;
    private String shortBio;
    private Boolean accepted;

    public Speaker() {
    }

    @JsonbCreator
    public Speaker(@JsonbProperty("name") String name,
                   @JsonbProperty("organization") String organization,
                   @JsonbProperty("title") String title,
                   @JsonbProperty("country") String country,
                   @JsonbProperty("shortBio") String shortBio) {
        this.name = name;
        this.organization = organization;
        this.title = title;
        this.country = country;
        this.shortBio = shortBio;
        this.accepted = false;
    }

    public String getId() {
        return id;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getShortBio() {
        return shortBio;
    }

    public void setShortBio(String shortBio) {
        this.shortBio = shortBio;
    }

    public Boolean isAccepted() {
        return accepted;
    }

    public void setAccepted(Boolean accepted) {
        this.accepted = accepted;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Speaker speaker = (Speaker) o;
        return Objects.equals(id, speaker.id) && Objects.equals(name, speaker.name) && Objects.equals(organization, speaker.organization) && Objects.equals(title, speaker.title) && Objects.equals(country, speaker.country) && Objects.equals(shortBio, speaker.shortBio) && Objects.equals(accepted, speaker.accepted);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, organization, title, country, shortBio, accepted);
    }
}
