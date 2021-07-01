package com.girlsclothremover_bodyscanner2020.audreyprank.model;

public class Person {

    private String Name;
    private String Address;
    private int Profile;
    private int Location;

    public Person() {
    }

    public Person(String name, String address, int profile, int location) {
        Name = name;
        Address = address;
        Profile = profile;
        Location = location;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public int getProfile() {
        return Profile;
    }

    public void setProfile(int profile) {
        Profile = profile;
    }

    public int getLocation() {
        return Location;
    }

    public void setLocation(int location) {
        Location = location;
    }
}
