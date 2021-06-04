package com.example.dating_app;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.annotation.NonNull;

@Entity
public class Settings {
    @PrimaryKey(autoGenerate = true)
    private int settingID;

    @ColumnInfo(name = "reminder_time")
    private int remindTime;

    @ColumnInfo(name = "max_distance")
    private int distMax;

    @ColumnInfo(name = "gender")
    private String gender; //0 for male, 1 for female, 3 for non-binary

    @ColumnInfo(name = "privateStatus")
    private boolean privateStatus;

    @ColumnInfo(name = "min_age")
    private int ageMin;

    @ColumnInfo(name = "max_age")
    private int ageMax;

    @NonNull
    public int getSettingID() {
        return settingID;
    }

    public void setSettingID(int settingID) {
        this.settingID = settingID;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getRemindTime() {
        return remindTime;
    }

    public void setRemindTime(int remindTime) {
        this.remindTime = remindTime;
    }

    public int getDistMax() {
        return distMax;
    }

    public void setDistMax(int distMax) {
        this.distMax = distMax;
    }


    public void setPrivateStatus(boolean privateStatus) {
        this.privateStatus = privateStatus;
    }

    public boolean isPrivateStatus() {
        return privateStatus;
    }

    public int getAgeMin() {
        return ageMin;
    }

    public void setAgeMin(int ageMin) {
        this.ageMin = ageMin;
    }

    public int getAgeMax() {
        return ageMax;
    }

    public void setAgeMax(int ageMax) {
        this.ageMax = ageMax;
    }
}



