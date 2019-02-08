package be.ehb.demo1helloworld;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private int counter;
    private Button btnCounter;
    private Button btnCounterDown;
    private TextView tvTitle;

    //Inner class gebruiken, om een interface te implementeren
    //onClick, wat doen indien op de knopw geklikt
    private View.OnClickListener btnCounterListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            counter++;
            tvTitle.setText(String.valueOf(counter));
        }
    };

    private View.OnClickListener btnCounterDownListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            counter--;
            tvTitle.setText(String.valueOf(counter));
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        counter = 0;
        //Verwijzing wat er in het scherm zit
        btnCounter = findViewById(R.id.btn_counter);
        btnCounterDown = findViewById(R.id.btn_counter_down);
        tvTitle = findViewById(R.id.tv_title);

        //Actie koppelen aan button
        btnCounter.setOnClickListener(btnCounterListener);
        btnCounterDown.setOnClickListener(btnCounterDownListener);

    }


}
