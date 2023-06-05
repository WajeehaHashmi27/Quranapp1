package com.example.quranapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.view.ViewGroup;
import android.widget.TextView;
import android.content.res.Configuration;

import com.example.quranapp.Qurandata;
import com.example.quranapp.ParaSurah;
public class MainActivity extends AppCompatActivity {


    ListView surahListView;
    ParaSurah ps;
    Qurandata qd;

    private static final String SELECTED_SURAH_INDEX_KEY = "selectedSurahIndex";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        surahListView = findViewById(R.id.list);
        ps = new ParaSurah();
        qd = new Qurandata();

        ArrayAdapter<String> surahAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_dropdown_item_1line,
                ps.englishSurahNames
        ) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                TextView textView = (TextView) super.getView(position, convertView, parent);
                String surahEnglish = ps.englishSurahNames[position];
                String surahUrdu = ps.urduSurahNames[position];
                String surahText = surahEnglish + " - " + surahUrdu;
                textView.setText(surahText);
                return textView;
            }
        };
        surahListView.setAdapter(surahAdapter);
        surahListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, Surahcontent.class);
                intent.putExtra(SELECTED_SURAH_INDEX_KEY, position);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // Save the selected surah index in the bundle
        outState.putInt(SELECTED_SURAH_INDEX_KEY, surahListView.getSelectedItemPosition());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        // Restore the selected surah index from the bundle
        int selectedSurahIndex = savedInstanceState.getInt(SELECTED_SURAH_INDEX_KEY);
        surahListView.setSelection(selectedSurahIndex);
    }


}
