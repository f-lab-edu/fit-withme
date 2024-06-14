package com.example.fitwithme.domain.model;

import lombok.Builder;

@Builder
public record User(Long id, String userId, String userName, String userPassword, String email, String phone) {

}