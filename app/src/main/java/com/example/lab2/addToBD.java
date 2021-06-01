package com.example.lab2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class addToBD extends AppCompatActivity {

    public EditText manufacturer, model, androidVersion, website;
    public Button mWebsiteButton, cancelButton, saveButton;

    public static final int CANCEL_ADDING_TO_DATABASE = 10;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_to_bd2);

        Bundle bundle = getIntent().getExtras();

        cancelButton = findViewById(R.id.cancelButton);
        mWebsiteButton = findViewById(R.id.websiteButton);
        saveButton = findViewById(R.id.saveButton);

        manufacturer = findViewById(R.id.manufacturerEdit);
        model = findViewById(R.id.modelEdit);
        androidVersion = findViewById(R.id.androidVersionEdit);
        website = findViewById(R.id.websiteEdit);

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                intent = new Intent(addToBD.this, MainActivity.class);
                startActivityForResult(intent, CANCEL_ADDING_TO_DATABASE);
            }
        });

   saveButton.setOnClickListener(v -> {

       String mManufacturer = manufacturer.getText().toString();
       String mModel = model.getText().toString();
       String mAndroidVersion = androidVersion.getText().toString();
       String mWebsite = website.getText().toString();

       if (!(mManufacturer.isEmpty() && mModel.isEmpty() &&
               mAndroidVersion.isEmpty() && mWebsite.isEmpty())) {

           Bundle bundle2 = new Bundle();
           if (bundle == null) {
               bundle2.putString("manufacturer", mManufacturer);
               bundle2.putString("model", mModel);
               bundle2.putString("version", mAndroidVersion);
               bundle2.putString("website", mWebsite);

               Intent replyIntent = new Intent();
               replyIntent.putExtras(bundle2);
               setResult(RESULT_OK, replyIntent);
           }

           else {
               bundle2.putLong("id", bundle.getLong("id"));
               bundle2.putString("manufacturer", mManufacturer);
               bundle2.putString("model", mModel);
               bundle2.putString("version", mAndroidVersion);
               bundle2.putString("website", mWebsite);

               Intent replyIntent = new Intent();
               replyIntent.putExtras(bundle2);
               setResult(99, replyIntent);
           }
           finish();
       }

       else {
           Toast.makeText(addToBD.this, "Write text", Toast.LENGTH_LONG).show();

           if (mManufacturer.matches(""))
           {
               manufacturer.setError(getString(R.string.error));
           }
           if (mModel.matches(""))
           {
               model.setError(getString(R.string.error));
           }
           if (mAndroidVersion.matches(""))
           {
               androidVersion.setError(getString(R.string.error));
           }
           if (mWebsite.matches(""))
           {
               website.setError(getString(R.string.error));
           }
       }

   });


   mWebsiteButton.setOnClickListener(new View.OnClickListener() {
       @Override
       public void onClick(View v) {

           if (!(website.getText().toString().isEmpty())) {
               String url = website.getText().toString();
               if (!(url.startsWith("http://")) && !(url.startsWith("https://"))) {
                   url = "https://" + url;
                   Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                   startActivity(browserIntent);
               }
               else {
                   Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                   startActivity(browserIntent);
               }
           }

           else {
               website.setError(getString(R.string.empty));
           }
       }
   });


       if (bundle != null) {
           long bId = bundle.getLong("id");
           String bOem = bundle.getString("manufacturer");
           String bModel = bundle.getString("model");
           String bVersion = bundle.getString("version");
           String bWebsite = bundle.getString("web");

           manufacturer.setText(bOem);
           model.setText(bModel);
           androidVersion.setText(bVersion);
           website.setText(bWebsite);

           saveButton.setText(R.string.edit_text);

       }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putString("manu_save", manufacturer.getText().toString());
        outState.putString("model_save", model.getText().toString());
        outState.putString("version_save", androidVersion.getText().toString());
        outState.putString("website_save", website.getText().toString());
        super.onSaveInstanceState(outState);

    }
    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        manufacturer.setText(savedInstanceState.getString("manu_save"));
        model.setText(savedInstanceState.getString("model_save"));
        androidVersion.setText(savedInstanceState.getString("version_save"));
        website.setText(savedInstanceState.getString("website_save"));

    }







}

