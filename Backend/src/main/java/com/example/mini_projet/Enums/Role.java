package com.example.mini_projet.Enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
                    USER_READ,
                    USER_CREATE,
                    USER_DELETE,
                    USER_UPDATE

            )
    ),


    USER(
            Set.of(

            )
    );

    @Getter
    private final Set<Permission> permissions;


    public List<SimpleGrantedAuthority> getAuthorities(){
        var authorities = getPermissions()
                .stream()
                .map(permission -> new SimpleGrantedAuthority(permission.name())).collect(Collectors.toList());
        authorities.add(new SimpleGrantedAuthority("ROLE_"+this.name()));
        return authorities;
    }
}
