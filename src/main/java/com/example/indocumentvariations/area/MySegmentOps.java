package com.example.indocumentvariations.area;

import org.fxmisc.richtext.model.SegmentOpsBase;
import org.fxmisc.richtext.model.TextOps;

import java.util.Optional;

public class MySegmentOps <S> extends SegmentOpsBase<MySegment, S> {
    public MySegmentOps() {
        super(new MySegment("EMPTY"));
    }

    @Override
    public char realCharAt(MySegment mySegment, int i) {
        return mySegment.getCurrentText().charAt(i);
    }

    @Override
    public String realGetText(MySegment mySegment) {
        return mySegment.getCurrentText();
    }

    @Override
    public MySegment realSubSequence(MySegment mySegment, int i, int i1) {
        System.out.println("MySegmentOps::realSubSequence");
//        return new MySegment(mySegment.getCurrentText().substring(i, i1));
        // realSubSequence 2:


//        return new MySegment(mySegment, start, end);
        System.out.printf("mySegment:\n%s\n", mySegment);
        return new MySegment(mySegment, i, i1);
    }

    @Override
    public int length(MySegment mySegment) {
        var r=  mySegment.getCurrentText().length();
        System.out.printf("length: %s\n", r);
        return r;
    }

    @Override
    public Optional<MySegment> joinSeg(MySegment mySegment, MySegment seg1) {
        return Optional.empty(); // if they cannot be joined!
    }
}
