package co.yactech.covid_19;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import co.yactech.covid_19.Adapter.RecyclerViewAdapter;
import co.yactech.covid_19.Model.Doctor;

public class Patient_Profile extends AppCompatActivity {

    List<Doctor> lstpatient;
    RecyclerView recyclerView;

    FirebaseDatabase database;
    DatabaseReference mRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient__profile);

        database = FirebaseDatabase.getInstance();
        mRef = database.getReference("Doctor");

        lstpatient = new ArrayList<>();
        recyclerView = findViewById(R.id.recyclerviewid);

        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    String keyp = snapshot.getKey();


                    String username = dataSnapshot.child(keyp).child("username").toString();
                    // Toast.makeText(getApplicationContext(),username,Toast.LENGTH_SHORT).show();
                    String[] user;
                    user = username.split("=");

                    String password = dataSnapshot.child(keyp).child("password").toString();
                    String img = dataSnapshot.child(keyp).child("image").toString();
                    String address = dataSnapshot.child(keyp).child("address").toString();

                    String[] addres;
                    addres = address.split("=");

                    String phone = dataSnapshot.child(keyp).child("phone").toString();
                    String email = dataSnapshot.child(keyp).child("email").toString();

                    String[] Email;
                    Email = email.split("=");


                    Doctor doctor = new Doctor(user[2], Email[2], password, phone, addres[2], img);


                    lstpatient.add(doctor);
                }

                final RecyclerViewAdapter myadapter = new RecyclerViewAdapter(getApplicationContext(), lstpatient);
                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                recyclerView.setAdapter(myadapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }
}
