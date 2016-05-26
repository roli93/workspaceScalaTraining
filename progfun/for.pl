hasWrittenTwo(Books,Author) :-
    member(Book1,Books),
    member(Book2,Books),
    Book1 /= Book2,
    author(Book1,Author),
    author(Book2,Author).
