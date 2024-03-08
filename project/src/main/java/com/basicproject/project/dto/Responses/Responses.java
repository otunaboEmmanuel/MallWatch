package com.basicproject.project.dto.Responses;

public enum Responses {

    USER_CREATED("00", "User Created Successfully"),
    DUPLICATE_USER("01", "User already exists"),
    PRODUCT_CREATED("00", "Product added successfully"),
    CART_CREATED("00", "Item added to cart successfully"),
    UNEXPECTED_ERROR("90", "Unexpected error occurred"),
    WRONG_USERNAME("02", "Wrong User ID"),
    WRONG_PASSWORD("03", "Wrong Password"),
    UNAUTHORIZED_USER("29", "User Unauthorized for this operation"),
    WRONG_CREDENTIALS("04", "Username and password does not match");




    private String code;
    private String message;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    Responses(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
