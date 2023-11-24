package com.example.indocumentvariations.area;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import org.fxmisc.richtext.*;
import org.fxmisc.richtext.model.*;

import org.reactfx.util.*;

import java.util.List;
import java.util.Optional;

public class MyArea extends GenericStyledArea<Void, Either<String, MySegment>, String> {


    // zrobić wspólny interfejs Segment, który implementuje zarówno MyString, jak i MySegment
    // w Opsach korzystać z instanceof do wyciągania realnego typu i delegowania do odpowiednich opsów konkretnych
    BigModel bigModel;
    ObjectProperty<Tag> selectedTag = new SimpleObjectProperty<>();
    public void setSelectedTag(Tag selectedTag) {
        this.selectedTag.set(selectedTag);
    }
    public Tag getSelectedTag() {
        return selectedTag.get();
    }
    public ObjectProperty<Tag> selectedTagProperty() {
        return selectedTag;
    }
    private static final TextOps<String, String> STYLED_TEXT_OPS = SegmentOps.styledTextOps();
    private static final MySegmentOps<String> MY_OPS = new MySegmentOps<>();
    private static final TextOps<Either<String, MySegment>, String> EITHER_OPS = STYLED_TEXT_OPS._or(MY_OPS, (e1, e2) -> Optional.empty());
    //    private static final TextOps<MySegment, Void> ops = OPS;
    /*
    czy wszystkie wyświetlane elementy włącznie z obrazkami muszę traktować jako text?
     */
    public MyArea(BigModel bigModel) {
        super(
                null,
                (t, p) -> {
                },
                null,
                EITHER_OPS,
                e -> e.getSegment().unify(
                        TextExt::new,
                        mySegment -> {
                            return new TextExt(mySegment.getCurrentText());
                        })

        );
        this.bigModel = bigModel;
    }

    /*
    tu gdzieś powinna być metoda
     */
    public void writeMySegment(Tag tag) {
        setOnKeyPressed(k -> insertMySegment(getCaretPosition(), tag));
    }

    public void insertMySegment(int position, Tag tag) {
        replaceWithMySegment(position, position, tag);
    }

    /*
    dobre pytanie
    skoro tekst jest przechowywany w modelu to jak powinienem
    to writeMySegment nie jest takie trywialne ponieważ przechwytywanie klawiszy będzie aktualizowało model
    ojejku jej
    aczkolwiek na rzecz, którego tagu, a co za tym idzie, którego modelu, będę przechwytywał wciśnięcia!
    jak zrobić, żeby wciśnięcia aktualizowały model?
    kurde!
     */
    public void replaceWithMySegment(int start, int end, Tag tag) {
        replace(start, end, ReadOnlyStyledDocument.fromSegment(
                Either.right(new MySegment(tag)),
                null,
                "",
                EITHER_OPS
        ));
    }

    /*
    public void replaceWithMember(int start, int end, String text, GroupTag group) {
        // add to map:
        //  member -> start, end
        TextMember member = new TextMember(text, group);
        replace(start, end, ReadOnlyStyledDocument.fromSegment(
                Either.right(member),
                null,
                TextStyle.EMPTY,
                EITHER_OPS
        ));
    }
     */
    private void replaceSelectionWithMySegment(Tag tag) {
        System.out.println("MyArea::replaceSelectionWithMySegment");

        /*
        najpierw bym dodawał segment pusty
        a następnie patrzył, gdzie też ten pusty został dodany!
         */
        // dodaj do modelu najpierw?
        Either<String, MySegment> right = Either.right(new MySegment(tag));
        replaceSelection(ReadOnlyStyledDocument.fromSegment(right, null, null, EITHER_OPS));

        var inserted = getTagSegments(tag).stream().filter(seg -> seg.getInVariationIndex() == -1).findFirst();
        if (inserted.isPresent()){
            int i = getTagSegments(tag).indexOf(inserted.get());
            bigModel.getModel(selectedTag.get()).activeVariation.get().add(i, getText(getSelection()));
        }
    }
    public List<MySegment> getMySegments() {
        return getAllSegments().stream().filter(Either::isRight).map(Either::getRight).toList();
    }
    public List<Either<String, MySegment>> getAllSegments() {
        return getDocument().subSequence(0, getLength()).getParagraphs().stream().
                flatMap(e -> e.getSegments().stream()).
                toList();
    }
    public List<MySegment> getTagSegments(Tag tag) {
        return getMySegments().stream().filter(seg -> seg.tag.equals(tag)).toList();
    }
    /*
    i teraz teoretycznie bym mógł nadpisać kontrolę tego, co robi back space...
    albo subsekwencję mieć, która podaję wskaźnik do listy następcy
     */
}
