package com.example.messageapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
     EditText txt_message,txt_phone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txt_message=(EditText)findViewById(R.id.txt2);
        txt_phone=(EditText)findViewById(R.id.txt1);
    }
    public void btn_send(View view)
    {
int permission_check= ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS);
if(permission_check== PackageManager.PERMISSION_GRANTED)
{
    Mymessage();
}
else
{
    ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.SEND_SMS},0);
}
    }
    private void Mymessage()
    {
        String phonenumber=txt_phone.getText().toString().trim();

        String messager=txt_message.getText().toString().trim();
        if(!txt_message.getText().toString().equals("")||!txt_phone.getText().toString().equals(""))
        {
            SmsManager smsManager=SmsManager.getDefault();
            smsManager.sendTextMessage(phonenumber,null,messager,null,null);
            Toast.makeText(this,"message sent",Toast.LENGTH_LONG).show();
        }
        else
        {
            Toast.makeText(this,"enter phone number and message",Toast.LENGTH_LONG).show();
        }
    }


}
