package com.admin.esweetadminappfinal;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.admin.esweetadminappfinal.Model.Rider;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class RegisterRiderActivity extends AppCompatActivity {

    private Button registerRiderBtn;
    private Toolbar toolbar;
    private EditText rname;
    private EditText rmobile;
    private EditText remail;
    private EditText rpassword;
    private EditText raddress;
    private Button register_rider_btn;
    private CollectionReference ridercollection;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_rider);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        db = FirebaseFirestore.getInstance();
        ridercollection=db.collection("Riders");
        rname = findViewById(R.id.rname);
        rmobile = findViewById(R.id.rmobile);
        remail = findViewById(R.id.remail);
        rpassword = findViewById(R.id.rpassword);
        raddress = findViewById(R.id.raddress);
        register_rider_btn = findViewById(R.id.register_rider_btn);


        register_rider_btn.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                String name = rname.getText().toString();
                String mobile = rmobile.getText().toString();
                String email = remail.getText().toString();
                String password= rpassword.getText().toString();
                String addrress = raddress.getText().toString();

                Rider r=new Rider();
                r.setName(name);
                r.setMobile(mobile);
                r.setEmail(email);
                r.setPassword(password);
                r.setAddress(addrress);
                ridercollection.add(r).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(RegisterRiderActivity.this, "Rider Saved Successfully", Toast.LENGTH_SHORT).show();
                        rname.setText("");
                        rmobile.setText("");
                        remail.setText("");
                        rpassword.setText("");
                        raddress.setText("");
                    }
                });
            }
        });



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.example_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        switch (item.getItemId()) {
            case R.id.nav_dashboard_icon:
                startActivity(new Intent(RegisterRiderActivity.this, MainActivity.class));
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}