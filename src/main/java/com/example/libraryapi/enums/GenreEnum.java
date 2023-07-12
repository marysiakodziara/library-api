package com.example.libraryapi.enums;

import java.util.List;

public enum GenreEnum {

    SCIENCE_FICTION(MainGenreEnum.FIRST),
    SATIRE(MainGenreEnum.FIRST),
    DRAMA(MainGenreEnum.FIRST),
    ACTION_AND_ADVENTURE(MainGenreEnum.FIRST),
    ROMANCE(MainGenreEnum.FIRST),
    MYSTERY(MainGenreEnum.FIRST),
    HORROR(MainGenreEnum.FIRST),
    SELF_HELP(MainGenreEnum.FIRST),
    GUIDE(MainGenreEnum.FIRST),
    TRAVEL(MainGenreEnum.FIRST),
    CHILDREN(MainGenreEnum.FIRST),
    RELIGION_SPIRITUALITY_AND_NEW_AGE(MainGenreEnum.FIRST),
    SCIENCE(MainGenreEnum.FIRST),
    HISTORY(MainGenreEnum.FIRST),
    MATH(MainGenreEnum.FIRST),
    ANTHOLOGY(MainGenreEnum.FIRST),
    POETRY(MainGenreEnum.FIRST),
    ENCYCLOPEDIAS(MainGenreEnum.FIRST),
    DICTIONARIES(MainGenreEnum.FIRST),
    COMICS(MainGenreEnum.FIRST),
    ART(MainGenreEnum.FIRST),
    COOKBOOKS(MainGenreEnum.FIRST),
    DIARIES(MainGenreEnum.FIRST),
    JOURNALS(MainGenreEnum.FIRST),
    PRAYER_BOOKS(MainGenreEnum.FIRST),
    SERIES(MainGenreEnum.FIRST),
    TRILOGY(MainGenreEnum.FIRST),
    BIOGRAPHIES(MainGenreEnum.FIRST),
    AUTOBIOGRAPHIES(MainGenreEnum.FIRST),
    FANTASY(MainGenreEnum.FIRST);

    final MainGenreEnum mainGenreEnum;

    GenreEnum(MainGenreEnum mainGenreEnum) {
        this.mainGenreEnum = mainGenreEnum;
    }
    public List<GenreEnum> getGenres() {
        return List.of(FANTASY, ART);
    }
}
