package com.alexeym.soft.storage;

import com.alexeym.soft.model.PotatoBag;

import java.util.List;

/**
 * Created by Alexey Matveev on 3/7/2018.
 */
public interface PotatoStorage {

    List<PotatoBag> list();

    PotatoBag add(PotatoBag bag);

}
