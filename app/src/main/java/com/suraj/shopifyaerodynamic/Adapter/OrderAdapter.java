package com.suraj.shopifyaerodynamic.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.suraj.shopifyaerodynamic.Model.ItemModel;
import com.suraj.shopifyaerodynamic.R;

import java.util.ArrayList;
import java.util.List;

public class OrderAdapter extends ArrayAdapter<ItemModel.Items> implements Filterable {
    Context context;
    List<ItemModel.Items> orderList;
    List<ItemModel.Items> orderFilterList;
    int layoutResID;
    TextView txtOrderNumber;
    TextView txtOrderDate;
    TextView txtOrderQuantity;

    public OrderAdapter(Context context, int resource, List<ItemModel.Items> listItems) {
        super(context, resource, listItems);
        this.context = context;
        this.orderList = listItems;
        this.orderFilterList =listItems;
        this.layoutResID = resource;
    }

    // to update the count and item which will be referred in onItemClicklistner
    @Override
    public int getCount() {
        return orderFilterList.size();
    }

    @Override
    public ItemModel.Items getItem(int i) {
        return orderFilterList.get(i);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(getContext()).
                inflate(R.layout.order_list_item, parent, false);

        if (position >= this.orderFilterList.size()) {
            position = 0;
        }
        ItemModel.Items order = orderFilterList.get(position);

        txtOrderNumber = (TextView) convertView.findViewById(R.id.txtOrderNumber);
        txtOrderDate = (TextView) convertView.findViewById(R.id.txtOrderDate);
        txtOrderQuantity = (TextView) convertView.findViewById(R.id.txtOrderItems);

        txtOrderNumber.setText(order.getorderNumber().toString());
        txtOrderDate.setText(order.getOrderCreatedAt().toString().substring(0,10));
        txtOrderQuantity.setText(Integer.toString(order.getorderLineItems().size()));
        return convertView;
    }

    // Implementing the filter
    @Override
    public Filter getFilter() {
        return new Filter() {
            @SuppressWarnings("unchecked")
            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                //Log.d(Constants.TAG, "**** PUBLISHING RESULTS for: " + constraint);
                orderFilterList = (List<ItemModel.Items>) results.values;
                OrderAdapter.this.notifyDataSetChanged();
            }

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
               // Log.d(Constants.TAG, "**** PERFORM FILTERING for: " + constraint);
                List<ItemModel.Items> filteredResults = getFilteredResults(constraint.toString());

                FilterResults results = new FilterResults();
                results.values = filteredResults;

                return results;
            }

            private List<ItemModel.Items> getFilteredResults(String constraintString) {
                constraintString = constraintString.toLowerCase();// to search in all lower case

                List<ItemModel.Items> templist= new ArrayList<>();
                for (ItemModel.Items order : orderList) {

                    int productCheck =-1;
                    for(int i= 0 ; i<order.getorderLineItems().size();i++)
                    {
                        if(order.getorderLineItems().get(i).getLineItemName().toLowerCase().contains(constraintString))// Checking for product
                        {//preparting the temp list
                            productCheck = 0;
                            break;
                        }
                    }

                   if( order.getorderNumber().toLowerCase().contains(constraintString)
                       || order.getOrderCustomer().getCustomerFirstName().toLowerCase().contains(constraintString)
                       || order.getOrderCustomer().getCustomerEmail().toLowerCase().contains(constraintString)||productCheck!=-1){
                           //preparting the temp list
                           templist.add(order);
                    }
                }
                return templist;
            }
        };
    }
}
