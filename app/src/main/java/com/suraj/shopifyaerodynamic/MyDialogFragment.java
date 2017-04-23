package com.suraj.shopifyaerodynamic;

import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.suraj.shopifyaerodynamic.Adapter.OrderAdapter;
import com.suraj.shopifyaerodynamic.Adapter.OrderProductAdapter;
import com.suraj.shopifyaerodynamic.Model.ItemModel;

import java.util.List;

/**
 * Created by Suraj on 4/22/2017.
 */

public class MyDialogFragment extends DialogFragment {
    private  ItemModel.Items orderList;
    private TextView txtOrderNumber;
    private TextView txtOrderUserId;
    private TextView txtOrderCreatedAt;
    private TextView txtOrderTotalPrice;
    private OrderProductAdapter orderProductAdapter;
    private ListView productListView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_sample_dialog, container, false);
        orderList = getArguments().getParcelable("order");
        getDialog().setTitle("Order Details");

        Button dismiss     = (Button) rootView.findViewById(R.id.dismiss);
        txtOrderNumber     =  (TextView) rootView.findViewById(R.id.txtOrderNumber);
        txtOrderUserId     =  (TextView) rootView.findViewById(R.id.txtOrderUserId);
        txtOrderCreatedAt  =  (TextView) rootView.findViewById(R.id.txtOrderCreatedAt);
        txtOrderTotalPrice =  (TextView) rootView.findViewById(R.id.txtOrderTotalPrice);
        productListView    = (ListView) rootView.findViewById(R.id.productListView);

        int a = productListView.getId();
        txtOrderNumber.setText(orderList.getorderNumber());
        txtOrderUserId.setText(orderList.getorderuserId());
        txtOrderCreatedAt.setText(orderList.getOrderCreatedAt());
        txtOrderTotalPrice.setText(orderList.getOrderTotalPrice());


        orderProductAdapter = new OrderProductAdapter(getActivity(), R.layout.product_list_item, orderList.getorderLineItems());
        productListView.setAdapter(orderProductAdapter);

        dismiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        return rootView;
    }
}
