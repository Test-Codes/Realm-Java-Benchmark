package io.realm.examples.intro.unusing;

import com.bluelinelabs.logansquare.annotation.JsonField;
import com.bluelinelabs.logansquare.annotation.JsonObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by TheFinestArtist on 7/7/15.
 */
@JsonObject
public class Users {
    @JsonField
    List<User> users = new ArrayList<>();
}
