package com.suraj.shopifyaerodynamic;

import android.app.FragmentManager;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.suraj.shopifyaerodynamic.Adapter.OrderAdapter;
import com.suraj.shopifyaerodynamic.Model.ItemModel;

import java.util.List;

public class OrderList extends AppCompatActivity  {

    private OrderAdapter orderAdapter;
    private ListView orderListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_list);
        Intent intent = getIntent();
        final List<ItemModel.Items> orderList = intent.getParcelableArrayListExtra("map");

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

                FragmentManager fm = getFragmentManager();
                MyDialogFragment dialogFragment = new MyDialogFragment ();
                dialogFragment.show(fm, "Order Details");
                dialogFragment.setArguments(bundle);
            }
        });
    }
}
