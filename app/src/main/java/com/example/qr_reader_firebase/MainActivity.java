package com.example.qr_reader_firebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    DatabaseReference databaseReference;

   public static TextView showdata;
    Button button;
    Button reqbutton;
    String parentid;
    RequestData rd1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rd1=new RequestData();
        showdata=findViewById(R.id.textView);
        button=findViewById(R.id.button);
        reqbutton=findViewById(R.id.button2);



        parentid="X parent of Y";
        rd1.setMessage(parentid);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),qrScanner.class));
            }
        });
        reqbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                databaseReference= FirebaseDatabase.getInstance().getReference("staff").child(qrScanner.data);

                databaseReference.setValue(rd1).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(getApplicationContext(),"Request Sent Succesfully",Toast.LENGTH_LONG).show();
//                        MainActivity.showdata.setText("Data Insterted succesfully :"+data);
//                        onBackPressed();
                    }
                });
            }
        });
    }
}