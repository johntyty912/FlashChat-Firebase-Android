package com.johntyty912.flashchatnewfirebase;

class InstantMessage {
    private String message;
    private String author;
    private String date;

    InstantMessage(String message, String author, String date) {
        this.message = message;
        this.author = author;
        this.date = date;
    }

    public InstantMessage() {
    }

    String getMessage() {
        return message;
    }

    String getAuthor() {
        return author;
    }

    String getDate() {
        return date;
    }
}
