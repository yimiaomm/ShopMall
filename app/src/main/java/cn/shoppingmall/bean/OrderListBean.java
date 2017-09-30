package cn.shoppingmall.bean;

/**
 * Created by pc on 2017/9/7.
 */

public class OrderListBean {
    private String orderId,orderStatus,orderContent,orderNewPrice,orderOldPrice,orderImageUrl
    ,orderCount;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getOrderContent() {
        return orderContent;
    }

    public void setOrderContent(String orderContent) {
        this.orderContent = orderContent;
    }

    public String getOrderNewPrice() {
        return orderNewPrice;
    }

    public void setOrderNewPrice(String orderNewPrice) {
        this.orderNewPrice = orderNewPrice;
    }

    public String getOrderOldPrice() {
        return orderOldPrice;
    }

    public void setOrderOldPrice(String orderOldPrice) {
        this.orderOldPrice = orderOldPrice;
    }

    public String getOrderImageUrl() {
        return orderImageUrl;
    }

    public void setOrderImageUrl(String orderImageUrl) {
        this.orderImageUrl = orderImageUrl;
    }

    public String getOrderCount() {
        return orderCount;
    }

    public void setOrderCount(String orderCount) {
        this.orderCount = orderCount;
    }
}
