package com.alexeym.soft.storage;

import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alexey Matveev on 3/7/2018.
 */
@Repository
public class SupplierMemoryStorage implements SupplierStorage {

    private List<String> KNOWN_SUPPLIERS = new ArrayList<>();

    @Override
    public List<String> list() {
        return KNOWN_SUPPLIERS;
    }

    @PostConstruct
    public void init() {
        KNOWN_SUPPLIERS.add("De Coster");
        KNOWN_SUPPLIERS.add("Owel");
        KNOWN_SUPPLIERS.add("Patatas Ruben");
        KNOWN_SUPPLIERS.add("Yunnan Spices");
    }
}
