package bbcag.ch.myapplication;

import android.content.pm.ActivityInfo;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;

public class HUDActivity extends AppCompatActivity implements View.OnTouchListener, View.OnClickListener{

    private InputManager inputManager;
    private ImageButton[] movementButtons = new ImageButton[5];
    private ImageButton[] cameraButtons = new ImageButton[5];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hud);
        inputManager = new InputManager();

        movementButtons[0]=(ImageButton) findViewById(R.id.forwardbutton);
        movementButtons[1]=(ImageButton) findViewById(R.id.backwardbutton);
        movementButtons[2]=(ImageButton) findViewById(R.id.leftbutton);
        movementButtons[3]=(ImageButton) findViewById(R.id.rightbutton);
        movementButtons[4]=(ImageButton) findViewById(R.id.stopMovement);

        cameraButtons[0]=(ImageButton) findViewById(R.id.cameraforward);
        cameraButtons[1]=(ImageButton) findViewById(R.id.camerabackward);
        cameraButtons[2]=(ImageButton) findViewById(R.id.cameraleft);
        cameraButtons[3]=(ImageButton) findViewById(R.id.cameraright);
        cameraButtons[4]=(ImageButton) findViewById(R.id.stopCamera);

        for (ImageButton imageButton : movementButtons){
                imageButton.setOnTouchListener(this);
        }
        for (ImageButton imageButton : cameraButtons){
            imageButton.setOnClickListener(this);
        }


        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
    }


    @Override
    public boolean onTouch(View v, MotionEvent event) {
        inputManager.handleInputMovement(v,this);
        return false;
    }

    @Override
    public void onClick(View v) {
        inputManager.hadleInputCamera(v,this);
    }
}
