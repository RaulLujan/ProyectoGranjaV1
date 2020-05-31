package com.mygdx.game.Dominio;

import java.util.GregorianCalendar;

public class Campo extends Infraestructura {

    private Integer plantedResourceType;
    private boolean planted, needsWater, needsManure, needsHerbizide;
    private GregorianCalendar timeFieldWasPlanted;
    int stage;

    public Campo(){
        this.planted = false;
        this.needsHerbizide = false;
        this.needsManure = false;
        this.needsWater = false;
        this.stage = 0;

    }

    //GETTERS & SETTERS
    public boolean isPlanted() {
        return planted;
    }

    public void setPlanted(boolean planted) {
        this.planted = planted;
    }

    public Integer getPlantedResourceType() {
        return plantedResourceType;
    }

    public void setPlantedResourceType(Integer plantedResourceType) {
        this.plantedResourceType = plantedResourceType;
    }

    public boolean isNeedsWater() {
        return needsWater;
    }

    public void setNeedsWater(boolean needsWater) {
        this.needsWater = needsWater;
    }

    public boolean isNeedsManure() {
        return needsManure;
    }

    public void setNeedsManure(boolean needsManure) {
        this.needsManure = needsManure;
    }

    public boolean isNeedsHerbizide() {
        return needsHerbizide;
    }

    public void setNeedsHerbizide(boolean needsHerbizide) {
        this.needsHerbizide = needsHerbizide;
    }

    public GregorianCalendar getTimeFieldWasPlanted() {
        return timeFieldWasPlanted;
    }

    public void setTimeFieldWasPlanted(GregorianCalendar timeFieldWasPlanted) {
        this.timeFieldWasPlanted = timeFieldWasPlanted;
    }

    public int getStage() {
        return stage;
    }

    public void setStage(int stage) {
        this.stage = stage;
    }
}
