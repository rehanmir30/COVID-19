package co.yactech.covid_19;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import co.yactech.covid_19.Model.Doctor;
import co.yactech.covid_19.Model.Patient;

public class SignUp_Activity extends AppCompatActivity {

    ImageView back;
    AutoCompleteTextView actv;
    Button create_account;

    EditText name, email, phone, password, confirm_password, address;
    String Email, Password;

    private static final String[] occuptation = new String[]{"Doctor", "Patient"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_);

        back = findViewById(R.id.back);
        actv = findViewById(R.id.actv);

        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        phone = findViewById(R.id.phone);
        password = findViewById(R.id.password);
        confirm_password = findViewById(R.id.confirm_password);
        address = findViewById(R.id.address);

        create_account = findViewById(R.id.create_account);


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, occuptation);
        actv.setAdapter(adapter);


        create_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (name.getText().toString().isEmpty()) {
                    name.setError("Enter Name");
                    return;
                } else if (email.getText().toString().isEmpty()) {
                    email.setError("Email Required");
                    return;
                } else if (phone.getText().toString().isEmpty()) {
                    phone.setError("Enter Phone number");
                    return;
                } else if (password.getText().toString().isEmpty()) {
                    password.setError("Password Required");
                    return;
                } else if (confirm_password.getText().toString().isEmpty()) {
                    confirm_password.setError("Please Confirm Password");
                    return;
                } else if (address.getText().toString().isEmpty()) {
                    address.setError("Enter Address");
                    return;
                } else if (!(password.getText().toString().equals(confirm_password.getText().toString()))) {
                    confirm_password.setError("Passwords don't match");
                    return;
                } else if (actv.getText().toString().isEmpty()) {
                    actv.setError("Select your catagory");
                    return;
                } else if (!(actv.getText().toString().equals(occuptation[0])) && !(actv.getText().toString().equals(occuptation[1]))) {
                    actv.setError("No such catagory found");
                    return;
                } else if (actv.getText().toString().equals(occuptation[0])){
                    final ProgressDialog mDialogue = new ProgressDialog(SignUp_Activity.this);
                    mDialogue.setMessage("Please wait...");
                    mDialogue.show();
//                    Email = email.getText().toString();
//                    Password = password.getText().toString();
                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    final DatabaseReference myRef = database.getReference("Doctor");
//                    myRef.child("username").setValue("Hello, World!");

                    myRef.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            if (dataSnapshot.child(name.getText().toString()).exists()) {
                                mDialogue.dismiss();
                                Toast.makeText(SignUp_Activity.this, "Doctor already exists!", Toast.LENGTH_LONG).show();
                                return;
                            } else {
                                mDialogue.dismiss();
                                Doctor user = new Doctor(name.getText().toString(),email.getText().toString(), password.getText().toString(),phone.getText().toString(),address.getText().toString());
                              //  int count= (int) dataSnapshot.child("Doctor").getChildrenCount();
                                myRef.child(name.getText().toString()).setValue(user);
                                Toast.makeText(SignUp_Activity.this, "Doctor added. Welcome!",Toast.LENGTH_SHORT).show();
                                finish();
                            }

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });



                }else if (actv.getText().toString().equals(occuptation[1])){
                    final ProgressDialog mDialogue = new ProgressDialog(SignUp_Activity.this);
                    mDialogue.setMessage("Please wait...");
                    mDialogue.show();
                    Email = email.getText().toString();
                    Password = password.getText().toString();
                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    final DatabaseReference myRef = database.getReference("Patient");
//                    myRef.child("username").setValue("Hello, World!");

                    myRef.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            if (dataSnapshot.child(name.getText().toString()).exists()) {
                                mDialogue.dismiss();
                                Toast.makeText(SignUp_Activity.this, "Patient already exists!", Toast.LENGTH_LONG).show();
                                return;
                            } else {
                                mDialogue.dismiss();
                                Patient user = new Patient(name.getText().toString(),email.getText().toString(), password.getText().toString(),phone.getText().toString(),address.getText().toString());
                                myRef.child(name.getText().toString()).setValue(user);
                                Toast.makeText(SignUp_Activity.this, "Patient added. Welcome!", Toast.LENGTH_LONG).show();
                                finish();
                            }

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });



                }
                else {
                    Toast.makeText(getApplicationContext(),"Something went wrong, Try again later",Toast.LENGTH_LONG).show();
                    return;
                }


            }
        });


        //

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SignUp_Activity.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        });
    }
}
