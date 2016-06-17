package bbcag.ch.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button exitButton = (Button) findViewById(R.id.button2);
        exitButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.equals(findViewById(R.id.button2))) {
            System.exit(0);
        }
        if (v.equals(findViewById(R.id.button))) ;
            //TODO

    }
}