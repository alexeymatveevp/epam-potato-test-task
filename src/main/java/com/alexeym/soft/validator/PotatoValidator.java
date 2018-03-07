package com.alexeym.soft.validator;

import com.alexeym.soft.model.PotatoBag;
import com.alexeym.soft.storage.SupplierStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.List;

/**
 * Created by Alexey Matveev on 3/7/2018.
 */
@Component
public class PotatoValidator {

    @Autowired
    private SupplierStorage supplierStorage;

    public ValidationResult validatePotatoBag(PotatoBag bag) {
        if (bag.getPotatoes() < 1 || bag.getPotatoes() > 100) {
            return ValidationResult.invalid("Number of potatoes in a bag must be between 1 and 100");
        }
        List<String> suppliers = supplierStorage.list();
        if (suppliers != null && !suppliers.contains(bag.getSupplier())) {
            return ValidationResult.invalid("Supplier " + bag.getSupplier() + " is not known");
        }
        if (bag.getPackDate() != null && bag.getPackDate().isAfter(Instant.now())) {
            return ValidationResult.invalid("Pack date cannot be in future");
        }
        if (bag.getPrice() < 1 || bag.getPrice() > 50) {
            return ValidationResult.invalid("Potato bag price must be between 1 and 100");
        }
        return ValidationResult.valid();
    }
}
