package io.realm.examples.intro;

import io.realm.RealmObject;

/**
 * Created by TheFinestArtist on 6/24/15.
 */
public class Email extends RealmObject {

    private String address;
    private String number;
    private boolean active;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
