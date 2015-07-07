package io.realm.examples.intro;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.Index;

/**
 * Created by TheFinestArtist on 6/24/15.
 */
public class Contact extends RealmObject {

    private String name;

    @Index
    private RealmList<Email> emails;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public RealmList<Email> getEmails() {
        return emails;
    }
}
