package com.azoubel.expensecontrol;

import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.azoubel.expensecontrol.controller.HomeController;
import com.azoubel.expensecontrol.model.User;

import java.util.List;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    List<User> users;
    HomeController controller;

    ConstraintLayout usersLayout;
    ConstraintLayout expensesLayout;
    ConstraintLayout paymentsLayout;

    private static final int usersMenuId = 0;
    private static final int expensesMenuId = 1;
    private static final int paymentsMenuId = 2;

    FloatingActionButton actionButton;
    Menu navigationMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        actionButton = (FloatingActionButton) findViewById(R.id.fab);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        CoordinatorLayout appBarHome = findViewById(R.id.app_bar);

        usersLayout = appBarHome.findViewById(R.id.home_users);

        expensesLayout = appBarHome.findViewById(R.id.home_expenses);

        paymentsLayout = appBarHome.findViewById(R.id.home_payments);

        if(controller == null) {
            controller = HomeController.getInstance();
        }

        users = controller.loadUsers(this);

        if(users == null || users.isEmpty()) {
            usersLayout.setVisibility(View.VISIBLE);
        }

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);

        navigationView.setNavigationItemSelectedListener(this);

        navigationMenu = navigationView.getMenu();

        addMenuItens(navigationMenu);
        updateFloatButton();
    }

    private void addMenuItens(Menu naviMenu) {

        MenuItem usersItem = naviMenu.add( R.id.menuOptions, Menu.NONE, usersMenuId,"usuários");
        usersItem.setIcon(R.drawable.ic_menu_camera);

        MenuItem expensesItem = naviMenu.add( R.id.menuOptions, Menu.NONE, expensesMenuId,"gastos");
        expensesItem.setIcon(R.drawable.ic_menu_camera);

        MenuItem paymentsItem = naviMenu.add( R.id.menuOptions, Menu.NONE, paymentsMenuId,"pagamentos");
        paymentsItem.setIcon(R.drawable.ic_menu_camera);

        addUsersMenu(naviMenu);

    }

    private void addUsersMenu(Menu naviMenu) {
        if(users != null && !users.isEmpty()) {
            for (User user : users) {
                MenuItem usersItem = naviMenu.add( R.id.menuUsers, Menu.NONE, user.getUserId(), user.getName());
                usersItem.setIcon(getUserIcon(user.getImage()));
            }
        }
        else {

        }
    }

   private int getUserIcon(int userImage) {
        return R.drawable.ic_menu_share;
    }

    private void updateFloatButton() {
        actionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

   /* @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }*/

    /*@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }*/

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.

        CharSequence title = item.getTitle();

        if(title.equals("dead")) {
            Toast.makeText(this, "clicked on dead item!!", Toast.LENGTH_SHORT).show();
        }
        /*int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);*/
        return true;
    }

}
