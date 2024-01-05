package com.marcuslorenzana.imageobjectdetector.models;

import java.util.List;

public class ImaggaApiResult {
    private List<ImaggaApiTagItem> tags;

    public List<ImaggaApiTagItem> getTags() {
        return tags;
    }

    public void setTags(List<ImaggaApiTagItem> tags) {
        this.tags = tags;
    }

    @Override
    public String toString() {
        return "ImaggaApiResult{" +
                "tags=" + tags +
                '}';
    }
}
