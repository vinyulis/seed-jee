package com.systelab.seed.util.exceptions;

public class PatientNotFoundException extends SeedBaseException {
    public PatientNotFoundException() {
        super(ErrorCode.PATIENT_NOT_FOUND);
    }
}
