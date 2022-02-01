package com.example.booking_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    private CardView profile_card;
    private CardView booking_card;
    private CardView whatsapp_card;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        profile_card = (CardView) findViewById(R.id.card_view_Profile);
        profile_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ProfileActivity.class));
            }
        });

//        booking_card = (CardView) findViewById(R.id.card_view_booking);
//        booking_card.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(HomeActivity.this, BookingActivity.class));
//            }
//        });


        whatsapp_card = (CardView) findViewById(R.id.card_view_whatsapp);
        whatsapp_card.setOnClickListener(new View.OnClickListener() {
            String editText_mobile = "548083353";
            String editText_msg = "hii";


            @Override
            public void onClick(View v) {
                String mobileNumber = editText_mobile.toString();
                String message = editText_msg.toString();

                boolean installed = appInstallOrNot("com.whatsapp");

                if (installed) {
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse("http://api.whatsapp.com/send?phone=" + "+972" + mobileNumber + "&text=" + message));
                    startActivity(intent);
                } else {
                    Toast.makeText(MainActivity.this, "whatsapp not installed on your device", Toast.LENGTH_SHORT);
                }

            }
        });


    }

    private boolean appInstallOrNot(String url) {
        PackageManager packageManager = getPackageManager();
        boolean app_installed;
        try {
            packageManager.getPackageInfo(url, PackageManager.GET_ACTIVITIES);
            app_installed = true;

        } catch (PackageManager.NameNotFoundException e) {

            app_installed = false;
        }
        return app_installed;
    }
}
