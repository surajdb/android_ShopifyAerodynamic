package com.suraj.shopifyaerodynamic.Adapter;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.suraj.shopifyaerodynamic.Model.ItemModel;
import com.suraj.shopifyaerodynamic.R;

import java.util.List;

/**
 * Created by Suraj on 4/22/2017.
 */

public class OrderProductAdapter extends ArrayAdapter<ItemModel.Items.LineItems> {

    Context context;
    List<ItemModel.Items.LineItems> productList;
    int layoutResID;
    TextView txtProductId;
    TextView txtProductName;
    TextView txtProductQuantity;

    public OrderProductAdapter(Context context, int resource, List<ItemModel.Items.LineItems> listItems) {
        super(context, resource, listItems);
        this.context = context;
        this.productList = listItems;
        this.layoutResID = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(getContext()).
                inflate(R.layout.product_list_item, parent, false);

        ItemModel.Items.LineItems products = productList.get(position);

        txtProductId = (TextView) convertView.findViewById(R.id.txtProductId);
        txtProductName = (TextView) convertView.findViewById(R.id.txtProductName);
        txtProductQuantity = (TextView) convertView.findViewById(R.id.txtProductQuantity);

        txtProductId.setText(products.getLineItemProductId().toString());
        txtProductName.setText(products.getLineItemName().toString());
        txtProductQuantity.setText(products.getLineItemQuantity().toString());
        return convertView;
    }
}
