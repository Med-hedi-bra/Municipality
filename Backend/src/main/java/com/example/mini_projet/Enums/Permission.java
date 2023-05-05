package com.example.mini_projet.Enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Permission {


      ADMIN_READ("admin::read"),
    ADMIN_UPDATE("admin::update"),
    ADMIN_CREATE("admin::create"),
    ADMIN_DELETE("admin::delete"),


      SUBADMIN_READ("subadmin::read"),
    SUBADMIN_UPDATE("subadmin::update"),
    SUBADMIN_CREATE("subadmin::create"),
    SUBADMIN_DELETE("subadmin::delete"),


      USER_READ("user::read"),
    USER_UPDATE("user::update"),
    USER_CREATE("user::create"),
    USER_DELETE("user::delete");

    @Getter
    private final String permission;
}
