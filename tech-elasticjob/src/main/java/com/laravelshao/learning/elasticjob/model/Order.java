package com.laravelshao.learning.elasticjob.model;

public class Order {
    /**
     * 订单ID
     */
    private Integer orderId;
    /**
     * 订单状态 0-未处理 1-已处理
     */
    private Integer status;

    public Order() {
    }

    public Order(Integer orderId, Integer status) {
        this.orderId = orderId;
        this.status = status;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }



    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", status=" + status +
                '}';
    }
}
