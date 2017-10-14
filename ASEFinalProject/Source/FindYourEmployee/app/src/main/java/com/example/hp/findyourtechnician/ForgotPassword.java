package com.example.hp.findyourtechnician;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

public class ForgotPassword extends AppCompatActivity {

    String UserName, Password, PhoneNumber;
    EditText Name;
    SmsManager smsManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        Firebase.setAndroidContext(this);

        Name = (EditText) findViewById(R.id.ForgotPassword_UserName);

    }

    public void SendSms(View view){

        Firebase ForgotPasswordRef = new Firebase("https://findyourtechnician.firebaseio.com/Users");

        UserName = ((EditText) findViewById(R.id.ForgotPassword_UserName)).getText().toString();

        if (UserName.isEmpty())
        {
            Name.setError("User Name cannot be blank");
            Name.setFocusable(true);
        }
        else {
            ForgotPasswordRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if (dataSnapshot.hasChild(UserName)){
                        Password = dataSnapshot.child(UserName).child("Password").getValue().toString();
                        PhoneNumber = dataSnapshot.child(UserName).child("Phone").getValue().toString();

                        String Message = "Your Password is: " + Password;

                        smsManager = SmsManager.getDefault();
                        smsManager.sendTextMessage(PhoneNumber, null, Message, null, null);

                        Toast.makeText(getApplicationContext(),"Sms has been sent to the registered phone number", Toast.LENGTH_LONG).show();

                        Intent intent = new Intent(ForgotPassword.this, UserLogin.class);
                        startActivity(intent);
                    }
                    else {
                        Name.setError("Please enter a valid user name");
                        Name.setFocusable(true);
                    }
                }

                @Override
                public void onCancelled(FirebaseError firebaseError) {

                }
            });
        }
    }

}