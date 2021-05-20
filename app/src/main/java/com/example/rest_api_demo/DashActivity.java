package com.example.rest_api_demo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rest_api_demo.utils.NetworkCheck;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class DashActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
//
//    FirebaseComponents firebaseComponents;
//

    FirebaseFirestore firebaseFirestore;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;

    Toolbar toolbar;
    DrawerLayout drawerLayout;
    NavController navController;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash);


        firebaseAuth = FirebaseAuth.getInstance();

        firebaseUser = firebaseAuth.getCurrentUser();

       // firebaseComponents = new FirebaseComponents(firebaseAuth,this);



        setupNavigation();


        NetworkCheck networkCheck = new NetworkCheck();

        if (!networkCheck.checkNetwork(this))
        {
            Toast.makeText(this, "No Internet", Toast.LENGTH_SHORT).show();
            Log.d("Dashboard Fragment","Something Wrong");

        }


    }

    public void setupNavigation() {

        toolbar = findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        drawerLayout = findViewById(R.id.drawerDashLayout);

        navigationView = findViewById(R.id.navigationView);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.nav_drawer_open,R.string.nav_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);

        navController = Navigation.findNavController(this,R.id.host_fragment);



    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {


        item.setCheckable(true);
        drawerLayout.closeDrawers();

        int id = item.getItemId();

        if (id == R.id.dashboard)
        {
            navController.navigate(R.id.dashboardFragment);
        }
        else if (id == R.id.logout)
        {
            firebaseAuth.signOut();
            Intent intent = new Intent(this,signin_activity.class);
            startActivity(intent);
        }


        return true;
    }


    @Override
    public void onBackPressed() {

        if (drawerLayout.isDrawerOpen(GravityCompat.START))
        {
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else
        {
            super.onBackPressed();
        }

    }
}