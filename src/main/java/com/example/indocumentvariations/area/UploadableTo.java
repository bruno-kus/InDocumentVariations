package com.example.indocumentvariations.area;

public interface UploadableTo<U> {
    // albo uploader do, którego deleguję albo bezpośrednia implementacja!
    // to powinno pozwolić mi korzystać z dekoratora!
    // uploader wariacji będzie w strefie!
    void upload(U uploadable);
}
