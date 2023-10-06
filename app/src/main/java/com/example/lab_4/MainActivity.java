package com.example.lab_4;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    EditText teamNameField, teamAddressField;
    TextView titleTextView, teamNameTextField2, teamNameTextField;
    ImageView logoImage;
    Button googleMaps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        googleMaps=findViewById(R.id.googleMaps);
//        logoImage=findViewById(R.id.logoImage);
//        titleTextView=findViewById(R.id.titleTextView);
//        teamNameTextField2=findViewById(R.id.teamNameTextField2);
//        teamNameTextField=findViewById(R.id.teamNameTextField);
//        teamNameField=findViewById(R.id.teamNameField);
//        teamAddressField=findViewById(R.id.teamAddressField);
//
//        googleMaps.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String postalCode = AddressInput.getText().toString();
//                if(postalCode.equals("")){
//                    textView0.setText("Please enter Postal Code to search");
//                    return;
//                }
//
//                OnOpenInGoogleMaps(v);
//
//
//            }
//        });
    }

    public void OnOpenInGoogleMaps (View view) {
        EditText teamAddres = (EditText) findViewById(R.id.teamAddressField);
    // Create a Uri from an intent string. Use the result to create an Intent.
        Uri gmmIntentUri = Uri.parse("http://maps.google.co.in/maps?q="+teamAddres.getText());
    // Create an Intent from gmmIntentUri. Set the action to ACTION_VIEW
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
    // Make the Intent explicit by setting the Google Maps package
        mapIntent.setPackage("com.google.android.apps.maps");
    // Attempt to start an activity that can handle the Intent
        startActivity(mapIntent);
    }

    public void OnSetAvatarButton(View view) {
//Application Context and Activity
        Intent intent = new Intent(getApplicationContext(), ProfileActivity.class);
        startActivityForResult(intent, 0);
    }

    @SuppressLint("MissingSuperCall")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_CANCELED) return;
    //Getting the Avatar Image we show to our users
        ImageView avatarImage = (ImageView) findViewById(R.id.logoImage);
    //Figuring out the correct image
        String drawableName = "ic_logo_00";
        switch (data.getIntExtra("imageID",R.id.logoImage00)) {
            case R.id.logoImage00:
                drawableName = "ic_logo_01";
                break;
            case R.id.logoImage01:
                drawableName = "ic_logo_02";
                break;
            case R.id.logoImage02:
                drawableName = "ic_logo_03";
                break;
            case R.id.logoImage03:
                drawableName = "ic_logo_04";
                break;
            case R.id.logoImage04:
                drawableName = "ic_logo_05";
                break;
            case R.id.logoImage05:
                drawableName = "ic_logo_00";
                break;
            default:
                drawableName = "ic_logo_00";
                break;
        }
        int resID = getResources().getIdentifier(drawableName, "drawable", getPackageName());
        avatarImage.setImageResource(resID);
    }
}