package com.demo.crud.RestAPI.Repository;

import com.demo.crud.RestAPI.Entity.User;

import java.time.Instant;

/**
 * Projection for {@link User}
 */
public interface UserInfo {
    Long getId();

    String getTitle();

    String getUrl();

    Instant getCreatedAt();
}
