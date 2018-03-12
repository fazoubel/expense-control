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
import com.azoubel.expensecontrol.model.Expense;
import com.azoubel.expensecontrol.model.User;
import com.azoubel.expensecontrol.ui.view.ExpensesView;
import com.azoubel.expensecontrol.ui.view.PaymentsView;
import com.azoubel.expensecontrol.ui.view.UsersView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    List<User> users;
    List<Expense> expenses;
    HomeController controller;

    UsersView usersView;
    ExpensesView expensesView;
    PaymentsView paymentsView;

    FloatingActionButton actionButton;
    Menu navigationMenu;

    DrawerLayout drawer;

    private static final int SHOW_USERS_VIEW = 0;
    private static final int SHOW_EXPENSES_VIEW = 1;
    private static final int SHOW_PAYMENTS_VIEW = 2;

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

        usersView = appBarHome.findViewById(R.id.users_view_layout);

        expensesView = appBarHome.findViewById(R.id.expenses_view_layout);

        paymentsView = appBarHome.findViewById(R.id.payments_view_layout);

        if(controller == null) {
            controller = HomeController.getInstance();
        }

        users = controller.loadUsers(this);

        usersView.setData(users, this);

        showView(SHOW_USERS_VIEW);

        if(users == null || users.isEmpty()) {

            controller.insertUser(this, "fernando oliveira", "11111111111", (byte) 0, 0);

            controller.insertUser(this, "Suelene Maria", "11111111111", (byte) 0, 0);

            controller.insertUser(this, "Edipo Araujo", "11111111111", (byte) 0, 0);

            users = controller.loadUsers(this);

            usersView.setData(users, this);

            showView(SHOW_USERS_VIEW);

        }
        else {
            long startDate = getStartDate();
            long endDate = getEndDate();
            expenses = controller.findExpenseByUser(this, users.get(0).getUserId(), startDate, endDate);
            showView(SHOW_EXPENSES_VIEW);
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
            for (User user : this.users) {
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

        int id = item.getItemId();

        if (id == R.id.nav_users) {
            showView(SHOW_USERS_VIEW);
        } /*else if (id == R.id.nav_expenses) {

        } else if (id == R.id.nav_payments) {

        }*/ else {
            for (User user : users) {
                if(id == user.getUserId()) {
                    List<Expense> expenseList = controller.findExpenseByUser(this, user.getUserId(),
                            getStartDate(), getEndDate());

                    expensesView.setData(expenseList, this);

                    showView(SHOW_EXPENSES_VIEW);
                    break;
                }
            }
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void showView(int view) {

        //show users view
        if(view == SHOW_USERS_VIEW) {
            usersView.setVisibility(View.VISIBLE);
            expensesView.setVisibility(View.GONE);
            paymentsView.setVisibility(View.GONE);
        }
        else if(view == SHOW_EXPENSES_VIEW) {
            usersView.setVisibility(View.GONE);
            expensesView.setVisibility(View.VISIBLE);
            paymentsView.setVisibility(View.GONE);
        }
        else {
            usersView.setVisibility(View.GONE);
            expensesView.setVisibility(View.GONE);
            paymentsView.setVisibility(View.VISIBLE);
        }
    }

}
