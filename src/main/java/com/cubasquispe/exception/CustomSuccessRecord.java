package com.cubasquispe.exception;

import java.time.LocalDateTime;

public record CustomSuccessRecord(
    LocalDateTime dateTime,
        String message,
        String details

) {
    
}
