package com.example.indocumentvariations.area;


import java.util.Objects;

public class PassageState implements Passage {
    int position;
    String text;

    public String getText() {
        return text;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getPosition() {
        return position;
    }
    PassageState(int position, String text) {
        this.text = text;
        this.position = position;
    }
    PassageState(int position) {
        this(position, null);
    }
    PassageState(PassageState tp) {
        this.text = tp.text;
        this.position = tp.position;
    }

    public int comparePosition(PassageState that) {
        return Integer.compare(this.position, that.position);
    }

    public int compareText(PassageState that) {
        return Objects.compare(this.text, that.text, String::compareTo);
    }

    public MyRange toRange() {
        return new MyRange(position, position + text.length());
    }

    @Override
    public String toString() {
        return String.format("{%s, %d}", text, position);
    }
}
