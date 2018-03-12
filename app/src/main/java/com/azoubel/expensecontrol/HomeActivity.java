package com.azoubel.expensecontrol;

import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
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

import com.azoubel.expensecontrol.controller.HomeController;
import com.azoubel.expensecontrol.data.model.Expense;
import com.azoubel.expensecontrol.data.model.User;
import com.azoubel.expensecontrol.ui.view.UsersView;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    List<User> users;
    List<Expense> expenses;
    HomeController controller;

    UsersView usersView;
    ConstraintLayout expensesLayout;
    ConstraintLayout paymentsLayout;

    FloatingActionButton actionButton;
    Menu navigationMenu;

    DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        actionButton = (FloatingActionButton) findViewById(R.id.fab);

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        ConstraintLayout appBarHome = findViewById(R.id.app_bar);

        usersView = appBarHome.findViewById(R.id.home_users);

        expensesLayout = appBarHome.findViewById(R.id.home_expenses);

        paymentsLayout = appBarHome.findViewById(R.id.home_payments);

        if(controller == null) {
            controller = HomeController.getInstance();
        }

        users = controller.loadUsers(this);

        usersView.setData(users, this);

        usersView.setVisibility(View.VISIBLE);

        if(users == null || users.isEmpty()) {

            controller.insertUser(this, "fernando oliveira", "11111111111", (byte) 0, 0);

            controller.insertUser(this, "Suelene Maria", "11111111111", (byte) 0, 0);

            controller.insertUser(this, "Edipo Araujo", "11111111111", (byte) 0, 0);

            users = controller.loadUsers(this);

            usersView.setData(users, this);

            usersView.setVisibility(View.VISIBLE);
        }
        else {
            long startDate = getStartDate();
            long endDate = getEndDate();
            expenses = controller.findExpenseByUser(this, users.get(0).getUserId(), startDate, endDate);
            expensesLayout.setVisibility(View.VISIBLE);
        }

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);

        navigationView.setNavigationItemSelectedListener(this);

        navigationMenu = navigationView.getMenu();

        addUsersMenu(navigationMenu);
        updateFloatButton();
    }

    private long getStartDate() {
        Calendar calendar = Calendar.getInstance();
        Date now  = new Date();
        now.setDate(calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
        long startDate = now.getTime();
        return startDate;
    }

    private long getEndDate() {
        Calendar calendar = Calendar.getInstance();
        Date now  = new Date();
        now.setDate(calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        long endDate = now.getTime();
        return endDate;
    }

     private void addUsersMenu(Menu naviMenu) {
        if(users != null && !users.isEmpty()) {
            for (User user : users) {
                MenuItem usersItem = naviMenu.add( R.id.menuUsers, user.getUserId(), Menu.NONE, user.getName());
                usersItem.setIcon(getUserIcon(user.getImage()));
                usersItem.setCheckable(true);
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

        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        CharSequence title = item.getTitle();

        int id = item.getItemId();

        if (id == R.id.nav_users) {
        } else if (id == R.id.nav_expenses) {

        } else if (id == R.id.nav_payments) {

        } else {

        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
