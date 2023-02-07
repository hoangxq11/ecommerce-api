package com.demo.web.security;

public final class AuthoritiesConstants {

    // Based role: Customers, managers, warehouse staff and consultants
    public static final String CUSTOMER = "ROLE_CUSTOMER";
    public static final String MANAGER = "ROLE_MANAGER";
    public static final String WAREHOUSE_STAFF = "ROLE_WAREHOUSE_STAFF";
    public static final String CONSULTANT = "ROLE_CONSULTANT";

    private AuthoritiesConstants() {}
}
