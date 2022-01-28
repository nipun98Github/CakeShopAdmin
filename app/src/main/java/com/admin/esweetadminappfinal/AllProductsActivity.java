package com.admin.esweetadminappfinal;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.google.android.gms.tasks.OnCompleteListener;
import com.admin.esweetadminappfinal.Model.Product;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;

import java.util.List;

public class AllProductsActivity extends AppCompatActivity {

    private Toolbar toolbar;

    String category_name,brand_name,price;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    public FirebaseStorage storage;
    TableLayout table;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_products);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        toolbar = findViewById(R.id.toolbar);
        table = findViewById(R.id.table_layout);

        setSupportActionBar(toolbar);



        db.collection("Products").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                List<Product> products= queryDocumentSnapshots.toObjects(Product.class);

                for(int i=0;i<products.size();i++) {

                    DocumentSnapshot doc = queryDocumentSnapshots.getDocuments().get(i);
                    String product_Id = doc.getId();
                    Product product = doc.toObject(Product.class);
                    TableRow row=new TableRow(AllProductsActivity.this);


                    TextView procategory=new TextView(AllProductsActivity.this);
                    TextView proname=new TextView(AllProductsActivity.this);
//                    TextView proCategory=new TextView(AllProductsActivity.this);
                    TextView proPrice=new TextView(AllProductsActivity.this);


                    procategory.setText(product.getProductCategoryName());
                    proname.setText(product.getProductName());
//                    proCategory.setText(product.getCategory());
                    proPrice.setText(String.valueOf(product.getProductPrice()));



                    row.addView(procategory);
                    row.addView(proname);

                    row.addView(proPrice);
                    table.addView(row);
                }
            }
        });

    }
    }


