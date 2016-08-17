package jokes.com.getjokes;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by Arjun on 22-Jul-2016 for FinalProject.
 */
public class JokesActivity extends AppCompatActivity {

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jokes);

        String joke = getIntent().getStringExtra("JOKE");

        ((TextView) findViewById(R.id.textJoke)).setText(joke);
    }

}
