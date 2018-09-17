package com.systelab.seed.util.pagination;

public class Pageable {

    private int number;
    private int size;

    public Pageable() {
        this.number = 1;
        this.size = 100;
    }

    public Pageable(int number, int size) {
        this.number = number;
        this.size = size;
    }

    public int getPageNumber() {
        return number;
    }

    public int getPageSize() {
        return size;
    }
}
