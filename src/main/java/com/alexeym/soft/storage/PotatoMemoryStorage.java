package com.alexeym.soft.storage;

import com.alexeym.soft.model.PotatoBag;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by Alexey Matveev on 3/7/2018.
 */
@Repository
public class PotatoMemoryStorage implements PotatoStorage {

    private AtomicLong id = new AtomicLong(0);

    private List<PotatoBag> bags = new ArrayList<>();

    @Override
    public List<PotatoBag> list() {
        return bags;
    }

    @Override
    public PotatoBag add(PotatoBag bag) {
        PotatoBag idBag = bag.withId(randomId());
        bags.add(idBag);
        return idBag;
    }

    private String randomId() {
        return id.incrementAndGet() + "";
    }

}
