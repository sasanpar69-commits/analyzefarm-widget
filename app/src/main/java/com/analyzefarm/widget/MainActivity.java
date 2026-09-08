package com.analyzefarm.widget;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setPadding(40, 40, 40, 40);
        layout.setBackgroundColor(0xfff0f4f8);

        TextView title = new TextView(this);
        title.setText("🐔 آنالیز فارم");
        title.setTextSize(24);
        title.setTextColor(0xff1e3a8a);
        title.setGravity(View.TEXT_ALIGNMENT_CENTER);
        layout.addView(title);

        TextView subtitle = new TextView(this);
        subtitle.setText("تحلیل تهویه سالن مرغداری");
        subtitle.setTextSize(14);
        subtitle.setTextColor(0xff64748b);
        subtitle.setGravity(View.TEXT_ALIGNMENT_CENTER);
        layout.addView(subtitle);

        Button btnWebsite = new Button(this);
        btnWebsite.setText("🌐 ورود به سایت آنالیز فارم");
        btnWebsite.setOnClickListener(v -> {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://analyzefarm.ir/"));
            startActivity(browserIntent);
        });
        layout.addView(btnWebsite);

        Button btnInstagram = new Button(this);
        btnInstagram.setText("📸 اینستاگرام ما");
        btnInstagram.setOnClickListener(v -> {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://instagram.com/analyzefarm.ir"));
            startActivity(browserIntent);
        });
        layout.addView(btnInstagram);

        TextView adText = new TextView(this);
        adText.setText("📊 تحلیل تهویه سالن مرغداری بدون سنسور\nwww.analyzefarm.ir");
        adText.setTextSize(10);
        adText.setTextColor(0xffb45309);
        adText.setGravity(View.TEXT_ALIGNMENT_CENTER);
        adText.setPadding(0, 20, 0, 0);
        layout.addView(adText);

        setContentView(layout);
    }
}
