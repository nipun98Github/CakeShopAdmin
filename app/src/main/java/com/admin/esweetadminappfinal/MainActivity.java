package com.admin.esweetadminappfinal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private CardView cardviewProductAdd;
    private CardView cardviewRiderAdd;
    private CardView cardviewOrdersAll;
    private CardView cardviewOrdersPending;
    private Toolbar toolbar;
    private DrawerLayout drawer;
    private String name;
    private String emailName;
    private String userDocId;
    private String authId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        Bundle bundle = getIntent().getExtras();
//
//        name = bundle.getString("auth_name");
//        emailName = bundle.getString("auth_email");
//        authId = bundle.getString("auth_id");
//        userDocId = bundle.getString("adminDocId");


        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);

        toggle.syncState();
        cardviewProductAdd = findViewById(R.id.cardview_product);
        cardviewRiderAdd = findViewById(R.id.cardview_rider);
        cardviewOrdersAll = findViewById(R.id.cardview_order_all);
        cardviewOrdersPending = findViewById(R.id.cardview_order_pending);

        try {
            cardviewProductAdd.setBackgroundResource(R.drawable.card_view_border);
            cardviewRiderAdd.setBackgroundResource(R.drawable.card_view_border);
            cardviewOrdersAll.setBackgroundResource(R.drawable.card_view_border);
            cardviewOrdersPending.setBackgroundResource(R.drawable.card_view_border);

        } catch (Exception e) {

        }

        cardviewProductAdd = findViewById(R.id.cardview_product);

        cardviewProductAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, AddProductActivity.class));


            }

        });

        cardviewRiderAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, RegisterRiderActivity.class));

            }
        });
        cardviewOrdersAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, AllOrders.class));


            }

        });  cardviewOrdersPending.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, AllOrders.class));


            }

        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        switch (item.getItemId()) {
            case R.id.option_signout:
                showPopup();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
    //    @Override
//    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//        switch (item.getItemId()) {
//            case R.id.nav_home:
//                getSupportFragmentManager().beginTransaction().replace(R.id.framelayout,
//                        new HomeFragment()).commit();
//                break;
//            case R.id.nav_profile:
//                getSupportFragmentManager().beginTransaction().replace(R.id.framelayout,
//                        new ProfileFragment()).commit();
//                break;
//            case R.id.nav_customize_orders:
//                getSupportFragmentManager().beginTransaction().replace(R.id.framelayout,
//                        new CustomizeOrdersFragment()).commit();
//                break;
//            case R.id.nav_cart_payment:
//                Intent intent = new Intent(HomeActivity.this, CartPaymentActivity.class);
//                startActivity(intent);
//                Toast.makeText(this, "Share", Toast.LENGTH_SHORT).show();
//                break;
//            case R.id.nav_order_details_history:
//                getSupportFragmentManager().beginTransaction().replace(R.id.framelayout,
//                        new OrderDetailsHistoryFragment()).commit();
//                Toast.makeText(this, "Send", Toast.LENGTH_SHORT).show();
//                break;
//            case R.id.nav_about:
//                Intent intent1 = new Intent(HomeActivity.this, AboutActivity.class);
//                startActivity(intent1);
//                Toast.makeText(this, "Send", Toast.LENGTH_SHORT).show();
//                break;
//        }
//        drawer.closeDrawer(GravityCompat.START);
//        return true;
//    }
    private void showPopup() {
        AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
        alert.setMessage("Are you sure?")
                .setPositiveButton("Logout", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {

                        logout(); // Last step. Logout function

                    }

                    private void logout() {

                        finish();
                    }


                }).setNegativeButton("Cancel", null);

        AlertDialog alert1 = alert.create();
        alert1.show();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_all_products:
                startActivity(new Intent(MainActivity.this,AllProductsActivity.class));
                break;

//            case R.id.nav_about:
//                startActivity(new Intent(MainActivity.this,SettingsActivity.class));
//                break;
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}