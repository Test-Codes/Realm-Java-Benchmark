/*
 * Copyright 2014 Realm Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.realm.examples.intro;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;

import com.bluelinelabs.logansquare.LoganSquare;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;


public class IntroExampleActivity extends Activity {

    public static final String TAG = IntroExampleActivity.class.getName();
    private LinearLayout rootLayout = null;

    private Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_realm_basic_example);
        rootLayout = ((LinearLayout) findViewById(R.id.container));
        rootLayout.removeAllViews();

        Realm.deleteRealm(new RealmConfiguration.Builder(this).build());
        realm = Realm.getInstance(this);

        {
            long timeMillis = System.currentTimeMillis();
            realm.beginTransaction();
            for (int i = 0; i < 30000; i++) {
                Email email = realm.createObject(Email.class);
                email.setAddress("john" + i + "@example.com");
                email.setNumber("#" + i);
                email.setActive(true);
            }
            realm.commitTransaction();
            Log.e("IntroExampleActivity", "Time#1: " + (System.currentTimeMillis() - timeMillis));
        }

        {
            long timeMillis = System.currentTimeMillis();
            realm.beginTransaction();
            for (int i = 0; i < 30000; i++) {
                Email email = new Email();
                email.setAddress("john" + i + "@example.com");
                email.setNumber("#" + i);
                email.setActive(true);
                realm.copyToRealm(email);
            }
            realm.commitTransaction();
            Log.e("IntroExampleActivity", "Time#2: " + (System.currentTimeMillis() - timeMillis));
        }

        {
            long timeMillis = System.currentTimeMillis();
            realm.beginTransaction();
            List<Email> emails = new ArrayList<>();
            for (int i = 0; i < 30000; i++) {
                Email email = new Email();
                email.setAddress("john" + i + "@example.com");
                email.setNumber("#" + i);
                email.setActive(true);
                emails.add(email);
            }
            realm.copyToRealm(emails);
            realm.commitTransaction();
            Log.e("IntroExampleActivity", "Time#3: " + (System.currentTimeMillis() - timeMillis));
        }

        {
            long timeMillis = System.currentTimeMillis();
            Gson gson = new Gson();
            for (int i = 0; i < 30000; i++) {
                Contact contact = new Contact();
                contact.address = "john" + i + "@example.com";
                contact.number = "#" + i;
                contact.active = true;
                String jsonString = gson.toJson(contact);
            }
            Log.e("IntroExampleActivity", "Time#4: " + (System.currentTimeMillis() - timeMillis));
        }

        {
            try {
                long timeMillis = System.currentTimeMillis();
                Gson gson = new Gson();
                for (int i = 0; i < 30000; i++) {
                    User user = new User();
                    user.address = "john" + i + "@example.com";
                    user.number = "#" + i;
                    user.active = true;
                    String jsonString = LoganSquare.serialize(user);
                }
                Log.e("IntroExampleActivity", "Time#5: " + (System.currentTimeMillis() - timeMillis));
            } catch (IOException e) {
                Log.e("IntroExampleActivity", "IOException: " + e.getMessage());
            }
        }

        {
            try {
                long timeMillis = System.currentTimeMillis();
                Users users = new Users();
                for (int i = 0; i < 30000; i++) {
                    User user = new User();
                    user.address = "john" + i + "@example.com";
                    user.number = "#" + i;
                    user.active = true;
                    users.users.add(user);
                }
                String jsonString = LoganSquare.serialize(users);
                Preferences.setData(this, jsonString);
                Log.e("IntroExampleActivity", "Time#6: " + (System.currentTimeMillis() - timeMillis));
            } catch (IOException e) {
                Log.e("IntroExampleActivity", "IOException: " + e.getMessage());
            }
        }
    }

//            07-07 14:33:25.140  23219-23219/io.realm.examples.intro E/IntroExampleActivity﹕ Time#1: 2664
//            07-07 14:33:28.900  23219-23219/io.realm.examples.intro E/IntroExampleActivity﹕ Time#2: 3758
//            07-07 14:33:32.165  23219-23219/io.realm.examples.intro E/IntroExampleActivity﹕ Time#3: 3265
//            07-07 14:33:35.800  23219-23219/io.realm.examples.intro E/IntroExampleActivity﹕ Time#4: 3639
//            07-07 14:33:37.630  23219-23219/io.realm.examples.intro E/IntroExampleActivity﹕ Time#5: 1826
//            07-07 14:33:39.440  23219-23219/io.realm.examples.intro E/IntroExampleActivity﹕ Time#6: 1813

//            07-07 14:34:21.070  24513-24513/io.realm.examples.intro E/IntroExampleActivity﹕ Time#1: 3666
//            07-07 14:34:23.630  24513-24513/io.realm.examples.intro E/IntroExampleActivity﹕ Time#2: 2558
//            07-07 14:34:26.850  24513-24513/io.realm.examples.intro E/IntroExampleActivity﹕ Time#3: 3221
//            07-07 14:34:30.525  24513-24513/io.realm.examples.intro E/IntroExampleActivity﹕ Time#4: 3678
//            07-07 14:34:32.345  24513-24513/io.realm.examples.intro E/IntroExampleActivity﹕ Time#5: 1817
//            07-07 14:34:38.190  24513-24513/io.realm.examples.intro E/IntroExampleActivity﹕ Time#6: 5844

//            07-07 14:34:55.050  25584-25584/io.realm.examples.intro E/IntroExampleActivity﹕ Time#1: 2427
//            07-07 14:34:58.125  25584-25584/io.realm.examples.intro E/IntroExampleActivity﹕ Time#2: 3075
//            07-07 14:35:01.390  25584-25584/io.realm.examples.intro E/IntroExampleActivity﹕ Time#3: 3264
//            07-07 14:35:04.985  25584-25584/io.realm.examples.intro E/IntroExampleActivity﹕ Time#4: 3596
//            07-07 14:35:06.745  25584-25584/io.realm.examples.intro E/IntroExampleActivity﹕ Time#5: 1756
//            07-07 14:35:12.520  25584-25584/io.realm.examples.intro E/IntroExampleActivity﹕ Time#6: 5779

//            07-07 14:36:31.545  26561-26561/io.realm.examples.intro E/IntroExampleActivity﹕ Time#1: 3306
//            07-07 14:36:34.335  26561-26561/io.realm.examples.intro E/IntroExampleActivity﹕ Time#2: 2792
//            07-07 14:36:36.815  26561-26561/io.realm.examples.intro E/IntroExampleActivity﹕ Time#3: 2478
//            07-07 14:36:39.525  26561-26561/io.realm.examples.intro E/IntroExampleActivity﹕ Time#4: 2711
//            07-07 14:36:40.885  26561-26561/io.realm.examples.intro E/IntroExampleActivity﹕ Time#5: 1362
//            07-07 14:36:45.355  26561-26561/io.realm.examples.intro E/IntroExampleActivity﹕ Time#6: 4467

    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm.close();
    }
}
