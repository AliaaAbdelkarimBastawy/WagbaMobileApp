package com.example.wagba_application;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Network;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class NoInternetConnection_Activity extends AppCompatActivity {
    boolean isConnected;
    Button try_again;
    ConnectivityManager connectivityManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().setElevation(0);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_no_internet_connection);
        try_again = findViewById(R.id.TryAgainBtn);
        try_again.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (isConnected){

                    Intent i = new Intent(NoInternetConnection_Activity.
                            this,HomeActivity.class);
                    startActivity(i);
                    finish();

                }else   {

                    Toast.makeText(NoInternetConnection_Activity.this,"Not Connected",Toast.LENGTH_SHORT).show();

                }

            }
        });
    }


    private void registerNetworkCallback(){


        try {

            connectivityManager = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
            connectivityManager.registerDefaultNetworkCallback(new ConnectivityManager.NetworkCallback(){

                @Override
                public void onAvailable(@NonNull Network network) {
                    isConnected = true;
                }

                @Override
                public void onLost(@NonNull Network network) {
                    isConnected = false;
                }
            });




        }catch (Exception e){

            isConnected = false;

        }

    }

    private void unregisterNetworkCallback(){

        connectivityManager.unregisterNetworkCallback(new ConnectivityManager.NetworkCallback());

    }

    @Override
    protected void onResume() {
        super.onResume();
        registerNetworkCallback();
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterNetworkCallback();
    }

}
