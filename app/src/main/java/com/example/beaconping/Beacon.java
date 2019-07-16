package com.example.beaconping;

public class Beacon {
    private int mMinorId;
    private int mRssi;
    private double mDistance;
    private String mUnique;

    public Beacon(int minorId, int rssi, double distance, String unique) {
        mMinorId = minorId;
        mRssi = rssi;
        mDistance = distance;
        mUnique = unique;
    }

    public int getmMinorId() {
        return mMinorId;
    }

    public int getmRssi() {
        return mRssi;
    }

    public double getmDistance() {
        return mDistance;
    }

    public String getmUnique() {
        return mUnique;
    }

    public void setmMinorId(int mMinorId) {
        this.mMinorId = mMinorId;
    }

    public void setmRssi(int mRssi) {
        this.mRssi = mRssi;
    }

    public void setmDistance(double mDistance) {
        this.mDistance = mDistance;
    }

    public void setmUnique(String mUnique) {
        this.mUnique = mUnique;
    }
}
