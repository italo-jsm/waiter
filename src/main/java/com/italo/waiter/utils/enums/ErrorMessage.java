package com.italo.waiter.utils.enums;

public enum ErrorMessage {

    COMPANY_WITH_NO_USERS("001", "A new company must have at least one user"),
    COMPANY_SYSTEM_USER_EXISTS("002", "A new company must not have existing users"),
    ROLE_NOT_FOUND("003", "Role not found");

    private final String code;
    private final String name;

    ErrorMessage(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getFormattedMessage(){
        return this.code.concat(" - ").concat(this.name);
    }
}
