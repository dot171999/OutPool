package com.dot.outpool;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    FragmentManager fm=getFragmentManager();
    static int fragmentName;
    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentOne(); //home

        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayShowTitleEnabled(false);

        drawerLayout=findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle=new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.activity_main_navigation_toggle_open,R.string.activity_main_navigation_toggle_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.navigation_drawer);
        navigationView.setNavigationItemSelectedListener(this);

        BottomNavigationView navigation = findViewById(R.id.navigation);
        BottomNavigationViewHelper.removeShiftMode(navigation); //disable animation for items > 3

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);



        if(isNetworkAvailable(this))
        {
            userProfile();
        }
        else
        {
            Toast.makeText(this,"No Internet connection",Toast.LENGTH_LONG).show();
        }
    }

    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager conMan = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if(conMan.getActiveNetworkInfo() != null && conMan.getActiveNetworkInfo().isConnected())
            return true;
        else
            return false;
    }

    public void userProfile(){
        GraphRequest data_request = GraphRequest.newMeRequest(
                AccessToken.getCurrentAccessToken(),
                new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(
                            JSONObject json_object,
                            GraphResponse response) {
                        NavigationView navigationView = findViewById(R.id.navigation_drawer);
                        View header=navigationView.getHeaderView(0);
                        TextView textView=header.findViewById(R.id.userName);
                        TextView textView1=header.findViewById(R.id.userID);
                        final ImageView imageView=header.findViewById(R.id.imageView2);
                        try {
                            textView.setText(json_object.getString("name"));
                            textView1.setText(json_object.getString("id"));
                            JSONObject pictureData=new JSONObject(json_object.get("picture").toString());
                            JSONObject pictureUrl=new JSONObject(pictureData.getString("data"));
                            Picasso.get().load(pictureUrl.getString("url")).into(imageView);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
        Bundle permission_param = new Bundle();
        permission_param.putString("fields", "id,name,picture.width(200).height(200)");
                data_request.setParameters(permission_param);
        data_request.executeAsync();

    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.tab1:
                    fragmentOne();
                    return true;
                case R.id.tab2:
                    fragmentTwo();
                    return true;
                case R.id.tab3:
                    fragmentThree();
                    return true;
                case R.id.tab4:
                    fragmentFour();
                    return true;
            }
            return false;
        }
    };

    public void fragmentOne() {
        fragmentName=1;
        FragmentTransaction ft =fm.beginTransaction();
        Fragment fragment=new FragmentOne();
        ft.replace(R.id.frame,fragment);
        ft.commit();
    }

    public void fragmentTwo() {
        fragmentName=2;
        FragmentTransaction ft =fm.beginTransaction();
        Fragment fragment=new FragmentTwo();
        ft.replace(R.id.frame,fragment);
        ft.commit();
    }

    public void fragmentThree() {
        fragmentName=3;
        FragmentTransaction ft =fm.beginTransaction();
        Fragment fragment=new FragmentThree();
        ft.replace(R.id.frame,fragment);
        ft.commit();
    }

    public void fragmentFour() {
        fragmentName=4;
        FragmentTransaction ft =fm.beginTransaction();
        Fragment fragment=new FragmentFour();
        ft.replace(R.id.frame,fragment);
        ft.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main_toolbar_menu, menu);
        return true;
    }

    public void logout(View view) {
        LoginManager.getInstance().logOut();
        Intent intent=new Intent(MainActivity.this,LoginActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.logout) {
            LoginManager.getInstance().logOut();
            Intent intent=new Intent(this,LoginActivity.class);
            startActivity(intent);
        }
        else if (id == R.id.test_login_screen) {
            Intent i=new Intent(this,LoginActivity.class);
            int test=101;
            i.putExtra("test101",test);
            Toast toast=Toast.makeText(getApplicationContext(),"loginTest",Toast.LENGTH_LONG);
            toast.show();
            startActivity(i);
        }
        DrawerLayout drawer =findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if(fragmentName==2 || fragmentName==3 || fragmentName==4)
        {
            BottomNavigationView navigation = findViewById(R.id.navigation);
            navigation.setSelectedItemId(R.id.tab1);
            return;
        }

        if(drawerLayout.isDrawerOpen(Gravity.START))
        {
            drawerLayout.closeDrawer(Gravity.START);
            return;
        }
        super.onBackPressed();
    }
}
