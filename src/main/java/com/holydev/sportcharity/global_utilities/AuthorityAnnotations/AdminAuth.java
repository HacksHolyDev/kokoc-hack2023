package com.holydev.sportcharity.global_utilities.AuthorityAnnotations;


import org.springframework.security.access.prepost.PreAuthorize;

import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@PreAuthorize("hasAuthority(T(com.holydev.sportcharity.entities.users.Role).ADMIN)")
public @interface AdminAuth {
}
