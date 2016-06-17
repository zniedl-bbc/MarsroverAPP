package bbcag.ch.myapplication;

import android.view.View;
import android.widget.TextView;

/**
 * Created by zniedl on 17.06.2016.
 */
public class InputManager {


    public void handleInputMovement(View v, HUDActivity hudActivity) {
        TextView command = (TextView) hudActivity.findViewById(R.id.command);
        if(v.equals(hudActivity.findViewById(R.id.forwardbutton))){
            command.setText("Forwards");
        }
        if(v.equals(hudActivity.findViewById(R.id.backwardbutton))){
            command.setText("Backwards");
        }
        if(v.equals(hudActivity.findViewById(R.id.leftbutton))){
            command.setText("Left");
        }
        if(v.equals(hudActivity.findViewById(R.id.rightbutton))) {
            command.setText("Right");
        }
        if(v.equals(hudActivity.findViewById(R.id.stopMovement))){
            command.setText("Movement stop");
        }

    }



    public void hadleInputCamera(View v, HUDActivity hudActivity){
        TextView command = (TextView) hudActivity.findViewById(R.id.command);
        if(v.equals(hudActivity.findViewById(R.id.cameraforward))){
            command.setText("Camera forward");
        }
        if(v.equals(hudActivity.findViewById(R.id.camerabackward))){
            command.setText("Camera backward");
        }
        if(v.equals(hudActivity.findViewById(R.id.cameraleft))){
            command.setText("Camera left");
        }
        if(v.equals(hudActivity.findViewById(R.id.cameraright))){
            command.setText("Camera right");
        }
        if(v.equals(hudActivity.findViewById(R.id.stopCamera))){
            command.setText("Camera stop");
        }
    }
}
