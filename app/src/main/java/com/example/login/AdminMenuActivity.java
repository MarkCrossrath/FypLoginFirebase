package com.example.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class AdminMenuActivity extends AppCompatActivity implements View.OnClickListener {

    Button EventBtn;
    Button PulseBtn;
    Button ResaleBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_menu);

        EventBtn = findViewById(R.id.events_button);
        PulseBtn = findViewById(R.id.the_pulse_button);
        ResaleBtn = findViewById(R.id.resell_button);

        findViewById(R.id.events_button).setOnClickListener(this);

    }



    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.events_button:
                startActivity(new Intent(this, AdminEventActivity.class));


                break;
            //case R.id.the_pulse_button:
            //  startActivity(new Intent(this, PulseActivity.class));
            //    break;
            //  case R.id.resell_button:
            //   startActivity(new Intent(this, ResaleActivity.class));
            //     break;

        }

    }
}
