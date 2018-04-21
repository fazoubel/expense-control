package com.azoubel.expensecontrol;

import android.content.Intent;
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

import com.azoubel.expensecontrol.controller.Controller;
import com.azoubel.expensecontrol.model.Address;
import com.azoubel.expensecontrol.model.Expense;
import com.azoubel.expensecontrol.model.ExpenseCategory;
import com.azoubel.expensecontrol.model.Payment;
import com.azoubel.expensecontrol.model.PaymentWay;
import com.azoubel.expensecontrol.model.User.Person;
import com.azoubel.expensecontrol.model.User.User;
import com.azoubel.expensecontrol.ui.UserActivity;
import com.azoubel.expensecontrol.ui.view.ExpensesView;
import com.azoubel.expensecontrol.ui.view.PaymentsView;
import com.azoubel.expensecontrol.ui.view.UsersView;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, ExpensesView.ExpenseClickListener, UsersView.UserClickListener {

    private static final int SHOW_USERS_VIEW = 0;
    private static final int SHOW_EXPENSES_VIEW = 1;
    private static final int SHOW_PAYMENTS_VIEW = 2;

    private static Controller controller;
    private List<User> users;

    private List<Expense> expenses;
    private UsersView usersView;
    private ExpensesView expensesView;

    private PaymentsView paymentsView;
    private FloatingActionButton addButton;
    private FloatingActionButton editButton;
    private FloatingActionButton openButton;

    private Menu navigationMenu;

    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        addButton = (FloatingActionButton) findViewById(R.id.add_button);
        editButton = (FloatingActionButton) findViewById(R.id.edit_button);
        openButton = (FloatingActionButton) findViewById(R.id.open_button);

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
            controller = new Controller();
        }

        users = controller.loadUsers(this);

        usersView.setData(users, this);

        usersView.setUserClickListener(this);

        showView(SHOW_USERS_VIEW);

        if(users == null || users.isEmpty()) {

            controller.addAddress(this, "rua alemanha", 102, "rio doce","olinda", "pernambuco", "brazil", "11111-111");

            controller.addAddress(this, "rua chicago", 102, "rio doce","camaragibe", "pernambuco", "brazil", "22222-222");

            controller.addAddress(this, "avenida mexico", 102, "rio doce","paulista", "pernambuco", "brazil", "33333-333");

            Address address1 = controller.findAddress(this, "rua alemanha", 102, "rio doce");

            Address address2 = controller.findAddress(this, "rua chicago", 102, "rio doce");

            controller.addPerson(this, "fernando", "oliveira", "fernando",
                    "111111111111", new Date().getTime(), 0, 1000, 0, address1);

            controller.addPerson(this, "thiago", "lopes", "lopes",
                    "222222222222", new Date().getTime(), 0, 1000, 0, address2);

            controller.addPerson(this, "caliane", "figueredo", "caliane",
                    "3333333333", new Date().getTime(), 0, 1000, 0, address2);

            users = controller.loadUsers(this);

            usersView.setData(users, this);

            usersView.setUserClickListener(this);

            showView(SHOW_USERS_VIEW);

            controller.addStore(this, "loja tartaruga", 1, "tartaruga.com", "loja sobre tartarugas");

            controller.addStore(this, "loja suelene", 2, "suelene.com", "loja sobre babozeiras");

            Calendar calendar = Calendar.getInstance();

            calendar.add(Calendar.DATE, 2);

            Date expirationDate = calendar.getTime();

            controller.addExpense(this, users.get(0).getUserId(), 1, 55.04f, expirationDate.getTime(),
                    "compra de uma tartaruga ninja", ExpenseCategory.compra, 0);

            calendar.add(Calendar.DATE, 1);

            Date expirationDate2 = calendar.getTime();

            controller.addExpense(this, users.get(1).getUserId(), 1, 105.00f, expirationDate2.getTime(),
                    "compra de um cágado", ExpenseCategory.compra, 5.5f);

            List<Expense> expenseList = controller.findExpenseByUser(this, users.get(0).getUserId(), getStartDate(), getEndDate());

            controller.addPayment(this, users.get(0).getUserId(), expenseList.get(0).getExpenseId(), PaymentWay.dinheiro,
                    expenseList.get(0).getInitialValue(), "");

        }
        /*else {
            long startDate = getStartDate();
            long endDate = getEndDate();
            expenses = controller.findExpenseByUser(this, users.get(0).getUserId(), startDate, endDate);
            showView(SHOW_EXPENSES_VIEW);
        }*/

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
                if(user instanceof Person) {
                    Person person = (Person) user;
                    MenuItem usersItem = naviMenu.add( R.id.menuUsers, user.getUserId(), Menu.NONE, person.getFirstName());
                    usersItem.setIcon(getUserIcon(user.getImage()));
                    usersItem.setCheckable(true);
                }
            }
        }
    }

    private int getUserIcon(int userImage) {
        return R.drawable.ic_menu_share;
    }

    private void updateFloatButton() {
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent startNewPersonActivityIntent = new Intent(HomeActivity.this, UserActivity.class);
                startActivity(startNewPersonActivityIntent);
            }
        });

        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User selectedUser = usersView.getSelectedUser();
                if(selectedUser != null) {
                    Intent startNewPersonActivityIntent = new Intent(HomeActivity.this, UserActivity.class);
                    startNewPersonActivityIntent.putExtra("id", selectedUser.getUserId());
                    startActivity(startNewPersonActivityIntent);
                }
            }
        });

        openButton.setOnClickListener(new View.OnClickListener() {
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
            if(paymentsView.getVisibility() == View.VISIBLE) {
                showView(SHOW_EXPENSES_VIEW);
            }
            else if(expensesView.getVisibility() == View.VISIBLE) {
                showView(SHOW_USERS_VIEW);
            }
            else {
                super.onBackPressed();
            }
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.nav_users) {
            showView(SHOW_USERS_VIEW);
        } else if (id == R.id.nav_stores) {

        } /* else if (id == R.id.nav_payments) {

        }*/ else {
            for (User user : users) {
                if(id == user.getUserId()) {
                    List<Expense> expenseList = controller.findExpenseByUser(this, user.getUserId(),
                            getStartDate(), getEndDate());

                    expensesView.setData(expenseList, this);
                    expensesView.setExpenseClickListener(this);

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

    @Override
    public void onExpenseClicked(int expenseId) {
        List<Payment> paymentList = controller.findPaymentsByExpense(this, expenseId);
        paymentsView.setData(paymentList, this);
        showView(SHOW_PAYMENTS_VIEW);
    }

    @Override
    public void onUserClicked(int userId) {
        /*List<Expense> expenseList = controller.findExpenseByUser(this, userId, getStartDate(), getEndDate());
        expensesView.setData(expenseList, this);
        expensesView.setExpenseClickListener(this);
        showView(SHOW_EXPENSES_VIEW);*/
    }
}
