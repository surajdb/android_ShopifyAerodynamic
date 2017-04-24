package com.suraj.shopifyaerodynamic.Screens;

import android.app.FragmentManager;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import com.suraj.shopifyaerodynamic.Adapter.OrderAdapter;
import com.suraj.shopifyaerodynamic.Model.ItemModel;
import com.suraj.shopifyaerodynamic.R;

import java.util.List;

public class OrderList extends AppCompatActivity  {

    private OrderAdapter orderAdapter;
    private ListView orderListView;
    private EditText txtSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_list);
        Intent intent = getIntent();
        final List<ItemModel.Items> orderList = intent.getParcelableArrayListExtra("map");

        txtSearch = (EditText) findViewById(R.id.txtSearch);
        orderListView = (ListView) findViewById(R.id.orderListView);
        // To remove focus of keyboard on activity start
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        // Get the data
        orderAdapter = new OrderAdapter(this, R.layout.order_list_item, orderList);
        orderListView.setAdapter(orderAdapter);

        orderListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
               // testing  Toast.makeText(getApplicationContext(),"Click Working", Toast.LENGTH_SHORT).show();

                ItemModel.Items model = (ItemModel.Items) adapterView.getAdapter().getItem(position);//.getItemAtPosition(position);
                Bundle bundle = new Bundle();
                bundle.putParcelable("order", model);

                //open the fragment
                FragmentManager fm = getFragmentManager();
                MyDialogFragment dialogFragment = new MyDialogFragment ();
                dialogFragment.show(fm, "Order Details");
                dialogFragment.setArguments(bundle);
            }
        });

        txtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //OrderList.this.orderAdapter.getFilter().filter(s.toString());
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                OrderList.this.orderAdapter.getFilter().filter(s.toString());
            }
        });
    }
}
