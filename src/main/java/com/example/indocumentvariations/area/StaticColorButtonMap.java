package com.example.indocumentvariations.area;

import javafx.scene.control.Button;

import java.util.EnumMap;

public class StaticColorButtonMap {
    // teraz powinienem być w stanie stworzyć kilka takich map, które by na różne sposoby mapowały enuma
    // problem polega na tym, że nie mogę w trakcie programu dodać nic do puli!
    EnumMap<BackgroundColor, BigModel> enumMap = new EnumMap<>(BackgroundColor.class);
    void setModelForAll(BigModel bigModel) {
        enumMap.replaceAll((k, v) -> bigModel);
    }



    /*
    w konstruktorze guzika ma być enum BackgroundColor
    co ja właściwie przyporządkowuje i gdzie?
    MyArea::writeSegmuent(tag) <-

     MyButton(tag) <- enumMap::get(

    użytkownik "tworzy" enumerator
    mapa pozwala połączyć enumerator oraz konkretny model

    czy powinny być wspólne kolory pomiędzy różne modele?
    ależ oczywiście! w końcu to jest coś ustalonego z góry

    ciekawa odpowiedź:
    nawet jeżeli użytkownik ustawia statyczną pulę kolorów, to musi być ona zmienialna
    ale może na przykład wybierać spośród enumeratorów


     */
    /*
    kwestia enumeracji
    mogę sprawić, że użytkownik wybiera spośród puli wybranej przeze mnie
    czym jest wybrana przeze mnie pula???

    ja się muszę w ogóle zastanowić czy pula kolorów wykorzystywanych do tagowania ma być skończona
    pytanie czy każde zaznaczenie powinno być inne?
    ależ tak!
    zdecydowanie może być ich dużo
    tylko że fizycznie kolorów nie ma tyle by się w nich połapać!
     */


//     */
//
//    zgadza się ponieważ każdy tag zna swój model
//    */
    static class MyButton extends Button {
        MyButton(String s, Tag tag) {
            super(s);
            setOnAction(e -> area.writeMySegment(tag));
        }
    }
}
