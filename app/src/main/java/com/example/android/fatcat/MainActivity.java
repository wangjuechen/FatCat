package com.example.android.fatcat;

import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends Activity {

    ImageView iv;
    Button BtnPlay;
    Uri fileUri;
    String photoPath = "";
    private EditText weight;
    private TextView result;
    private EditText age;
    private EditText name;
    private Button cal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iv=(ImageView)findViewById(R.id.ImageView);
        BtnPlay=(Button)findViewById(R.id.BtnPlay);

        name = (EditText) findViewById(R.id.etName);
        age = (EditText) findViewById(R.id.etAge);
        weight = (EditText) findViewById(R.id.weight);

        cal = (Button) findViewById(R.id.calc);

        //Creating two Arrays for the Spinner Class
        ArrayAdapter<CharSequence> adapterAge;
        ArrayAdapter<CharSequence> adapterSex;

        String[] genderAr = {"Domestic", "Persian","Siamese","Maine Coon"};
        String[] typeAr = {"male", "female"};

        Spinner genderDrp    =(Spinner)findViewById(R.id.spinGender);
        Spinner typeDrp =(Spinner)findViewById(R.id.etType);

        adapterAge =  new ArrayAdapter<CharSequence>(this,android.R.layout.simple_spinner_item,genderAr);
        adapterAge.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        genderDrp.setAdapter(adapterAge);

        adapterSex =  new ArrayAdapter<CharSequence>(this,android.R.layout.simple_spinner_item,typeAr);
        adapterSex.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        typeDrp.setAdapter(adapterSex);

        String selectedGender  = genderDrp.getSelectedItem().toString();
        String selectedType  = typeDrp.getSelectedItem().toString();

        BtnPlay.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View arg0)
            {
                try
                {
                    Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent, 0);
                }
                catch(Exception e)
                {
                    Log.e("Exception", e.getMessage());
                }
            }
        });

        cal.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(v.getContext(),AdviceActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        iv=(ImageView)findViewById(R.id.ImageView);
        BtnPlay=(Button)findViewById(R.id.BtnPlay);
        BtnPlay.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View arg0)
            {
                try
                {
                    Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent, 0);
                }
                catch(Exception e)
                {
                    Log.e("Exception", e.getMessage());
                }
            }
        });
        super.onResume();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if(requestCode==0)
        {
            super.onActivityResult(requestCode, resultCode, data);
            Bundle Extras = data.getExtras();
            Bitmap mBitmap = (Bitmap)Extras.get("data");
            iv.setImageBitmap(mBitmap);
        }
        else
        {
            Toast.makeText(this, "You didn't take photo", Toast.LENGTH_SHORT).show();
        }
    }

    /*private void displayBMI(float bmi) {
        String bmiLabel = "";

        if (Float.compare(bmi, 15f) <= 0) {
            bmiLabel = getString(R.string.very_severely_underweight);
        } else if (Float.compare(bmi, 15f) > 0  &&  Float.compare(bmi, 16f) <= 0) {
            bmiLabel = getString(R.string.severely_underweight);
        } else if (Float.compare(bmi, 16f) > 0  &&  Float.compare(bmi, 18.5f) <= 0) {
            bmiLabel = getString(R.string.underweight);
        } else if (Float.compare(bmi, 18.5f) > 0  &&  Float.compare(bmi, 25f) <= 0) {
            bmiLabel = getString(R.string.normal);
        } else if (Float.compare(bmi, 25f) > 0  &&  Float.compare(bmi, 30f) <= 0) {
            bmiLabel = getString(R.string.overweight);
        } else if (Float.compare(bmi, 30f) > 0  &&  Float.compare(bmi, 35f) <= 0) {
            bmiLabel = getString(R.string.obese_class_i);
        } else if (Float.compare(bmi, 35f) > 0  &&  Float.compare(bmi, 40f) <= 0) {
            bmiLabel = getString(R.string.obese_class_ii);
        } else {
            bmiLabel = getString(R.string.obese_class_iii);
        }

        bmiLabel = bmi + "\n\n" + bmiLabel;
        result.setText(bmiLabel);
    }*/

}


