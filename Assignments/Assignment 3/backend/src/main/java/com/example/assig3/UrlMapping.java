package com.example.assig3;

public class UrlMapping { //TODO: adapteaza urls
    public static final String API_PATH = "/api";
    public static final String PATIENT = API_PATH +  "/patients";
    public static final String CONSULTATION = API_PATH +  "/consultation";

    public static final String ENTITY = "/{id}";
    public static final String EXPORT_REPORT = "/export/{type}";

    public static final String SEARCH = "/{string}";
    public static final String FIRST_TEST = "/firstTest";

    public static final String REVIEWS = "/reviews";

    public static final String AUTH = API_PATH + "/auth";
    public static final String SIGN_IN = "/sign-in";
    public static final String SIGN_UP = "/sign-up";

    public static final String USERS = API_PATH + "/users";

}
