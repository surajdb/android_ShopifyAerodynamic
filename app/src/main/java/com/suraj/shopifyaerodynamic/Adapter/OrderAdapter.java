package com.suraj.shopifyaerodynamic.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.suraj.shopifyaerodynamic.Model.ItemModel;
import com.suraj.shopifyaerodynamic.R;

import java.util.List;

public class OrderAdapter extends ArrayAdapter<ItemModel.Items> {
    Context context;
    List<ItemModel.Items> orderList;
    int layoutResID;
    TextView txtOrderNumber;
    TextView txtOrderDate;

    public OrderAdapter(Context context, int resource, List<ItemModel.Items> listItems) {
        super(context, resource, listItems);
        this.context = context;
        this.orderList = listItems;
        this.layoutResID = resource;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(getContext()).
                inflate(R.layout.order_list_item, parent, false);

        ItemModel.Items order = orderList.get(position);

        txtOrderNumber = (TextView) convertView.findViewById(R.id.txtOrderNumber);
        txtOrderDate = (TextView) convertView.findViewById(R.id.txtOrderDate);

        txtOrderNumber.setText(order.getorderNumber().toString());
        txtOrderDate.setText(order.getOrderCreatedAt().toString());
        return convertView;
    }

}
