package com.marcuslorenzana.imageobjectdetector.models;

import com.marcuslorenzana.imageobjectdetector.entities.ObjectEntity;

import java.util.List;

public class ImageMetadataModel {

    private Long id;
    private String imageSource;
    private String label;

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
        return "ImageMetadataModel{" +
                "id=" + id +
                ", imageSource='" + imageSource + '\'' +
                ", label='" + label + '\'' +
                ", objects=" + objects +
                '}';
    }
}
