package com.example.quranapp;


import android.os.Bundle;

import android.widget.TextView;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import com.example.quranapp.Qurandata;
import com.example.quranapp.ParaSurah;
import java.util.Locale;

public class Ayat extends AppCompatActivity {

    private static final String SURAH_NAME_KEY = "surahName";
    private static final String AYAT_NUMBER_KEY = "ayatNumber";
    private static final String AYAT_CONTENT_KEY = "ayatContent";
    TextView ayatNumberTextView;
    TextView ayatContentTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ayat);


        ayatNumberTextView = findViewById(R.id.ayatNumberTextView);
        ayatContentTextView = findViewById(R.id.ayatContentTextView);

        Intent intent = getIntent();
        if (intent != null) {
            String surahName = intent.getStringExtra(SURAH_NAME_KEY);
            int ayatNumber = intent.getIntExtra(AYAT_NUMBER_KEY, 0);
            String ayatContent = intent.getStringExtra(AYAT_CONTENT_KEY);
            setTitle(surahName);

            ayatNumberTextView.setText(String.format(Locale.getDefault(), "Ayat Number: %d", ayatNumber));
            ayatContentTextView.setText(ayatContent);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // Save the state of the activity
        outState.putString(SURAH_NAME_KEY, getTitle().toString());
        outState.putString(AYAT_CONTENT_KEY, ayatContentTextView.getText().toString());
        // Extract the ayat number from the text view
        int ayatNumber = Integer.parseInt(ayatNumberTextView.getText().toString().replaceAll("\\D+", ""));
        outState.putInt(AYAT_NUMBER_KEY, ayatNumber);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        // Restore the state of the activity
        String surahName = savedInstanceState.getString(SURAH_NAME_KEY);
        String ayatContent = savedInstanceState.getString(AYAT_CONTENT_KEY);
        int ayatNumber = savedInstanceState.getInt(AYAT_NUMBER_KEY);

        setTitle(surahName);
        ayatNumberTextView.setText(String.format(Locale.getDefault(), "Ayat Number: %d", ayatNumber));
        ayatContentTextView.setText(ayatContent);
    }
}

