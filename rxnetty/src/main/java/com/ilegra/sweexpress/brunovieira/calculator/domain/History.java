package com.ilegra.sweexpress.brunovieira.calculator.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class History {

    private List<String> history;

    public History() {
        this.history = new ArrayList<>();
    }

    public void addOperation(String function, double x, double y) {
        String info = function + ", " + x + ", " + y;
        history.add(info);
    }

    public List<String> getHistory() {
        return Collections.unmodifiableList(history);
    }
}
