package com.example.myapplication;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.PrivateKey;
import java.util.ArrayList;
import java.util.List;

public class BreedDetailsActivity extends AppCompatActivity {

    private ImageView breedImageView;
    private TextView breedTextView;

    private String selectedBreed;

    private BreedDetailAdapter breedDetailAdapter;
    private List<String> breeddetailList;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_breed_details);
        // calling this activity's function to
        // use ActionBar utility methods
        ActionBar actionBar = getSupportActionBar();

        // providing title for the ActionBar
        actionBar.setTitle("Revest");

        // providing subtitle for the ActionBar
        actionBar.setSubtitle("   Design a custom Action Bar");

        // adding icon in the ActionBar
        actionBar.setIcon(R.drawable.revestlogo);

        // methods to display the icon in the ActionBar
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

        breedImageView = findViewById(R.id.breed_image_view);
        breedTextView = findViewById(R.id.breed_details_text_view);


        breedTextView = findViewById(R.id.breed_details_text_view);

        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("selectedBreed")) {
            selectedBreed = intent.getStringExtra("selectedBreed");
            breedTextView.setText(selectedBreed);

        }

        // Get the selected breed from the intent
        selectedBreed = getIntent().getStringExtra("selectedBreed");

        // Construct the API endpoint with the selected breed
        String imageUrl = "https://dog.ceo/api/breed/" + selectedBreed + "/images/random";

        // Fetch JSON data from the API endpoint
        new Thread(() -> {
            try {
                URL url = new URL(imageUrl);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();

                if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    StringBuilder response = new StringBuilder();
                    String line;

                    while ((line = reader.readLine()) != null) {
                        response.append(line);
                    }
                    reader.close();

                    // Parse JSON data and get the image URL
                    JSONObject jsonObject = new JSONObject(response.toString());
                    String imageUrldata = jsonObject.getString("message");

                    // Load the image into ImageView using Picasso
                    runOnUiThread(() -> Picasso.get().load(imageUrldata).into(breedImageView));
                } else {
                    // Handle connection error or non-OK response
                }

                connection.disconnect();
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }
        }).start();

    }
}