package com.dudulu.app;

/**
 * Created by Vincent on 4/15/14.
 */
public class RowItem {
    private Item item0;
    private Item item1;
    private Item item2;

    public RowItem() {}

    public RowItem(Item item0, Item item1, Item item2) {
        this.item0 = item0;
        this.item1 = item1;
        this.item2 = item2;
    }

    public Item getItem0() {
        return item0;
    }

    public void setItem0(Item item0) {
        this.item0 = item0;
    }

    public Item getItem1() {
        return item1;
    }

    public void setItem1(Item item1) {
        this.item1 = item1;
    }

    public Item getItem2() {
        return item2;
    }

    public void setItem2(Item item2) {
        this.item2 = item2;
    }
}
