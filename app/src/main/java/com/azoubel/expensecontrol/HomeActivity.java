package com.azoubel.expensecontrol;

import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
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
import com.azoubel.expensecontrol.model.CreditCard;
import com.azoubel.expensecontrol.model.Expense;
import com.azoubel.expensecontrol.model.ExpenseCategory;
import com.azoubel.expensecontrol.model.Payment;
import com.azoubel.expensecontrol.model.PaymentWay;
import com.azoubel.expensecontrol.model.Store;
import com.azoubel.expensecontrol.model.User.Person;
import com.azoubel.expensecontrol.model.User.User;
import com.azoubel.expensecontrol.ui.ExpensesActivity;
import com.azoubel.expensecontrol.ui.PaymentsActivity;
import com.azoubel.expensecontrol.ui.StoreActivity;
import com.azoubel.expensecontrol.ui.UserActivity;
import com.azoubel.expensecontrol.ui.view.ExpensesView;
import com.azoubel.expensecontrol.ui.view.PaymentsView;
import com.azoubel.expensecontrol.ui.view.StoreView;
import com.azoubel.expensecontrol.ui.view.UsersView;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static final int SHOW_USERS_VIEW = 0;
    private static final int SHOW_EXPENSES_VIEW = 1;
    private static final int SHOW_PAYMENTS_VIEW = 2;
    private static final int SHOW_STORES_VIEW = 3;
    private static final int SHOW_PROMOTIONS_VIEW = 4;

    private static Controller controller;
    private List<User> users;

    private UsersView usersView;
    private ExpensesView expensesView;
    private StoreView storesView;

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

        storesView = appBarHome.findViewById(R.id.stores_view_layout);

        if(controller == null) {
            controller = new Controller();
        }

        users = controller.loadAllPersons(this);

        usersView.setData(users, this);

        changeView(SHOW_USERS_VIEW);

        if(users == null || users.isEmpty()) {

            Address address1 = new Address();
            address1.setStreet("rua alemanha");
            address1.setNumber(102);
            address1.setNeighborhood("rio doce");
            address1.setCity("olinda");
            address1.setState("pernambuco");
            address1.setCountry("brazil");
            address1.setComplement("11111-111");
            //address1.setReference();
            //address1.setApartment();
            //address1.setApartmentBlock();

            controller.addAddress(this, address1);

            Address address2 = new Address();
            address2.setStreet("rua chicago");
            address2.setNumber(2052);
            address2.setNeighborhood("Graças");
            address2.setCity("Recife");
            address2.setState("pernambuco");
            address2.setCountry("brazil");
            address2.setComplement("22222-222");
            address2.setReference("perto do longe");
            address2.setApartment(1345);
            address2.setApartmentBlock("D");

            controller.addAddress(this, address2);

            //controller.addAddress(this, "avenida mexico", 102, "rio doce","paulista", "pernambuco", "brazil", "33333-333");

            address1 = controller.findAddress(this, "rua alemanha", 102, "rio doce");

            address2 = controller.findAddress(this, "rua chicago", 102, "rio doce");

            Person person1 = new Person();
            person1.setFirstName("fernando");
            person1.setLastName("oliveira");
            person1.setNickName("nando");
            person1.setPhoneNumber("111111111111");
            person1.setBirthday(new Date());
            person1.setSex(Person.SEX_MALE);
            person1.setImage(0);
            person1.setExpectedExpensesValue(500f);
            person1.setAddress(address1);

            controller.addPerson(this, person1);

            Person person2 = new Person();
            person2.setFirstName("thiago");
            person2.setLastName("lopes");
            person2.setNickName("txubaca");
            person2.setPhoneNumber("222222222222");
            person2.setBirthday(new Date());
            person2.setSex(Person.SEX_MALE);
            person2.setImage(0);
            person2.setExpectedExpensesValue(1500f);
            person2.setAddress(address2);

            controller.addPerson(this, person2);

            Person person3 = new Person();
            person3.setFirstName("caliane");
            person3.setLastName("figueredo");
            person3.setNickName("iemanja");
            person3.setPhoneNumber("3333333333");
            person3.setBirthday(new Date());
            person3.setSex(Person.SEX_FEMALE);
            person3.setImage(0);
            person1.setExpectedExpensesValue(5000f);
            person3.setAddress(address2);

            controller.addPerson(this, person3);

            users = controller.loadAllPersons(this);

            usersView.setData(users, this);

            changeView(SHOW_USERS_VIEW);

            Store store1 = new Store();
            store1.setStoreName("loja tartaruga");
            store1.setSite("tartarugas.com.br");
            store1.setDescription("loja de tartarugas");
            store1.setProductType("animais");
            store1.setPhoneNumber("12321312312");
            store1.setEmail("lojatartaruga@gmail.com");
            store1.setManagerName("Olavo");
            store1.setManagerPhoneNumber("123123123");
            store1.setManagerEmail("olavogerente@gmail.com");
            store1.setAddress(address1);

            controller.addStore(this, store1);

            Store store2 = new Store();
            store2.setStoreName("loja abobrinha");
            store2.setSite("abobrinhas.com.br");
            store2.setDescription("loja de abobrinha");
            store2.setProductType("abobrinhas");
            store2.setPhoneNumber("9999999999999");
            store2.setEmail("lojaabobrinhas@gmail.com");
            store2.setManagerName("Gilmar");
            store2.setManagerPhoneNumber("44444444444");
            store2.setManagerEmail("gilmargerente@gmail.com");
            store2.setAddress(address2);

            controller.addStore(this, store2);

            Calendar calendar = Calendar.getInstance();

            //calendar.add(Calendar.DATE, 2);

            Date expirationDate = calendar.getTime();

            List<Store> stores = controller.getAllStores(this);

            store1=null;
            store2=null;

            if(!stores.isEmpty() && stores.size() >=2) {
                store1 = stores.get(0);
                store2 = stores.get(1);
            }


            controller.addExpense(this, (Person) users.get(0), store1, 55.04f, expirationDate.getTime(),
                    "compra de tartaruga", ExpenseCategory.compra, 0, expirationDate.getTime());

            //calendar.add(Calendar.DATE, 1);

            Date expirationDate2 = calendar.getTime();

            controller.addExpense(this, (Person) users.get(1), store2, 105.00f, expirationDate2.getTime(),
                    "compra de cágado", ExpenseCategory.compra, 5.5f, expirationDate2.getTime());

            List<Expense> expenseList = controller.findExpenseByUser(this, users.get(0).getUserId(), getStartDate(), getEndDate());

            controller.addPayment(this, (Person) users.get(0), expenseList.get(0), PaymentWay.dinheiro,
                    expenseList.get(0).getInitialValue()/2, "");

            CreditCard creditCard1 = new CreditCard();
            creditCard1.setFlag("visa");
            creditCard1.setExpiration_date(new Date().toString());
            creditCard1.setNumber("2222-3333-4444-5555");
            creditCard1.setUser(users.get(0));

            controller.addCreditCard(this, creditCard1);

            controller.addPayment(this, (Person) users.get(0), expenseList.get(0), PaymentWay.cartão_de_crédito,
                    expenseList.get(0).getInitialValue()/2, creditCard1.getNumber());

            CreditCard creditCard2 = new CreditCard();
            creditCard2.setFlag("mastercard");
            creditCard2.setExpiration_date(new Date().toString());
            creditCard2.setNumber("1111-3333-7777-5555");
            creditCard2.setUser(users.get(1));

            controller.addCreditCard(this, creditCard2);

            CreditCard creditCard3 = new CreditCard();
            creditCard3.setFlag("visa");
            creditCard3.setExpiration_date(new Date().toString());
            creditCard3.setNumber("6666-3436-7444-5895");
            creditCard3.setUser(users.get(1));

            controller.addCreditCard(this, creditCard3);

            List<Expense> expenseList2 = controller.findExpenseByUser(this, users.get(1).getUserId(), getStartDate(), getEndDate());

            controller.addPayment(this, (Person) users.get(0), expenseList2.get(0), PaymentWay.cartão_de_crédito,
                    expenseList2.get(0).getInitialValue()/3, creditCard1.getNumber());

            controller.addPayment(this, (Person) users.get(1), expenseList2.get(0), PaymentWay.cartão_de_crédito,
                    expenseList2.get(0).getInitialValue()/3, creditCard2.getNumber());

            controller.addPayment(this, (Person) users.get(1), expenseList2.get(0), PaymentWay.cartão_de_crédito,
                    expenseList2.get(0).getInitialValue()/3, creditCard3.getNumber());

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
                if(usersView.getVisibility() == View.VISIBLE) {
                    Intent startUserActivityIntent = new Intent(HomeActivity.this, UserActivity.class);
                    startActivity(startUserActivityIntent);
                }
                else if (expensesView.getVisibility() == View.VISIBLE) {
                    Intent startExpensesActivityIntent = new Intent(HomeActivity.this, ExpensesActivity.class);
                    startActivity(startExpensesActivityIntent);
                }
                else {
                    Intent startPaymentsActivityIntent = new Intent(HomeActivity.this, PaymentsActivity.class);
                    startPaymentsActivityIntent.putExtra("expense_id", paymentsView.getExpense().getExpenseId());
                    startActivity(startPaymentsActivityIntent);
                }
            }
        });

        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            if(usersView.getVisibility() == View.VISIBLE) {
                User selectedUser = usersView.getSelectedUser();
                if(selectedUser != null) {
                    Intent startNewPersonActivityIntent = new Intent(HomeActivity.this, UserActivity.class);
                    startNewPersonActivityIntent.putExtra("id", selectedUser.getUserId());
                    startActivity(startNewPersonActivityIntent);
                }
            }
            else if (expensesView.getVisibility() == View.VISIBLE) {
                Expense selectedExpense = expensesView.getSelectedExpense();
                if(selectedExpense != null) {
                    Intent expensesActivityIntent = new Intent(HomeActivity.this, ExpensesActivity.class);
                    expensesActivityIntent.putExtra("id", selectedExpense.getExpenseId());
                    startActivity(expensesActivityIntent);
                }
            }
            else if (paymentsView.getVisibility() == View.VISIBLE) {
                Payment selectedPayment = paymentsView.getSelectedPayment();
                if(selectedPayment != null) {
                    Intent paymentsActivityIntent = new Intent(HomeActivity.this, PaymentsActivity.class);
                    paymentsActivityIntent.putExtra("id", selectedPayment.getPaymentId());
                    startActivity(paymentsActivityIntent);
                }
            }
            else if(storesView.getVisibility() == View.VISIBLE) {
                Store selectedStore = storesView.getSelectedStore();
                if(selectedStore != null) {
                    Intent storeActivityIntent = new Intent(HomeActivity.this, StoreActivity.class);
                    storeActivityIntent.putExtra("id", selectedStore.getStoreId());
                    startActivity(storeActivityIntent);
                }
            }

            }
        });

        openButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            if(usersView.getVisibility() == View.VISIBLE) {
                User selectedUser = usersView.getSelectedUser();
                if(selectedUser != null) {
                    List<Expense> expenseList = controller.findExpenseByUser(HomeActivity.this, selectedUser.getUserId(),
                            getStartDate(), getEndDate());

                    expensesView.setData(expenseList, HomeActivity.this);
                    changeView(SHOW_EXPENSES_VIEW);
                }
            }
            else if (expensesView.getVisibility() == View.VISIBLE) {
                Expense selectedExpense = expensesView.getSelectedExpense();
                if(selectedExpense != null) {
                    List<Payment> paymentList = controller.findPaymentsByExpense(HomeActivity.this, selectedExpense.getExpenseId());
                    paymentsView.setData(paymentList, HomeActivity.this);
                    paymentsView.setExpense(expensesView.getSelectedExpense());
                    changeView(SHOW_PAYMENTS_VIEW);
                }
            }
            }
        });
    }

    @Override
    public void onBackPressed() {

        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            if(paymentsView.getVisibility() == View.VISIBLE) {
                changeView(SHOW_EXPENSES_VIEW);
            }
            else if(expensesView.getVisibility() == View.VISIBLE) {
                changeView(SHOW_USERS_VIEW);
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
            changeView(SHOW_USERS_VIEW);
        }
        else if (id == R.id.nav_stores) {
            List<Store> storeList = controller.getAllStores(this);
            storesView.setData(storeList, this);
            changeView(SHOW_STORES_VIEW);
        }
        else if (id == R.id.nav_promotions) {
            changeView(SHOW_PROMOTIONS_VIEW);
        }
        else {
            for (User user : users) {
                if(id == user.getUserId()) {
                    List<Expense> expenseList = controller.findExpenseByUser(this, user.getUserId(),
                            getStartDate(), getEndDate());

                    expensesView.setData(expenseList, this);

                    changeView(SHOW_EXPENSES_VIEW);
                    break;
                }
            }
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void changeView(int view) {

        usersView.clearSelected();
        expensesView.clearSelected();
        paymentsView.clearSelected();
        storesView.clearSelected();

        if(view == SHOW_USERS_VIEW) {
            usersView.setVisibility(View.VISIBLE);
            expensesView.setVisibility(View.GONE);
            paymentsView.setVisibility(View.GONE);
            storesView.setVisibility(View.GONE);
        }
        else if(view == SHOW_EXPENSES_VIEW) {
            usersView.setVisibility(View.GONE);
            expensesView.setVisibility(View.VISIBLE);
            paymentsView.setVisibility(View.GONE);
            storesView.setVisibility(View.GONE);
        }
        else if(view == SHOW_PAYMENTS_VIEW){
            usersView.setVisibility(View.GONE);
            expensesView.setVisibility(View.GONE);
            paymentsView.setVisibility(View.VISIBLE);
            storesView.setVisibility(View.GONE);
        }
        else if(view == SHOW_STORES_VIEW){
            usersView.setVisibility(View.GONE);
            expensesView.setVisibility(View.GONE);
            paymentsView.setVisibility(View.GONE);
            storesView.setVisibility(View.VISIBLE);
        }
        else {
            //promotions
        }
    }

}
