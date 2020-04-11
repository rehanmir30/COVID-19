package co.yactech.covid_19;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;

public class SignUp_Activity extends AppCompatActivity {

    ImageView back;
    AutoCompleteTextView actv;
    private static final String []occuptation=new String []{"Doctor","Patient"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_);

        back=findViewById(R.id.back);
        actv=findViewById(R.id.actv);

        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_expandable_list_item_1,occuptation);
        actv.setAdapter(adapter);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(SignUp_Activity.this,MainActivity.class);
                startActivity(i);
                finish();
            }
        });
    }
}
