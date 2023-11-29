package com.aamir.record;

import lombok.Builder;

@Builder
public record ResponseCustomer(Long customerId, String customerName, String email, boolean verified) {
}
