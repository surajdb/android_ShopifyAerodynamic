package com.suraj.shopifyaerodynamic.Model;

/**
 * Created by Suraj on 4/19/2017.
 */


import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class ItemModel implements Parcelable{
    @SerializedName("orders")
    List<Items> webItems = new ArrayList<>();

    public List<Items> getWebItems() {
        return webItems;
    }

    public void setWebItems(List<Items> webItems) {
        this.webItems = webItems;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(webItems);
    }

    //======================Parcelable Implemenatation start ==================
    public ItemModel(Parcel in) {
        webItems = in.createTypedArrayList(Items.CREATOR);
    }

    public static final Creator<ItemModel> CREATOR = new Creator<ItemModel>() {
        @Override
        public ItemModel createFromParcel(Parcel in) {
            return new ItemModel(in);
        }

        @Override
        public ItemModel[] newArray(int size) {
            return new ItemModel[size];
        }
    };
    //======================Parcelable Implemenatation end ========================


    public static class Items implements Parcelable{

        @SerializedName("id")
        String orderID;
        @SerializedName("total_price")
        String orderTotalPrice;
        @SerializedName("order_number")
        String orderNumber;
        @SerializedName("user_id")
        String orderuserId;
        @SerializedName("created_at")
        String orderCreatedAt;

        public Customer getOrderCustomer() {
            return orderCustomer;
        }

        public void setOrderCustomer(Customer orderCustomer) {
            this.orderCustomer = orderCustomer;
        }

        @SerializedName("customer")
        Customer orderCustomer ;

        @SerializedName("line_items")
        List<LineItems> orderLineItems = new ArrayList<>();
//==============================Parcelable Implemenatation start =======================
        protected Items(Parcel in) {
            orderID = in.readString();
            orderTotalPrice = in.readString();
            orderNumber = in.readString();
            orderuserId = in.readString();
            orderCreatedAt = in.readString();
            in.readTypedList(orderLineItems, LineItems.CREATOR);
            orderCustomer = in.readParcelable(Customer.class.getClassLoader()); //in.readParcelable(Customer.class.getClassLoader());
        }

        public static final Creator<Items> CREATOR = new Creator<Items>() {
            @Override
            public Items createFromParcel(Parcel in) {
                return new Items(in);
            }

            @Override
            public Items[] newArray(int size) {
                return new Items[size];
            }
        };
        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(orderID);
            dest.writeString(orderTotalPrice);
            dest.writeString(orderNumber);
            dest.writeString(orderuserId);
            dest.writeString(orderCreatedAt);
            dest.writeTypedList(orderLineItems);
            dest.writeParcelable(orderCustomer,flags);
        }
//=============================Parcelable Implemenatation end=============================
        public String getOrderID() {
            return orderID;
        }

        public String getOrderTotalPrice() {
            return orderTotalPrice;
        }

        public String getorderNumber() {
            return orderNumber;
        }

        public String getorderuserId() {
            return orderuserId;
        }

        public String getOrderCreatedAt() {
            return orderCreatedAt;
        }

        public List<LineItems> getorderLineItems() {
            return orderLineItems;
        }

        public void setOrderID(String orderID) {
            this.orderID = orderID;
        }

        public void setOrderTotalPrice(String orderTotalPrice) {
            this.orderTotalPrice = orderTotalPrice;
        }

        public void setorderNumber(String orderNumber) {
            orderNumber = orderNumber;
        }

        public void setorderuserId(String orderuserId) {
            orderuserId = orderuserId;
        }

        public void setOrderCreatedAt(String orderCreatedAt) {
            this.orderCreatedAt = orderCreatedAt;
        }

        public void setorderLineItems(List<LineItems> orderLineItems) {
            orderLineItems = orderLineItems;
        }




        public static class LineItems implements Parcelable{
            @SerializedName("id")
            String LineItemId;

            @SerializedName("title")
            String LineItemTitle;

            @SerializedName("quantity")
            String LineItemQuantity;

            @SerializedName("price")
            String LineItemPrice;

            @SerializedName("name")
            String LineItemName;

            @SerializedName("variant_id")
            String LineItemVariantId;

            @SerializedName("product_id")
            String LineItemProductId;

            protected LineItems(Parcel in) {
                LineItemId = in.readString();
                LineItemTitle = in.readString();
                LineItemQuantity = in.readString();
                LineItemPrice = in.readString();
                LineItemName = in.readString();
                LineItemVariantId =in.readString();
                LineItemProductId = in.readString();
            }

            public static final Creator<LineItems> CREATOR = new Creator<LineItems>() {
                @Override
                public LineItems createFromParcel(Parcel in) {
                    return new LineItems(in);
                }

                @Override
                public LineItems[] newArray(int size) {
                    return new LineItems[size];
                }
            };

            public String getLineItemId() {
                return LineItemId;
            }

            public String getLineItemTitle() {
                return LineItemTitle;
            }

            public String getLineItemQuantity() {
                return LineItemQuantity;
            }

            public String getLineItemPrice() {
                return LineItemPrice;
            }

            public String getLineItemName() {
                return LineItemName;
            }

            public String getLineItemProductId() {
                return LineItemProductId;
            }

            public String getLineItemVariantId() {
                return LineItemVariantId;
            }

            public void setLineItemVariantId(String lineItemVariantId) {
                LineItemVariantId = lineItemVariantId;
            }

            public void setLineItemId(String lineItemId) {
                LineItemId = lineItemId;
            }

            public void setLineItemTitle(String lineItemTitle) {
                LineItemTitle = lineItemTitle;
            }

            public void setLineItemQuantity(String lineItemQuantity) {
                LineItemQuantity = lineItemQuantity;
            }

            public void setLineItemPrice(String lineItemPrice) {
                LineItemPrice = lineItemPrice;
            }

            public void setLineItemName(String lineItemName) {
                LineItemName = lineItemName;
            }

            public void setLineItemProductId(String lineItemProductId) {
                LineItemProductId = lineItemProductId;
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(LineItemId);
                dest.writeString(LineItemTitle);
                dest.writeString(LineItemQuantity);
                dest.writeString(LineItemPrice);
                dest.writeString(LineItemName);
                dest.writeString(LineItemVariantId);
                dest.writeString(LineItemProductId);
            }
        }



        public static class Customer implements Parcelable {

            @SerializedName("email")
            String customerEmail;

            @SerializedName("first_name")
            String customerFirstName;

            @SerializedName("last_name")
            String customerLastName;

            public String getCustomerEmail() {
                return customerEmail;
            }

            public String getCustomerFirstName() {
                return customerFirstName;
            }

            public String getCustomerLastName() {
                return customerLastName;
            }

            public void setCustomerEmail(String customerEmail) {
                this.customerEmail = customerEmail;
            }

            public void setCustomerFirstName(String customerFirstName) {
                this.customerFirstName = customerFirstName;
            }

            public void setCustomerLastName(String customerLastName) {
                this.customerLastName = customerLastName;
            }


            protected Customer(Parcel in) {
                customerEmail = in.readString();
                customerFirstName = in.readString();
                customerLastName = in.readString();
            }

            public static final Creator<Customer> CREATOR = new Creator<Customer>() {
                @Override
                public Customer createFromParcel(Parcel in) {
                    return new Customer(in);
                }

                @Override
                public Customer[] newArray(int size) {
                    return new Customer[size];
                }
            };

            public Customer() { // Default constructor with blank values
                customerEmail="";
                customerFirstName="";
                customerLastName="";
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(customerEmail);
                dest.writeString(customerFirstName);
                dest.writeString(customerLastName);
            }
        }
    }
}
