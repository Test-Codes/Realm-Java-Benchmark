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

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.examples.intro.model.realm.RealmPrimaryContact;


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

//        {
//            long timeMillis = System.currentTimeMillis();
//            for (int i = 0; i < 10000; i++) {
//                BasicContact contact = new BasicContact();
//                contact.address = "john" + i + "@example.com";
//                contact.number = "#" + i;
//                contact.active = true;
//            }
//            Log.e("IntroExampleActivity", "Create Object Time#: " + (System.currentTimeMillis() - timeMillis));
//        }

//        {
//            long timeMillis = System.currentTimeMillis();
//            Gson gson = new Gson();
//            for (int i = 0; i < 10000; i++) {
//                BasicContact contact = new BasicContact();
//                contact.address = "john" + i + "@example.com";
//                contact.number = "#" + i;
//                contact.active = true;
//                gson.toJson(contact);
//            }
//            Log.e("IntroExampleActivity", "toJson Time#: " + (System.currentTimeMillis() - timeMillis));
//        }

//        {
//            long timeMillis = System.currentTimeMillis();
//            Gson gson = new Gson();
//            for (int i = 0; i < 10000; i++) {
//                gson.fromJson("{\"address\":\"john" + i + "@example.com\",\"number\":\"#" + i + "\",\"active\":true}", BasicContact.class);
//            }
//            Log.e("IntroExampleActivity", "fromJson Time#: " + (System.currentTimeMillis() - timeMillis));
//        }

//        {
//            long timeMillis = System.currentTimeMillis();
//            realm.beginTransaction();
//            for (int i = 0; i < 10000; i++) {
//                RealmContact contact = realm.createObject(RealmContact.class);
//                contact.setAddress("john" + i + "@example.com");
//                contact.setNumber("#" + i);
//                contact.setActive(true);
//            }
//            realm.commitTransaction();
//            Log.e("IntroExampleActivity", "Realm createObject Time#: " + (System.currentTimeMillis() - timeMillis));
//        }

//        {
//            long timeMillis = System.currentTimeMillis();
//            realm.beginTransaction();
//            for (int i = 0; i < 10000; i++) {
//                RealmContact contact = new RealmContact();
//                contact.setAddress("john" + i + "@example.com");
//                contact.setNumber("#" + i);
//                contact.setActive(true);
//                realm.copyToRealm(contact);
//            }
//            realm.commitTransaction();
//            Log.e("IntroExampleActivity", "Realm copyToRealm Time#: " + (System.currentTimeMillis() - timeMillis));
//        }

        {
            long timeMillis = System.currentTimeMillis();
            realm.beginTransaction();
            for (int i = 0; i < 10000; i++) {
                RealmPrimaryContact contact = new RealmPrimaryContact();
                contact.setAddress("john" + i + "@example.com");
                contact.setNumber("#" + i);
                contact.setActive(true);
                realm.copyToRealmOrUpdate(contact);
            }
            realm.commitTransaction();
            Log.e("IntroExampleActivity", "Realm copyToRealmOrUpdate Time#: " + (System.currentTimeMillis() - timeMillis));
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm.close();
    }
}

//07-10 00:34:33.765  12003-12003/io.realm.examples.intro E/IntroExampleActivity﹕ Create Object Time#: 149
//07-10 00:34:46.955  12877-12877/io.realm.examples.intro E/IntroExampleActivity﹕ Create Object Time#: 159
//07-10 00:34:08.420  10927-10927/io.realm.examples.intro E/IntroExampleActivity﹕ Create Object Time#: 138
//07-10 00:35:37.695  13733-13733/io.realm.examples.intro E/IntroExampleActivity﹕ Create Object Time#: 142
//07-10 00:36:04.925  14806-14806/io.realm.examples.intro E/IntroExampleActivity﹕ Create Object Time#: 144
//146.4

//07-10 00:36:59.855  15863-15863/io.realm.examples.intro E/IntroExampleActivity﹕ toJson Time#: 1199
//07-10 00:37:12.510  16579-16579/io.realm.examples.intro E/IntroExampleActivity﹕ toJson Time#: 1068
//07-10 00:37:30.110  16915-16915/io.realm.examples.intro E/IntroExampleActivity﹕ toJson Time#: 1050
//07-10 00:37:55.095  17464-17464/io.realm.examples.intro E/IntroExampleActivity﹕ toJson Time#: 1167
//07-10 00:38:06.710  18130-18130/io.realm.examples.intro E/IntroExampleActivity﹕ toJson Time#: 1125
//1121.8

//07-10 00:38:53.750  19777-19777/io.realm.examples.intro E/IntroExampleActivity﹕ fromJson Time#: 1046
//07-10 00:39:07.370  20110-20110/io.realm.examples.intro E/IntroExampleActivity﹕ fromJson Time#: 1029
//07-10 00:39:21.470  20360-20360/io.realm.examples.intro E/IntroExampleActivity﹕ fromJson Time#: 1038
//07-10 00:54:12.075  23711-23711/io.realm.examples.intro E/IntroExampleActivity﹕ fromJson Time#: 1011
//07-10 00:54:23.575  23942-23942/io.realm.examples.intro E/IntroExampleActivity﹕ fromJson Time#: 1036
//1032

//07-10 00:55:33.525  25067-25067/io.realm.examples.intro E/IntroExampleActivity﹕ Realm createObject Time#: 889
//07-10 00:56:07.210  26388-26388/io.realm.examples.intro E/IntroExampleActivity﹕ Realm createObject Time#: 901
//07-10 00:55:45.850  25891-25891/io.realm.examples.intro E/IntroExampleActivity﹕ Realm createObject Time#: 791
//07-10 00:56:23.405  27269-27269/io.realm.examples.intro E/IntroExampleActivity﹕ Realm createObject Time#: 837
//07-10 00:57:07.905  29349-29349/io.realm.examples.intro E/IntroExampleActivity﹕ Realm createObject Time#: 806
//844.8

//07-10 00:58:28.715  30890-30890/io.realm.examples.intro E/IntroExampleActivity﹕ Realm copyToRealm Time#: 893
//07-10 00:58:44.540  31200-31200/io.realm.examples.intro E/IntroExampleActivity﹕ Realm copyToRealm Time#: 971
//07-10 00:58:56.555  31488-31488/io.realm.examples.intro E/IntroExampleActivity﹕ Realm copyToRealm Time#: 958
//07-10 00:59:11.005  31644-31644/io.realm.examples.intro E/IntroExampleActivity﹕ Realm copyToRealm Time#: 941
//07-10 00:59:36.065  32059-32059/io.realm.examples.intro E/IntroExampleActivity﹕ Realm copyToRealm Time#: 963
//945.2

//07-10 01:01:45.060    2337-2337/io.realm.examples.intro E/IntroExampleActivity﹕ Realm copyToRealmOrUpdate Time#: 1751
//07-10 01:02:10.475    3007-3007/io.realm.examples.intro E/IntroExampleActivity﹕ Realm copyToRealmOrUpdate Time#: 1700
//07-10 01:02:26.405    3447-3447/io.realm.examples.intro E/IntroExampleActivity﹕ Realm copyToRealmOrUpdate Time#: 1680
//07-10 01:02:36.085    3615-3615/io.realm.examples.intro E/IntroExampleActivity﹕ Realm copyToRealmOrUpdate Time#: 1650
//07-10 01:02:58.365    4069-4069/io.realm.examples.intro E/IntroExampleActivity﹕ Realm copyToRealmOrUpdate Time#: 1754
//1707
