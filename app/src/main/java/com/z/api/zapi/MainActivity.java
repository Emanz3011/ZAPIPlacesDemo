package com.z.api.zapi;

import android.content.Context;
import android.os.SystemClock;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.android.gms.common.data.Freezable;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.PlaceLikelihood;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ZAPIClient zClient;
    PlacesZAPI zPlaces;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        zClient = new ZAPIClient();
        zPlaces = new PlacesZAPI();

        zClient.AddAPI(zPlaces);
        zClient.registerClient(this,this);
        //final Context c = this;

        zClient.addCallback(zClient.new onCreateCallback(){
            @Override
            public void onCreate() {


                PlacesZAPI.placesRequest pRequest = zPlaces.new placesRequest(zClient, getParent()){
                    @Override
                    public void onReturn(ArrayList<PlaceLikelihood> places) {
                        System.out.println(places.get(1).getPlace().getAddress());
                    }
                };
            }
        });
    }
}
