package edu.umb.cs443.nomad.cosmeticmirror;

import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Camera mCamera;
    private CameraPreview mPreview;
    private boolean safeToTakePicture = false;
    private int homeCount = 0;
    private int youtubeCount = 0;
    private int selfieCount = 0;
    private int facebookCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //  Check cameras
        safeToTakePicture = checkCameraHardware(this);
        if (safeToTakePicture)
        // Create an instance of Camera
        mCamera = getCameraInstance();
        else  Toast.makeText(getApplicationContext(),
                    (CharSequence) ("Cameras are not available"),
                    Toast.LENGTH_SHORT).show();

        // Create our Preview view and set it as the content of our activity.
        mPreview = new CameraPreview(this, mCamera);
        FrameLayout preview = (FrameLayout) findViewById(R.id.camera_preview);
        preview.addView(mPreview);

        final ImageButton homeButton = (ImageButton) findViewById(R.id.button_home);
        final ImageButton youtubeButton = (ImageButton) findViewById(R.id.button_youtube);
        final ImageButton selfieButton = (ImageButton) findViewById(R.id.button_selfie);
        final ImageButton facebookButton = (ImageButton) findViewById(R.id.button_facebook);

        youtubeButton.setVisibility(View.GONE);
        selfieButton.setVisibility(View.GONE);
        facebookButton.setVisibility(View.GONE);

        // Home button actions
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                homeCount++;
                if (homeCount % 2 != 0) {
                    youtubeButton.setImageResource(R.drawable.youtube110);
                    facebookButton.setImageResource(R.drawable.facebook110);
                    selfieButton.setImageResource(R.drawable.selfie110);
                    youtubeButton.setVisibility(View.VISIBLE);
                    facebookButton.setVisibility(View.VISIBLE);
                    selfieButton.setVisibility(View.VISIBLE);
                    facebookButton.setImageAlpha(255);
                    selfieButton.setImageAlpha(255);
                    youtubeButton.setImageAlpha(255);
                    homeCount = 1;
                    homeButton.setImageAlpha(100);
                }
                if (homeCount % 2 == 0) {
                    youtubeButton.setVisibility(View.GONE);
                    facebookButton.setVisibility(View.GONE);
                    selfieButton.setVisibility(View.GONE);
                    homeCount = 2;
                    homeButton.setImageAlpha(255);
                }
            }

        });

        // Youtube button actions
        youtubeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                youtubeCount++;
                android.view.ViewGroup.LayoutParams layoutParams = youtubeButton.getLayoutParams();
                if(youtubeCount%2 !=0) {
                    youtubeButton.setImageResource(R.drawable.youtube150);
                    facebookButton.setImageResource(R.drawable.facebook110);
                    selfieButton.setImageResource(R.drawable.selfie110);
                    youtubeButton.setImageAlpha(255);
                    facebookButton.setImageAlpha(100);
                    selfieButton.setImageAlpha(100);
                    youtubeCount = 1;
                }
                if (youtubeCount%2 == 0) {
                    youtubeButton.setImageResource(R.drawable.youtube110);
                   /* youtubeButton.setVisibility(View.GONE);
                    facebookButton.setVisibility(View.GONE);
                    selfieButton.setVisibility(View.GONE);*/
                    youtubeCount = 2;
                    facebookButton.setImageAlpha(255);
                    selfieButton.setImageAlpha(255);
                    //youtubeButton.setImageAlpha(255);
                }
            }

        });

        // Selfie button actions
        selfieButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selfieCount++;
                android.view.ViewGroup.LayoutParams layoutParams = youtubeButton.getLayoutParams();
                if(selfieCount%2 !=0) {
                    selfieButton.setImageResource(R.drawable.selfie150);
                    //youtubeButton.setImageAlpha(100);
                    facebookButton.setImageAlpha(100);
                    youtubeButton.setImageAlpha(100);
                    selfieCount = 1;
                }
                if (selfieCount%2 == 0) {
                    selfieButton.setImageResource(R.drawable.selfie110);
                   /* youtubeButton.setVisibility(View.GONE);
                    facebookButton.setVisibility(View.GONE);
                    selfieButton.setVisibility(View.GONE);*/
                    selfieCount = 2;
                    facebookButton.setImageAlpha(255);
                    youtubeButton.setImageAlpha(255);
                    //youtubeButton.setImageAlpha(255);
                }
            }

        });

        // Facebook button actions
        facebookButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                facebookCount++;
                android.view.ViewGroup.LayoutParams layoutParams = youtubeButton.getLayoutParams();
                if(facebookCount%2 !=0) {
                    facebookButton.setImageResource(R.drawable.facebook150);
                    //youtubeButton.setImageAlpha(100);
                    selfieButton.setImageAlpha(100);
                    youtubeButton.setImageAlpha(100);
                    facebookCount = 1;
                }
                if (facebookCount%2 == 0) {
                    facebookButton.setImageResource(R.drawable.facebook110);
                   /* youtubeButton.setVisibility(View.GONE);
                    facebookButton.setVisibility(View.GONE);
                    selfieButton.setVisibility(View.GONE);*/
                    facebookCount = 2;
                    selfieButton.setImageAlpha(255);
                    youtubeButton.setImageAlpha(255);
                    //youtubeButton.setImageAlpha(255);
                }
            }

        });
    }

    /** Check if this device has a camera */
    private boolean checkCameraHardware(Context context) {
        if (context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA)){
            // this device has a camera
            return true;
        } else {
            // no camera on this device
            return false;
        }
    }

    /** A safe way to get an instance of the Camera object. */
    public static Camera getCameraInstance(){
        Camera c = Camera.open(0);; //= null;
        try {
            //c = Camera.open();
            c = Camera.open(Camera.CameraInfo.CAMERA_FACING_FRONT); // attempt to get a Camera instance
        }
        catch (Exception e){
            // Camera is not available (in use or does not exist)
        }
        return c; // returns null if camera is unavailable
    }
}
