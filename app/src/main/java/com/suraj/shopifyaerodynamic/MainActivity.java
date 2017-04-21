package com.suraj.shopifyaerodynamic;

/**
 * Created by Suraj on 04/19/2017.
 */

import android.app.ProgressDialog;
import android.preference.PreferenceActivity;
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
    private Map<String, List<String>> orderList = new LinkedHashMap<>();//to Store all the orders
    private List<String> itemList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getDataFromUrl();
        setUpViews();// setup the intial view
        //getDataFromUrl(); // fetch results from the api
    }

    public void setUpViews()
    {
        spinnerItemSelect = (Spinner)  findViewById(R.id.spinnerItemSelect);
        txtTotalValue     = (TextView) findViewById(R.id.txtTotalValue );
        txtPricePerItem   = (TextView) findViewById(R.id.txtPricePerItem);
        txtTotalItem      = (TextView) findViewById(R.id.txtTotalItem );
        btnShowList       = (Button)   findViewById(R.id.btnShowList);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, itemList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerItemSelect.setAdapter(adapter);

        spinnerItemSelect.setOnItemSelectedListener(this);
        //String b = spinnerItemSelect.getSelectedItem().toString();
        Toast.makeText(getApplicationContext(),"ItemSelect",Toast.LENGTH_SHORT).show();

    }
    public void  getDataFromUrl() {
        AsyncHttpClient client = new AsyncHttpClient();
        progressBar();
        client.get(AppConstants.kUrl,null, new TextHttpResponseHandler() {

            @Override
            public void onStart() {
                // called before request is started
                progressDialog.dismiss();
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
                ItemModel model = new ItemModel();
                model = gson.fromJson(String.valueOf(responseString), ItemModel.class);

                final ItemModel.Items[] modelWebItems = model.getWebItems();

                if(modelWebItems != null) {
                    for (int i = 0; i < modelWebItems.length; i++) {
                        list.add(modelWebItems[i]);
                    }
                }
                else
                    Toast.makeText(getApplicationContext(),"No Result Found",Toast.LENGTH_SHORT).show();
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
                progressDialog.dismiss();
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
            ItemModel.Items.LineItems[] LineItems = list.get(i).getorderLineItems();

            String productId = "";
            for(int j=0;j<LineItems.length;j++)
            {
                List<String> itemDetails = new ArrayList<>();
                productId = (LineItems[j].getLineItemProductId()) ;

                if((!orderList.isEmpty()) && orderList.containsKey(productId)) // if product already exist then increase the quantity
                {
                    int oldProductQuantity =  Integer.parseInt(orderList.get(productId).get(4)) ;
                    int newProductQuantity =  oldProductQuantity + Integer.parseInt(LineItems[j].getLineItemQuantity());
                    orderList.get(productId).set(4, Integer.toString(newProductQuantity));
                }
                else // add the new product
                {
                    itemDetails.add(LineItems[j].getLineItemId());
                    itemDetails.add(LineItems[j].getLineItemName());
                    itemDetails.add(LineItems[j].getLineItemPrice());
                    itemDetails.add(LineItems[j].getLineItemProductId());
                    itemDetails.add(LineItems[j].getLineItemQuantity());
                    itemDetails.add(LineItems[j].getLineItemTitle());
                    itemList.add(LineItems[j].getLineItemTitle());
                    orderList.put(productId,itemDetails);
                }
            }
        }
    }
    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.spinnerItemSelect:

                break;
        }

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
         {
             switch (parent.getId())
             {
                 case R.id.spinnerItemSelect:
                     Toast.makeText(getApplicationContext(), spinnerItemSelect.getItemAtPosition(position).toString(), Toast.LENGTH_SHORT).show();
                     break;
             }
         }
    }
    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
//    public List<String> getItemsList()
//    {
//        List <String> ItemList = new ArrayList<>();
//        for(int i = 0; i<orderList.size();i++)
//        {
//            ItemList.add(orderList.)
//        }
//        return ItemList;
//    }
}
