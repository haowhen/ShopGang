package com.cis490.haonguyen.shopgang;

import com.cis490.haonguyen.shopgang.model.Store;

import java.util.Comparator;

/**
 * Created by Alex on 12/14/2014.
 */
    public class StoreNameComparer implements Comparator<Store> {
        @Override
        public int compare(Store name1, Store name2) {
            return name1.getStoreName().compareTo(name2.getStoreName());
        }
    }
