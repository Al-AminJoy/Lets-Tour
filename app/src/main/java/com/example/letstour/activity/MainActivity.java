package com.example.letstour.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.letstour.R;
import com.example.letstour.database.repository.EventRepository;
import com.example.letstour.fragment.CancelReqFragment;
import com.example.letstour.fragment.CreatePostFragment;
import com.example.letstour.fragment.HomeFragment;
import com.example.letstour.fragment.JoinReqFragment;
import com.example.letstour.fragment.MyPostFragment;
import com.example.letstour.services.BackgroundService;
import com.example.letstour.services.CheckConnectivity;
import com.example.letstour.utils.CommonTask;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private static final String CHANNEL_ID ="internet" ;
    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    private NavigationView navigationView;
    private ActionBarDrawerToggle mToggle;
    private Intent backgroundService;
    public static final String BROADCAST = "checkinternet";
    private IntentFilter intentFilter;
    private EventRepository eventRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        intentFilter = new IntentFilter();
        intentFilter.addAction(BROADCAST);
        Intent serviceIntent = new Intent(this, CheckConnectivity.class);
        startService(serviceIntent);
        if (CheckConnectivity.isOnline(getApplicationContext())){
           // Toast.makeText(getApplicationContext(),"true",Toast.LENGTH_SHORT).show();
        }else{
            //showNotification();
             Toast.makeText(getApplicationContext(),"Please Connect With Internet",Toast.LENGTH_SHORT).show();
        }


    }


    public BroadcastReceiver broadcastReceiver=new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(BROADCAST)){
                if (intent.getStringExtra("online_status").equals("true")){
                   // Toast.makeText(getApplicationContext(),"true",Toast.LENGTH_SHORT).show();
                    Log.d("data","true");
                }else {
                   // Toast.makeText(getApplicationContext(), "false", Toast.LENGTH_SHORT).show();
                    Toast.makeText(getApplicationContext(),"Please Connect With Internet",Toast.LENGTH_SHORT).show();
                    Log.d("data", "false");
                }
            }
        }
    };
    @Override
    protected void onRestart() {
        super.onRestart();
        registerReceiver(broadcastReceiver,intentFilter);
    }

    @Override
    protected void onStart() {
        super.onStart();

       toolbar = findViewById(R.id.main_toolbar);
       navigationView=findViewById(R.id.homeNav);
       drawerLayout=findViewById(R.id.drawer_layout);
       toolbar();
       loadFragment();

    }
    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(broadcastReceiver,intentFilter);
        backgroundService=new Intent(this, BackgroundService.class);
        startService(backgroundService);
    }
    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(broadcastReceiver);
        eventRepository=new EventRepository(getApplicationContext());
        eventRepository.deleteAll();

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        eventRepository=new EventRepository(getApplicationContext());
        eventRepository.deleteAll();
    }

    private void toolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        toolbar.setTitleTextColor(ContextCompat.getColor(this, R.color.white));
        mToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);
        mToggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.white));
        drawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
       navigationView.setNavigationItemSelectedListener(this);
      View headerView = navigationView.getHeaderView(0);
       // SharedPreferences spHeader = getSharedPreferences(SharedPref.AppPackage, Context.MODE_PRIVATE);
        TextView title = headerView.findViewById(R.id.textView);
        ImageView ivUserImage = headerView.findViewById(R.id.imageView);
        title.setText(CommonTask.getDataFromSharedPreference(getApplicationContext(),CommonTask.USER_NAME));
       // Picasso.with(getApplicationContext()).load(CommonTask.getDataFromSharedPreference(getApplicationContext(),CommonTask.USER_IMAGE)).into(ivUserImage);
        if (CommonTask.getDataFromSharedPreference(getApplicationContext(),CommonTask.AGENCY_NAME).equals("")){
            hideItem();
        }

    }
    private void hideItem()
    {
        navigationView = (NavigationView) findViewById(R.id.homeNav);
        Menu nav_Menu = navigationView.getMenu();
        nav_Menu.findItem(R.id.nav_Profile).setVisible(false);
        nav_Menu.findItem(R.id.nav_join_req).setVisible(false);
        nav_Menu.findItem(R.id.nav_cancel_req).setVisible(false);
    }
    private void loadFragment() {
        getSupportFragmentManager().beginTransaction().replace(R.id.main_panel,new HomeFragment()).commit();
    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.nav_Home: {
                getSupportFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.main_panel,
                        new HomeFragment()).commit();
                break;
            }
            case R.id.nav_Profile: {


                getSupportFragmentManager().beginTransaction().replace(R.id.main_panel,
                        new CreatePostFragment()).commit();
                break;
            }
            case R.id.nav_join_req: {


                getSupportFragmentManager().beginTransaction().replace(R.id.main_panel,
                        new JoinReqFragment()).commit();
                break;
            }
            case R.id.nav_cancel_req: {


                getSupportFragmentManager().beginTransaction().replace(R.id.main_panel,
                        new CancelReqFragment()).commit();
                break;
            }
            case R.id.nav_My_Post: {
                getSupportFragmentManager().beginTransaction().replace(R.id.main_panel,
                        new MyPostFragment()).commit();
                break;
            }
            case R.id.nav_Logout: {
                FirebaseAuth.getInstance().signOut();
                CommonTask.addDataIntoSharedPreference(getApplicationContext(),CommonTask.USER_KEY,"");
                CommonTask.addDataIntoSharedPreference(getApplicationContext(),CommonTask.AGENCY_NAME,"");
                CommonTask.addDataIntoSharedPreference(getApplicationContext(),CommonTask.USER_NAME,"");
                CommonTask.addDataIntoSharedPreference(getApplicationContext(),CommonTask.USER_EMAIL,"");
                CommonTask.addDataIntoSharedPreference(getApplicationContext(),CommonTask.USER_GENDER,"");
                CommonTask.addDataIntoSharedPreference(getApplicationContext(),CommonTask.USER_PRI_NUMBER,"");
                CommonTask.addDataIntoSharedPreference(getApplicationContext(),CommonTask.USER_NUMBER1,"");
                CommonTask.addDataIntoSharedPreference(getApplicationContext(),CommonTask.USER_NUMBER2,"");
                CommonTask.addDataIntoSharedPreference(getApplicationContext(),CommonTask.USER_IMAGE,"");
                startActivity(new Intent(MainActivity.this,LoginActivity.class));
                eventRepository = new EventRepository(getApplicationContext());
                eventRepository.deleteAll();
                stopService(backgroundService);
                finish();
                break;
            }

        }
        //close navigation drawer
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if(mToggle.onOptionsItemSelected(item))
        {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}