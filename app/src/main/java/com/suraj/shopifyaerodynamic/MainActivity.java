package com.suraj.shopifyaerodynamic;

/**
 * Created by Suraj on 04/19/2017.
 */

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.MPPointF;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.TextHttpResponseHandler;
import com.suraj.shopifyaerodynamic.Model.ItemModel;
import com.suraj.shopifyaerodynamic.Utility.AppConstants;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity  implements View.OnClickListener, AdapterView.OnItemSelectedListener {


    List<ItemModel.Items> list = new ArrayList<ItemModel.Items>();
    public ProgressDialog progressDialog;
    private Spinner  spinnerItemSelect;
    private TextView txtTotalValue;
    private TextView txtPricePerItem;
    private TextView txtTotalItem;
    private Button   btnShowList;
    PieChart pieChart;
    private Map<String, List<String>> productDetailList = new LinkedHashMap<>();//to Store all the products and their details
    private List<String> productList = new ArrayList<>();   // product list name
    private List<String> productIdList = new ArrayList<>(); // product list id
    private List<ItemModel.Items> orderList =  new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getDataFromUrl();
        setUpViews();
    }

    //Setup Initial Views
    public void setUpViews()
    {
        spinnerItemSelect = (Spinner)  findViewById(R.id.spinnerItemSelect);
        txtTotalValue     = (TextView) findViewById(R.id.txtTotalValue );
        txtPricePerItem   = (TextView) findViewById(R.id.txtPricePerItem);
        txtTotalItem      = (TextView) findViewById(R.id.txtTotalItem );
        btnShowList       = (Button)   findViewById(R.id.btnShowList);
        pieChart  = (PieChart) findViewById(R.id.chart);
        spinnerItemSelect.setOnItemSelectedListener(this);
        btnShowList.setOnClickListener(this);
    }

    // fetching the data obtained from Async request into our gloable list and orderHashmap
    public void  getDataFromUrl() {
        AsyncHttpClient client = new AsyncHttpClient();
        progressBar();
        client.get(AppConstants.kUrl,null, new TextHttpResponseHandler() {

            @Override
            public void onStart() {
                // called before request is started
                //progressDialog.dismiss();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                Toast.makeText(getApplicationContext(), AppConstants.kERROR_MSG, Toast.LENGTH_SHORT).show();
                Log.d("error", String.valueOf(statusCode));
                progressDialog.dismiss();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers,String responseString) {
                Gson gson = new GsonBuilder().create();
                ItemModel model ;//= new ItemModel();
                model = gson.fromJson(String.valueOf(responseString), ItemModel.class);
                list = model.getWebItems();
                for(int t= 0; t<list.size();t++)
                {
                    if(list.get(t).getOrderCustomer() == null)
                    {
                        list.get(t).setOrderCustomer(new ItemModel.Items.Customer()); // If there are any null object for Customer details replace it with Default blank etails
                    }
                }
                final List<ItemModel.Items> modelWebItems = model.getWebItems();

                if(modelWebItems.isEmpty()) {
                    Toast.makeText(getApplicationContext(),AppConstants.kNotFound,Toast.LENGTH_SHORT).show();
                }

                progressDialog.dismiss();
            }

            @Override
            public void onRetry(int retryNo) {
                progressDialog.dismiss();
                // called when request is retried
            }

            @Override
            public void onFinish()
            {
                getOrders();
                setSpinner();
                //progressDialog.dismiss();
            }
        });
    }
    public void progressBar()
    {
        progressDialog = new ProgressDialog(this, R.style.AppTheme);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Fetching data...");
        progressDialog.show();
    }
    public void  getOrders()
    {
        for(int i =0;i< list.size();i++)
        {
            List<ItemModel.Items.LineItems> LineItems = list.get(i).getorderLineItems();

            String productId = "";
            for(int j=0;j<LineItems.size();j++)
            {
                List<String> itemDetails = new ArrayList<>();
                productId = (LineItems.get(j).getLineItemProductId()) ;

                // if product already exist then increase the quantity
                if((!productDetailList.isEmpty()) && productDetailList.containsKey(productId))
                {
                    int oldProductQuantity =  Integer.parseInt(productDetailList.get(productId).get(4)) ;
                    int newProductQuantity =  oldProductQuantity + Integer.parseInt(LineItems.get(j).getLineItemQuantity());
                    productDetailList.get(productId).set(4, Integer.toString(newProductQuantity));
                }
                else // add the new product
                {
                    itemDetails.add(LineItems.get(j).getLineItemId());
                    itemDetails.add(LineItems.get(j).getLineItemName());
                    itemDetails.add(LineItems.get(j).getLineItemPrice());
                    itemDetails.add(LineItems.get(j).getLineItemProductId());
                    itemDetails.add(LineItems.get(j).getLineItemQuantity());
                    itemDetails.add(LineItems.get(j).getLineItemTitle());
                    productList.add(LineItems.get(j).getLineItemTitle());
                    productIdList.add(LineItems.get(j).getLineItemProductId());
                    productDetailList.put(productId,itemDetails);
                }
            }
        }
    }
    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.btnShowList:
                Intent orderListActivity = new Intent(MainActivity.this, OrderList.class);
                orderListActivity.putParcelableArrayListExtra("map", (ArrayList<? extends Parcelable>) list);
                startActivity(orderListActivity);
                break;
        }

    }

    // To perform some action on item selected
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        {
            switch (parent.getId())
            {
                case R.id.spinnerItemSelect:
                    if(!productList.get(position).equals("ALL")) // for Displaying results for individual items
                    {
                        //Toast.makeText(getApplicationContext(), spinnerItemSelect.getItemAtPosition(position).toString(), Toast.LENGTH_SHORT).show();
                        float totalPrice = 0.0f;
                        totalPrice = Float.parseFloat(productDetailList.get(productIdList.get(position)).get(AppConstants.kProductPrice)) *
                                     Integer.parseInt(productDetailList.get(productIdList.get(position)).get(AppConstants.kProductQuantity)) ;

                        txtTotalItem.setText(productDetailList.get(productIdList.get(position)).get(AppConstants.kProductQuantity));
                        txtTotalValue.setText("$"+Double.toString((double)Math.round(totalPrice*100.00)/100.00));
                        txtPricePerItem.setText("$"+productDetailList.get(productIdList.get(position)).get(AppConstants.kProductPrice));

                        List<PieEntry> entries = new ArrayList<>();
                        Float percentage = (100*totalPrice/calTotalPrice())*100/100;
                        entries.add(new PieEntry(percentage, productDetailList.get(productIdList.get(position)).get(AppConstants.kProductName))); // total price for each product
                        entries.add(new PieEntry(100 - percentage,"Rest of the items"));// total price for all product

                        PieDataSet dataSet = new PieDataSet(entries, "# Products");
                        dataSet.setDrawIcons(false);

                        // adding colors
                        ArrayList<Integer> colors = new ArrayList<Integer>();
                        for (int c : ColorTemplate.JOYFUL_COLORS)
                            colors.add(c);
                        colors.add(ColorTemplate.getHoloBlue());
                        //Formatting the PIE
                        dataSet.setSliceSpace(2f);
                        dataSet.setIconsOffset(new MPPointF(0, 40));
                        dataSet.setSelectionShift(5f);
                        dataSet.setValueTextColor(Color.BLACK);
                        dataSet.setColors(colors);

                        PieData data = new PieData(dataSet);
                        data.setValueFormatter(new PercentFormatter());
                        data.setValueTextSize(14f);
                        data.setValueTextColor(Color.BLACK);
                        pieChart.setData(data);
                        pieChart.invalidate(); // refresh
                        pieChart.setCenterText("Product Share");
                    }
                    else // for Displaying results for all items
                    {
                        txtTotalItem.setText(Integer.toString(calTotalItem()));
                        txtTotalValue.setText("$"+Double.toString((double)Math.round(calTotalPrice()*100.0)/100.0)); // Rounding off to d2 decimal places
                        txtPricePerItem.setText("-");// txtPricePerItem.setText("-");
                    }
                    break;
            }
        }
    }
    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    //Setting up spinner data once the data fetching is coemplete from Async request
    public void setSpinner()
    {
        productList.add("ALL"); // To add an option to select calculation for all the items
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, productList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerItemSelect.setAdapter(adapter);

    }
    // For Total item in the orders
    public int calTotalItem()
    {
        int totalItem = 0;
        for(int i=0;i<productIdList.size();i++)
        {
            totalItem  = totalItem + Integer.parseInt(productDetailList.get(productIdList.get(i)).get(AppConstants.kProductQuantity)) ;
        }
        return totalItem;
    }
    // For calculating Total Value of all the product
    public float calTotalPrice()
    {
        float totalPrice = 0.0f;
        for(int i=0;i<productIdList.size();i++)
        {
            totalPrice = totalPrice + Float.parseFloat(productDetailList.get(productIdList.get(i)).get(AppConstants.kProductQuantity))  *
                                      Float.parseFloat(productDetailList.get(productIdList.get(i)).get(AppConstants.kProductPrice)) ;
        }
        return totalPrice;
    }
}
