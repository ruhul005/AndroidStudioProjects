package com.example.ruhulamin.datawork;

/**
 * Created by ruhulamin on 1/4/18.
 */

public class Donor {
    String donorId;
    String donorName;
    String bloodType;

    public Donor(String donorId, String donorName, String bloodType) {
        this.donorId = donorId;
        this.donorName = donorName;
        this.bloodType = bloodType;
    }

    public String getDonorId() {
        return donorId;
    }

    public String getDonorName() {
        return donorName;
    }

    public String getBloodType() {
        return bloodType;
    }
}
