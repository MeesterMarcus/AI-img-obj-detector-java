package com.marcuslorenzana.imageobjectdetector.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class ImageMetadataEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    private String imageSource;
    private String label;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "image_object_mapping",
            joinColumns = @JoinColumn(name = "image_id"),
            inverseJoinColumns = @JoinColumn(name = "object_id")
    )
    private List<ObjectEntity> objects;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImageSource() {
        return imageSource;
    }

    public void setImageSource(String imageSource) {
        this.imageSource = imageSource;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public List<ObjectEntity> getObjects() {
        return objects;
    }

    public void setObjects(List<ObjectEntity> objects) {
        this.objects = objects;
    }

    @Override
    public String toString() {
        return "ImageMetadataEntity{" +
                "id=" + id +
                ", imageSource='" + imageSource + '\'' +
                ", label='" + label + '\'' +
                ", objects=" + objects +
                '}';
    }
}
