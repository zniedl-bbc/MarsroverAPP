package bbcag.ch.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        changeToHUD();
    }

    private void changeToHUD() {
        Intent intent = new Intent(this, HUDActivity.class);
        startActivity(intent);
    }
}
