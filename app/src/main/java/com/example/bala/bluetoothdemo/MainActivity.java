package com.example.bala.bluetoothdemo;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    BluetoothAdapter BA;
    public void TurnBluetoothOff(View view){
        BA.disable();
        if(BA.isEnabled()) {
            Toast.makeText(getApplicationContext(), "Bluetooth unable to turn off", Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(getApplicationContext(), "Bluetooth turned off", Toast.LENGTH_LONG).show();
        }
    }
    public void FindDevices(View view){
        Intent i = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
        startActivity(i);
    }
    public void PairedDevices(View view){
        Set<BluetoothDevice> pairedDevices = BA.getBondedDevices();
        ListView paiedDevicesListView = findViewById(R.id.PairedDevicesListView);
        ArrayList pairredDevicesArrayList = new ArrayList();
        for(BluetoothDevice device : pairedDevices){
            pairredDevicesArrayList.add(device.getName());
        }
        ArrayAdapter arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,pairredDevicesArrayList);
        paiedDevicesListView.setAdapter(arrayAdapter);


    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BA = BluetoothAdapter.getDefaultAdapter();
        if(BA.isEnabled()){
            Log.i("Info","Blutooth enabled");
        }else{
            Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivity(intent);
            if(BA.isEnabled()){
                Toast.makeText(getApplicationContext(),"Bluetooth turned on",Toast.LENGTH_LONG).show();
            }
        }
    }
}
