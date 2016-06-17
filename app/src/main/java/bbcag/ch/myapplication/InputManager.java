package bbcag.ch.myapplication;

import android.view.View;
import android.widget.TextView;

import bbcag.ch.myapplication.rovercontroll.ApolloController;

/**
 * Created by zniedl on 17.06.2016.
 */
public class InputManager {

    private ApolloController controller = new ApolloController();


    public void handleInputMovement(View v, HUDActivity hudActivity) {
        TextView command = (TextView) hudActivity.findViewById(R.id.command);
        if(v.equals(hudActivity.findViewById(R.id.forwardbutton))){
            controller.motionCommand("servoForward");
        }
        if(v.equals(hudActivity.findViewById(R.id.backwardbutton))){
            controller.motionCommand("servoBackward");
        }
        if(v.equals(hudActivity.findViewById(R.id.leftbutton))){
            controller.motionCommand("servoLeft");
        }
        if(v.equals(hudActivity.findViewById(R.id.rightbutton))) {
            controller.motionCommand("servoRight");
        }
        if(v.equals(hudActivity.findViewById(R.id.stopMovement))){
            controller.motionCommand("emergencyStop");
        }

    }



    public void hadleInputCamera(View v, HUDActivity hudActivity){
        TextView command = (TextView) hudActivity.findViewById(R.id.command);
        if(v.equals(hudActivity.findViewById(R.id.cameraforward))){
            controller.motionCommand("camUp");
        }
        if(v.equals(hudActivity.findViewById(R.id.camerabackward))){
            controller.motionCommand("camDown");
        }
        if(v.equals(hudActivity.findViewById(R.id.cameraleft))){
            controller.motionCommand("camLeft");
        }
        if(v.equals(hudActivity.findViewById(R.id.cameraright))){
            controller.motionCommand("camRight");
        }
    }
}
