package com.mygdx.game.control;

import com.mygdx.game.dominio.Campo;
import com.mygdx.game.dominio.TipoRecurso;
import java.util.GregorianCalendar;

public class FieldController {


    private Campo field;
    private int production = 0;
    private long totalGrowDurationInMillis;
   // private final long CORN_GROW_MILLIS =  2 * 24 * 60 * 60 *1000;
    private final long CORN_GROW_MILLIS =  30 * 1000;//(30 segs )
    //private final long POTATO_GROW_MILLIS =  (long)(1.5f * 24 * 60 * 60 *1000);
    private final long POTATO_GROW_MILLIS =  5 * 60 * 1000;
    //private final long STRAWBERRY_GROW_MILLIS =  (long)(1.2f * 24 * 60 * 60 *1000);
    private final long STRAWBERRY_GROW_MILLIS =  60 * 1000;


    public FieldController(Campo field){
        this.field = field;
    }

    public void setStage0(){
        this.totalGrowDurationInMillis = 0;
        field.setPlanted(false);
        field.setNeedsHerbizide(false);
        field.setNeedsManure(false);
        field.setNeedsWater(false);
        field.setStage(0);
        this.production = 0;
        field.setPlantedResourceType(null);
        field.setTimeFieldWasPlanted(null);

    }



    public void plant(int resourceType){
        if (resourceType == TipoRecurso.CORN || resourceType == TipoRecurso.STRAWBERRY || resourceType == TipoRecurso.POTATO){
            //plant
            this.field.setStage(1);
            this.field.setPlanted(true);
            this.field.setPlantedResourceType(resourceType);
            this.field.setTimeFieldWasPlanted(new GregorianCalendar());
            this.production = 950;
            this.field.setNeedsWater(false);
            this.field.setNeedsManure(false);
            this.field.setNeedsHerbizide(false);
            if(resourceType == TipoRecurso.CORN) this.totalGrowDurationInMillis = CORN_GROW_MILLIS;
            else if(resourceType == TipoRecurso.POTATO) this.totalGrowDurationInMillis = POTATO_GROW_MILLIS;
            else if(resourceType == TipoRecurso.STRAWBERRY) this.totalGrowDurationInMillis = STRAWBERRY_GROW_MILLIS;

        }
    }
    public long getRestingTime(){
        long timeTranscurredInMillis = new GregorianCalendar().getTimeInMillis() -field.getTimeFieldWasPlanted().getTimeInMillis();
        long timeBetWeenInMillis     = totalGrowDurationInMillis - timeTranscurredInMillis;

        if (timeBetWeenInMillis > 0 )return timeBetWeenInMillis;
        else return 0;
    }

    public int getCompletedPercent(){
      int percent = (int)( ((totalGrowDurationInMillis - getRestingTime()) * 100) / totalGrowDurationInMillis);
      if (percent <= 100) return percent;
      else return 100;

    }

    public void controlField(){
        if (field.getStage() == 1 && getCompletedPercent() > 33){
            growToStage2();
        }else if (field.getStage() == 2 && getCompletedPercent() > 66){
            growToStage3();
        }else if (field.getStage() == 3 && getCompletedPercent() == 100){
            growToStage4();
        }
    }



    public void growToStage2(){
        this.field.setNeedsManure(true);
        this.field.setStage(2);

    }
    public void growToStage3(){
        this.field.setNeedsWater(true);
        this.field.setNeedsHerbizide(true);
        this.field.setStage(3);
    }
    public void growToStage4(){
        this.field.setStage(4);
    }

    public void waterField(){
        this.field.setNeedsWater(false);
        this.production += 295;
    }
    public void manureToField(){
        this.field.setNeedsManure(false);
        this.production += 340;

    }
    public void herbicideToField(){
        this.field.setNeedsHerbizide(false);
        this.production += 180;
    }
    public int getWaterQuantity(int resourceType){
        int neededWater = 0;
        if(resourceType == TipoRecurso.CORN) neededWater = 1000;
        else if(resourceType == TipoRecurso.STRAWBERRY) neededWater = 5000;
        else if(resourceType == TipoRecurso.POTATO) neededWater = 2500;
        return neededWater;
    }
    public int getManureQuantity(int resourceType){
        int neededManure = 0;
        if(resourceType == TipoRecurso.CORN) neededManure = 100;
        else if(resourceType == TipoRecurso.STRAWBERRY) neededManure = 400;
        else if(resourceType == TipoRecurso.POTATO) neededManure = 200;
        return neededManure;
    }
    public int getHerbicideQuantity(int resourceType){
        int neededHerbicide = 0;
        if(resourceType == TipoRecurso.CORN) neededHerbicide = 100;
        else if(resourceType == TipoRecurso.STRAWBERRY) neededHerbicide = 75;
        else if(resourceType == TipoRecurso.POTATO) neededHerbicide = 75;
        return neededHerbicide;
    }

    public int getProduction() {
        return production;
    }

    public void setProduction(int production) {
        this.production = production;
    }

    public long getTotalGrowDurationInMillis() {
        return totalGrowDurationInMillis;
    }

    public void setTotalGrowDurationInMillis(long totalGrowDurationInMillis) {
        this.totalGrowDurationInMillis = totalGrowDurationInMillis;
    }
}
