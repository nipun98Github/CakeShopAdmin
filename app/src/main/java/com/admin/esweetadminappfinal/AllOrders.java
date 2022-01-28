package com.admin.esweetadminappfinal;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.admin.esweetadminappfinal.Asynctask.LoginAsyncTask;
import com.admin.esweetadminappfinal.Holder.OrderHolder;
import com.admin.esweetadminappfinal.Model.Customer;
import com.admin.esweetadminappfinal.Model.FCmClient;
import com.admin.esweetadminappfinal.Model.Invoice;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class AllOrders extends AppCompatActivity {

    private Toolbar toolbar;
    private Button ViewPendingOrdersBtn;
    private CollectionReference orderCollection;
    private CollectionReference customercollection;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    private RecyclerView recyclerView;
    private Spinner spinner;
    private EditText startDate,endDate;
    private ImageView refresh;
    FCmClient myfcmClient;

    private int mYear, mMonth, mDay, mHour, mMinute;


    private FirestoreRecyclerAdapter<Invoice, OrderHolder> fsorderAdapter;
    private String docid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_orders);
        toolbar = findViewById(R.id.toolbar);
        startDate = findViewById(R.id.date_start);
        refresh = findViewById(R.id.refresh_orderlist);
        spinner = findViewById(R.id.spinner_order_status);
        setSupportActionBar(toolbar);

//        ViewPendingOrdersBtn = findViewById(R.id.view_pending_orders_btn);


        orderCollection = db.collection("Invoice");
        customercollection = db.collection("Customers");

        recyclerView = findViewById(R.id.orderlist_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(AllOrders.this));

        setSpinner();

        setAdepter("All");

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                startDate.setText("");
                String selectedItem = spinner.getSelectedItem().toString();
                if (selectedItem.equals("Pending")){
                    setAdepter("Pending");
                }else if (selectedItem.equals("Delivered")){
                    setAdepter("Delivered");
                }else if (selectedItem.equals("Received")){
                    setAdepter("Received");
                }else{
                    setAdepter("All");
                }
            }



            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        startDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);


                DatePickerDialog datePickerDialog = new DatePickerDialog(AllOrders.this,
                        new DatePickerDialog.OnDateSetListener() {



                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {

                                startDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                                setAdepter("time");

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });



    }

    private void setAdepter(String all) {
db.collection("Invoice").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
    @Override
    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
        List<Invoice> invoices = queryDocumentSnapshots.toObjects(Invoice.class);
        if (invoices.size() > 0) {
            Invoice invoice = invoices.get(0);
            docid = invoice.getCustomerDocId();

        Query loadInvoice = orderCollection.whereEqualTo("userDocId",docid);
        if (all.equals("All")){
            loadInvoice = orderCollection.whereEqualTo("customerDocId",docid);
        }else if (all.equals("Pending")){
            loadInvoice = orderCollection.whereEqualTo("customerDocId",docid).whereEqualTo("status","ordered");
        }else if (all.equals("Delivered")){
            loadInvoice = orderCollection.whereEqualTo("customerDocId",docid).whereEqualTo("status","delivered");
        }else if (all.equals("Received")){
            loadInvoice = orderCollection.whereEqualTo("customerDocId",docid).whereEqualTo("status","received");
        }else{
            loadInvoice = orderCollection.whereEqualTo("customerDocId",docid).whereGreaterThanOrEqualTo("date",startDate.getText().toString());
        }

        FirestoreRecyclerOptions recyclerOptions = new FirestoreRecyclerOptions.Builder<Invoice>().setQuery(loadInvoice,Invoice.class).build();
        fsorderAdapter = new FirestoreRecyclerAdapter<Invoice,OrderHolder>(recyclerOptions){

            @NonNull
            @Override
            public OrderHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.orderhistory_list_item,parent,false);
                return new OrderHolder(view);
            }

            @Override
            protected void onBindViewHolder(@NonNull OrderHolder holder, int position, @NonNull Invoice model) {
                String id = getSnapshots().getSnapshot(position).getId();
                holder.orderId.setText("Order Id:"+ id);
                holder.orderdate.setText("Order On:" + model.getDate()+"/"+model.getTime());
                holder.orderamount.setText("Total Amount: Rs." +model.getSubTotal());

                if (model.getStatus().equals("ordered")){
                    holder.orderS.setText("Pending..");
                    holder.orderS.setTextColor(Color.parseColor("#DC0000"));
                    //  holder.orderS.setTextColor(Color.red(R.color.red));
                    holder.status.setImageResource(R.drawable.ic_pending);
                    holder.recieve.setImageResource(R.drawable.ic_order_now);
                    holder.recieve.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            recievedOrder(id,v);
                        }
                    });
                }else if (model.getStatus().equals("delivered")){

                    holder.orderS.setText("Delivered..");
                    holder.orderS.setTextColor(Color.parseColor("#14AA09"));
                    holder.status.setImageResource(R.drawable.ic_check);
                    holder.recieve.setOnClickListener(null);

                }else if (model.getStatus().equals("received")){
                    holder.orderS.setText("Received..");
                    holder.status.setImageResource(R.drawable.ic_check);
                    holder.orderS.setTextColor(Color.parseColor("#000000"));
                    holder.recieve.setOnClickListener(null);
                }

                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
//                        Intent invoice = new Intent(OrderHistory.this, InvoiceActivity.class);
//                        invoice.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                        invoice.putExtra("invoiceId",id);
//                        startActivity(invoice);
                    }
                });

            }
        };

        //set Adapter
        recyclerView.setAdapter(fsorderAdapter);
        fsorderAdapter.startListening();
        }
}});

    }

    private void recievedOrder(String id,View v) {
        orderCollection.document(id).update("status","delivered").addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                orderCollection.document(id).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        Invoice in = documentSnapshot.toObject(Invoice.class);
                        customercollection.document(in.getCustomerDocId()).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                            @Override
                            public void onSuccess(DocumentSnapshot documentSnapshot) {
                                Customer customer = documentSnapshot.toObject(Customer.class);
                                String fcmId = documentSnapshot.getString("fcmId");
                                Log.d("fcmIdfcmId......",fcmId);
                                myfcmClient.execute(fcmId,customer.getEmail(),id+" have been delivered");
                                //new LoginAsyncTask(v).execute(customer.getEmail(),id);

                        Toast.makeText(AllOrders.this, "Order delivered successfully", Toast.LENGTH_SHORT).show();
                            }
                        });

                    }
                });

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        myfcmClient = new FCmClient();
    }

    private void setSpinner() {
        String[] status = {"All","Pending","Delivered","Received"};
        ArrayAdapter vListAdapter = new ArrayAdapter(AllOrders.this,android.R.layout.simple_selectable_list_item,status);
        spinner.setAdapter(vListAdapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.example_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        switch (item.getItemId()) {
            case R.id.nav_dashboard_icon:
                startActivity(new Intent(AllOrders.this, MainActivity.class));
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}