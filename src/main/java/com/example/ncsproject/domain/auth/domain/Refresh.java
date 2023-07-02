package com.example.ncsproject.domain.auth.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;
import org.springframework.data.redis.core.index.Indexed;

import java.util.concurrent.TimeUnit;

@Getter
@AllArgsConstructor
@Builder
@RedisHash
public class Refresh {

    @Id
    private String accountId;

    @Indexed
    private String token;

    @TimeToLive(unit = TimeUnit.MINUTES)
    private Long ttl;

    public Refresh updateToken(String token) {
        this.token = token;
        return this;
    }
}
