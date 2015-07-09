package io.realm.examples.intro.unusing;

import com.bluelinelabs.logansquare.annotation.JsonField;
import com.bluelinelabs.logansquare.annotation.JsonObject;

/**
 * Created by TheFinestArtist on 7/7/15.
 */
@JsonObject
public class User {
    @JsonField
    public String address;
    @JsonField
    public String number;
    @JsonField
    public boolean active;
}
