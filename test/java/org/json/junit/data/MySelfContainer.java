package org.json.junit.data;

/**
 * Created by aronszabo on 08/10/2017.
 */
public class MySelfContainer {
    private MySelfContainer c1;
    private MySelfContainer c2;

    public MySelfContainer getC1() {
        return c1;
    }

    public void setC1(MySelfContainer c1) {
        this.c1 = c1;
    }

    public MySelfContainer getC2() {
        return c2;
    }

    public void setC2(MySelfContainer c2) {
        this.c2 = c2;
    }
}
