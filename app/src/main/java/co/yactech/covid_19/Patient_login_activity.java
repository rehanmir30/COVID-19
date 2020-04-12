package co.yactech.covid_19;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Patient_login_activity extends AppCompatActivity {

    EditText username, password;
    Button signin;

    FirebaseDatabase database;
    DatabaseReference mRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_login_activity);

        database = FirebaseDatabase.getInstance();
        mRef = database.getReference("Patient");

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);

        signin = findViewById(R.id.signin);

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (username.getText().toString().isEmpty()) {
                    username.setError("Enter username");
                    return;
                } else if (password.getText().toString().isEmpty()) {
                    password.setError("Enter Password");
                    return;
                } else {

                    mRef.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            if (dataSnapshot.child(username.getText().toString()).exists()) {
                                String Password = dataSnapshot.child(username.getText().toString()).child("password").getValue().toString();
                                if (Password.equals(password.getText().toString())) {
                                    Toast.makeText(getApplicationContext(), "correct logins", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(getApplicationContext(), "wrong logins", Toast.LENGTH_SHORT).show();
                                    return;
                                }
                            } else {
                                Toast.makeText(getApplicationContext(), "No such user found", Toast.LENGTH_LONG).show();
                                return;
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });


                }
            }
        });

    }
}
