package com.felipemoreira.desafioanotaai.domain.product.exceptions;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(String message) {
        super(message);
    }
}
