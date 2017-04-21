package com.suraj.shopifyaerodynamic.Model;

/**
 * Created by Suraj on 4/19/2017.
 */


import com.google.gson.annotations.SerializedName;

public class ItemModel {
    @SerializedName("orders")
    Items webItems[];

    public Items[] getWebItems() {
        return webItems;
    }

    public void setWebItems(Items[] webItems) {
        this.webItems = webItems;
    }

    public class Items{

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
        LineItems orderLineItems[];

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

        public LineItems[] getorderLineItems() {
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

        public void setorderLineItems(LineItems[] orderLineItems) {
            orderLineItems = orderLineItems;
        }

        public class LineItems{
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

        }
    }
}
