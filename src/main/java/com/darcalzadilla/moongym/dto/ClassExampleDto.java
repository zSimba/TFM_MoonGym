package com.darcalzadilla.moongym.dto;

public class ClassExampleDto {
    private String name;
    private String description;
    private String imageUrl;
    private int intensityLevel;

    public ClassExampleDto(String name, String description, String imageUrl, int intensityLevel) {
        this.name = name;
        this.description = description;
        this.imageUrl = imageUrl;
        this.intensityLevel = intensityLevel;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getIntensityLevel() {
        return intensityLevel;
    }

    public void setIntensityLevel(int intensityLevel) {
        this.intensityLevel = intensityLevel;
    }
}
