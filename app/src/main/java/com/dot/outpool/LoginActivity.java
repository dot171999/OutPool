package com.dot.outpool;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;

import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;

import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.sothree.slidinguppanel.SlidingUpPanelLayout;

import org.json.JSONObject;

import java.util.Arrays;


public class LoginActivity extends AppCompatActivity {
    static String panel_state;
    CallbackManager callbackManager;
    AccessTokenTracker accessTokenTracker;
    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme_NoActionBar);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        accessTokenTracker = new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken newAccessToken) {
                updateWithToken(newAccessToken);
            }
        };

        updateWithToken(AccessToken.getCurrentAccessToken());

        loginBtnPulseAnim();

        loginBtnBigOnPress();

        aboutSlideUp();
    }

    private void updateWithToken(AccessToken currentAccessToken) {
        if (currentAccessToken != null && getIntent().getIntExtra("test101",0)!=101 )
        {
            Intent i = new Intent(LoginActivity.this, MainActivity.class);
            i.putExtra("token",currentAccessToken);
            startActivity(i);
            finish();
        }
        else
        {
            callBackManager();
        }
    }

    public void callBackManager() {
        callbackManager = CallbackManager.Factory.create();
        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
            }

            @Override
            public void onCancel() {
                Toast toast= Toast.makeText(getApplicationContext(),"Login Cancelled",Toast.LENGTH_SHORT);
                toast.show();
            }

            @Override
            public void onError(FacebookException exception) {
                Toast toast= Toast.makeText(getApplicationContext(),"Unable to Login : Try Again",Toast.LENGTH_SHORT);
                toast.show();
            }
        });
    }

    public void setGLoginButton(View view) {
        Toast toast=Toast.makeText(getApplicationContext(),"Yet to add function",Toast.LENGTH_LONG);
        toast.show();
    }

    public void setFbLoginButton(View view) {
        LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("public_profile"));
    }

    private void loginBtnBigOnPress() {
        @SuppressLint("ClickableViewAccessibility")

        final ImageButton fbButton=findViewById(R.id.login_button_fb);
        final RelativeLayout.LayoutParams lp1=(RelativeLayout.LayoutParams) fbButton.getLayoutParams();
        fbButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction()==MotionEvent.ACTION_DOWN)

                {
                    lp1.width=(int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,75,getResources().getDisplayMetrics());
                    lp1.height=(int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,75,getResources().getDisplayMetrics());
                    fbButton.setLayoutParams(lp1);
                }
                if(event.getAction() == MotionEvent.ACTION_UP) {
                    lp1.width=(int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,60,getResources().getDisplayMetrics());
                    lp1.height=(int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,60,getResources().getDisplayMetrics());
                    fbButton.setLayoutParams(lp1);
                }
                return false;
            }
        });

        final ImageButton gButton=findViewById(R.id.login_button_g);
        final RelativeLayout.LayoutParams lp=(RelativeLayout.LayoutParams) gButton.getLayoutParams();
        gButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction()==MotionEvent.ACTION_DOWN)
                {
                    lp.width=(int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,75,getResources().getDisplayMetrics());
                    lp.height=(int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,75,getResources().getDisplayMetrics());
                    gButton.setLayoutParams(lp);
                }
                if(event.getAction() == MotionEvent.ACTION_UP) {
                    lp.width=(int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,60,getResources().getDisplayMetrics());
                    lp.height=(int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,60,getResources().getDisplayMetrics());
                    gButton.setLayoutParams(lp);
                }
                return false;
            }

        });
    }

    private void loginBtnPulseAnim() {
        final ImageButton imageView=findViewById(R.id.login_button_fb1);
        final ImageButton imageView1=findViewById(R.id.login_button_g1);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                imageView.setVisibility(View.VISIBLE);
                imageView1.setVisibility(View.VISIBLE);
            }
        },1500);

        AlphaAnimation fadeIn=new AlphaAnimation(0,1);

        AlphaAnimation fadeOut=new AlphaAnimation(1,0);


        final AnimationSet set = new AnimationSet(false);
        set.addAnimation(fadeIn);
        set.addAnimation(fadeOut);
        fadeOut.setStartOffset(500);
        set.setDuration(900);
        imageView.startAnimation(set);
        imageView1.startAnimation(set);

        set.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) { }
            @Override
            public void onAnimationRepeat(Animation animation) { }
            @Override
            public void onAnimationEnd(Animation animation) {
                imageView.startAnimation(set);
                imageView1.startAnimation(set);

            }
        });
    }

    public void aboutBtnOnPress(View view) {
        SlidingUpPanelLayout slidingUpPanelLayout = findViewById(R.id.sliding_layout);
        slidingUpPanelLayout.setPanelState(SlidingUpPanelLayout.PanelState.EXPANDED);
    }

    private void aboutSlideUp() {
        SlidingUpPanelLayout slidingUpPanelLayout = findViewById(R.id.sliding_layout);
        slidingUpPanelLayout.setParallaxOffset(61);
        slidingUpPanelLayout.addPanelSlideListener(new SlidingUpPanelLayout.PanelSlideListener() {
            @Override
            public void onPanelSlide(View panel, float slideOffset) {
            }
            @Override
            public void onPanelStateChanged(View panel, SlidingUpPanelLayout.PanelState previousState, SlidingUpPanelLayout.PanelState newState) {

                //Toast.makeText(getApplicationContext(),newState.name().toString(),Toast.LENGTH_SHORT).show();
                if(newState.name().equalsIgnoreCase("Collapsed")){

                    //action when collapsed

                }else if(newState.name().equalsIgnoreCase("Expanded")){
                    panel_state="Expanded";

                }else if(newState.name().equalsIgnoreCase("Dragging"))
                {
                }
            }
        });
    }

    @Override
    public void onBackPressed() {

        if(panel_state=="Expanded")
        {
            SlidingUpPanelLayout slidingUpPanelLayout = findViewById(R.id.sliding_layout);
            slidingUpPanelLayout.setPanelState(SlidingUpPanelLayout.PanelState.COLLAPSED);
            panel_state="Collapsed";
            return;
        }
        super.onBackPressed();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }

}
