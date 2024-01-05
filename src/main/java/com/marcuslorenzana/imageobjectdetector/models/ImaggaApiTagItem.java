package com.marcuslorenzana.imageobjectdetector.models;

public class ImaggaApiTagItem {
    private double confidence;
    private ImaggaApiTag tag;

    public double getConfidence() {
        return confidence;
    }

    public void setConfidence(double confidence) {
        this.confidence = confidence;
    }

    public ImaggaApiTag getTag() {
        return tag;
    }

    public void setTag(ImaggaApiTag tag) {
        this.tag = tag;
    }

    @Override
    public String toString() {
        return "ImaggaApiTagItem{" +
                "confidence=" + confidence +
                ", tag=" + tag +
                '}';
    }
}
