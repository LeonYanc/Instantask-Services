package com.instantask.service.dto;

/**
 * A DTO to check whether a user with the given email exists in the system.
 * Originally named "InviteRequestDto" but now only used for simple existence checks.
 */
public class CheckUserByEmailRequestDto {

    private String email;

    public CheckUserByEmailRequestDto() {
    }

    public CheckUserByEmailRequestDto(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
