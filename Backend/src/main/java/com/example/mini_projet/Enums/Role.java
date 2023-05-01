package com.example.mini_projet.Enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;

import static com.example.mini_projet.Enums.Permission.*;

@RequiredArgsConstructor
public enum Role {

    ADMIN(
            Set.of(
                    ADMIN_CREATE,
                    ADMIN_DELETE,
                    ADMIN_UPDATE,
                    ADMIN_READ,
                    SUBADMIN_CREATE,
                    SUBADMIN_DELETE,
                    SUBADMIN_UPDATE,
                    SUBADMIN_READ
            )
    )


    ,
    SUBADMIN(
            Set.of(
                    SUBADMIN_CREATE,
                    SUBADMIN_DELETE,
                    SUBADMIN_UPDATE,
                    SUBADMIN_READ
            )
    ),
    USER(Collections.emptySet());

    @Getter
    private final Set<Permission> permissions;
}
