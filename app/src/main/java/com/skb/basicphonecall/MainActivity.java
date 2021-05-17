package com.skb.basicphonecall;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.security.Permission;

public class MainActivity extends AppCompatActivity {
    EditText phoneNumber;
    ImageButton connectCall;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        phoneNumber = findViewById(R.id.editTextTextPersonName);
        connectCall = findViewById(R.id.imageButton);

        phoneNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                    phoneNumber.setTextColor(Color.BLACK);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        connectCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ContextCompat.checkSelfPermission(v.getContext(), Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED){
                    if(phoneNumber.getText().toString().length() != 10 ){
                        Toast.makeText(MainActivity.this, "Enter a valid Number", Toast.LENGTH_SHORT).show();
                        phoneNumber.setTextColor(Color.RED);
                    }
                    else {
                        Toast.makeText(MainActivity.this, "Connecting call", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Intent.ACTION_CALL);
                        intent.setData(Uri.parse("tel:" + phoneNumber.getText().toString().trim()));
                        startActivity(intent);
                    }
                }
                else{
                    ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.CALL_PHONE}, 0);
                }
            }
        });

    }
}