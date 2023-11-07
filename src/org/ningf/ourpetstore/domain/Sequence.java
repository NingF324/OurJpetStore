package org.ningf.ourpetstore.domain;

import java.io.Serializable;

/**
 * @description:
 * @author: Lenovo
 * @time: 2023/11/7 16:30
 */
public class Sequence implements Serializable {
    private static final long serialVersionUID = 8278780133180137281L;

    private String name;
    private int nextId;

    public Sequence() {
    }

    public Sequence(String name, int nextId) {
        this.name = name;
        this.nextId = nextId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNextId() {
        return nextId;
    }

    public void setNextId(int nextId) {
        this.nextId = nextId;
    }

}
