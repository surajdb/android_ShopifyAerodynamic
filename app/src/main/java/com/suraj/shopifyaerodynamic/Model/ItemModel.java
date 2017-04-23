package com.suraj.shopifyaerodynamic.Model;

/**
 * Created by Suraj on 4/19/2017.
 */


import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class ItemModel implements Parcelable{
    @SerializedName("orders")
    List<Items> webItems = new ArrayList<>();

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
        @SerializedName("line_items")
        List<LineItems> orderLineItems = new ArrayList<>();
//==========================================================================
        protected Items(Parcel in) {
            orderID = in.readString();
            orderTotalPrice = in.readString();
            orderNumber = in.readString();
            orderuserId = in.readString();
            orderCreatedAt = in.readString();
            in.readTypedList(orderLineItems, LineItems.CREATOR);
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
        }
//==========================================================================
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

            @SerializedName("product_id")
            String LineItemProductId;

            protected LineItems(Parcel in) {
                LineItemId = in.readString();
                LineItemTitle = in.readString();
                LineItemQuantity = in.readString();
                LineItemPrice = in.readString();
                LineItemName = in.readString();
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
                dest.writeString(LineItemProductId);
            }
        }
    }
}
