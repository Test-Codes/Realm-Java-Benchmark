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
import android.widget.TextView;

import io.realm.Realm;
import io.realm.RealmResults;


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

        realm = Realm.getInstance(this);

        realm.beginTransaction();
        for (int i = 0; i < 100; i++) {
            Contact contact = realm.createObject(Contact.class);
            contact.setName("John #" + i);

            Email email1 = realm.createObject(Email.class);
            email1.setAddress("john" + i + "@example.com");
            email1.setActive(true);
            contact.getEmails().add(email1);

            Email email2 = realm.createObject(Email.class);
            email2.setNumber("jd" + i + "@example.com");
            email2.setActive(false);
            contact.getEmails().add(email2);
        }
        realm.commitTransaction();

        RealmResults<Contact> contacts = realm.where(Contact.class).equalTo("emails.active", true).findAll();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm.close();
    }

    private void showStatus(String txt) {
        Log.i(TAG, txt);
        TextView tv = new TextView(this);
        tv.setText(txt);
        rootLayout.addView(tv);
    }
}
