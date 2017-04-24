package com.suraj.shopifyaerodynamic;

import android.app.FragmentManager;
import android.content.Intent;
import android.provider.SyncStateContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Filterable;
import android.widget.ListView;

import com.suraj.shopifyaerodynamic.Adapter.OrderAdapter;
import com.suraj.shopifyaerodynamic.Model.ItemModel;

import java.util.List;
import java.util.logging.Filter;

public class OrderList extends AppCompatActivity  {

    private OrderAdapter orderAdapter;
    private ListView orderListView;

    //private EditText search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_list);
        Intent intent = getIntent();
        final List<ItemModel.Items> orderList = intent.getParcelableArrayListExtra("map");

       // search = (EditText) findViewById(R.id.search);
        orderListView = (ListView) findViewById(R.id.orderListView);

        // Get the data
        orderAdapter = new OrderAdapter(this, R.layout.order_list_item, orderList);
        orderListView.setAdapter(orderAdapter);

        orderListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               // testing  Toast.makeText(getApplicationContext(),"Click Working", Toast.LENGTH_SHORT).show();

                Bundle bundle = new Bundle();
                bundle.putParcelable("order", orderList.get(position));
                //open the fragment
                FragmentManager fm = getFragmentManager();
                MyDialogFragment dialogFragment = new MyDialogFragment ();
                dialogFragment.show(fm, "Order Details");
                dialogFragment.setArguments(bundle);
            }
        });

//        search.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//               // OrderList.this.orderAdapter.getFilter().filter(s);
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//                OrderList.this.orderAdapter.getFilter().filter(s);
//            }
//        });


    }

}
