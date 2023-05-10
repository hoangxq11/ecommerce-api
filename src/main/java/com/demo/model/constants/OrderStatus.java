package com.demo.model.constants;

import java.util.Arrays;
import java.util.List;

public final class OrderStatus {

    public static final String PENDING = "PENDING";
    public static final String SHIPPING = "SHIPPING";
    public static final String DONE = "DONE";
    public static final String CANCELED = "CANCELED";
    public static final List<String> LIST_STATUS = Arrays.asList(PENDING, SHIPPING, DONE, CANCELED);

    private OrderStatus() {
    }
}
