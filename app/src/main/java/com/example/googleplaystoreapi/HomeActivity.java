package com.example.googleplaystoreapi;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.services.androidpublisher.AndroidPublisher;
import com.google.api.services.androidpublisher.AndroidPublisherScopes;
import com.google.api.services.androidpublisher.model.SubscriptionPurchase;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;

public class HomeActivity extends AppCompatActivity {

    private static final String TAG = "PlayStoreApi";
    TextView user_name, user_email;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        user_name = findViewById(R.id.user_name);
        user_email = findViewById(R.id.user_email);

        Intent intent = getIntent();
        if (intent != null) {
            String userName = intent.getStringExtra("userName");
            String userEmail = intent.getStringExtra("userEmail");

            if (userName != null && userEmail != null) {
                user_name.setText(userName);
                user_email.setText(userEmail);

                new PlayStoreApiTask().execute();
            }
        }
    }

    private class PlayStoreApiTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            try {
                InputStream credentialStream = HomeActivity.this.getAssets().open("rosy-slate-414404-9c5971dc79a0");
                Log.d("asdss1", "asdss" + credentialStream);

                GoogleCredential credential = GoogleCredential.fromStream(credentialStream)
                        .createScoped(Collections.singleton(AndroidPublisherScopes.ANDROIDPUBLISHER));
                Log.d("asdss2", "asdss" + credential);

                AndroidPublisher androidPublisher = new AndroidPublisher.Builder(
                        credential.getTransport(),
                        credential.getJsonFactory(),
                        credential)
                        .setApplicationName("GooglePlayStoreAPI")
                        .build();
                Log.d("asdss3", "asdss" + androidPublisher);

                String packageName = "카트라이더 러쉬플러스";
//                AndroidPublisher.Purchases.Subscriptions.List request = androidPublisher.purchases().subscriptions().list(packageName);
//                AndroidPublisher.Purchases.Subscriptions.ListResponse response = request.execute();
//                SubscriptionPurchase purchase = androidPublisher.purchases().subscriptions().get(packageName, "jangse8931@gmail.com", ).execute();

            } catch (IOException e) {
                Log.e(TAG, "Error : " + e.getMessage());
            }
            return null;
        }
    }
}
