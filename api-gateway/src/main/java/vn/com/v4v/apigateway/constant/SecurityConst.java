package vn.com.v4v.apigateway.constant;

/**
 * Name: SecurityConst
 * Author: QuangDK
 * Version: 1.0.0
 * CreatedDate: 03/09/2025
 * */
public class SecurityConst {

    public static class SERVICE_URL {

        public static final String SYSTEM_ADMIN = "/api/**";
        public static final String SERVICE_AUTH_URL = "/api/v1/auth/**";
        public static final String SERVICE_MASTER_DATA_URL = "/api/v1/master-data/**";
    }

    public static class ROLE {

        public static final String ROLE_ADMIN = "SYSTEM_ADMIN";
        public static final String ROLE_MASTER_DATA = "MASTER_DATA";
    }
}