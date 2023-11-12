package org.example.model.builder;

import org.example.model.AudioBook;
import org.example.model.Book;

import java.time.LocalDate;

public class AudioBookBuilder extends BookBuilder{

    private AudioBook book;

    public AudioBookBuilder() {
        super();
        book = new AudioBook();
    }

    @Override
    public AudioBookBuilder setRunTime(int runTime) {
        book.setRunTime(runTime);
        return this;
    }

    @Override
    public AudioBook build(){
        return book;
    }
}
