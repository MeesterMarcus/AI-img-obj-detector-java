package com.marcuslorenzana.imageobjectdetector.models;

import java.util.List;

public class ImageMetadata {
    String imageSource;
    List<String> objects;
    String label;
    Boolean enableObjectDetection;
    Boolean dryRun;

    public String getImageSource() {
        return imageSource;
    }

    public void setImageSource(String imageSource) {
        this.imageSource = imageSource;
    }


    public List<String> getObjects() {
        return objects;
    }

    public void setObjects(List<String> objects) {
        this.objects = objects;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Boolean getEnableObjectDetection() {
        return enableObjectDetection;
    }

    public void setEnableObjectDetection(Boolean enableObjectDetection) {
        this.enableObjectDetection = enableObjectDetection;
    }

    public Boolean getDryRun() {
        return dryRun;
    }

    public void setDryRun(Boolean dryRun) {
        this.dryRun = dryRun;
    }

    @Override
    public String toString() {
        return "ImageMetadata{" +
                "imageSource='" + imageSource + '\'' +
                ", objects=" + objects +
                ", label='" + label + '\'' +
                ", enableObjectDetection=" + enableObjectDetection +
                ", dryRun=" + dryRun +
                '}';
    }
}
