

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.view.ViewGroup;
import android.widget.TextView;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;

public class Surahcontent extends AppCompatActivity {

    private TextView ayatTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ayat);

        ayatTextView = findViewById(R.id.ayatTextView);

        Intent intent = getIntent();
        if (intent != null) {
            int selectedSurahIndex = intent.getIntExtra("selectedSurahIndex", 0);
            int startingIndex = ParaSurah.getSurahStart(selectedSurahIndex);
            int ayatCount = ParaSurah.getSurahVerses(selectedSurahIndex);

            String[] ayatContent = Qurandata.GetData(startingIndex, startingIndex + ayatCount);

            StringBuilder ayatContentBuilder = new StringBuilder();
            for (String ayat : ayatContent) {
                ayatContentBuilder.append(ayat).append("\n\n");
            }

            // Display the ayat content
            ayatTextView.setText(ayatContentBuilder.toString());
        }
    }
}
