package com.example.android.fatcat;

import android.os.Bundle;
import android.provider.MediaStore;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class Camera2Activity extends Activity {

    ImageView iv;
    Button BtnPlay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        return true;
    }

}


