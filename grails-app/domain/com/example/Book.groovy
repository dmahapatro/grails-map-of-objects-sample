package com.example

class Book {
    String name
    Map authors

    static hasMany = [authors: Author]
}
