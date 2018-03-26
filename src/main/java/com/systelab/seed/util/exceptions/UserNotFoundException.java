package com.systelab.seed.util.exceptions;

public class UserNotFoundException extends SeedBaseException {
    public UserNotFoundException() {
        super(ErrorCode.USER_NOT_FOUND);
    }
}
