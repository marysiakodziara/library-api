package com.example.libraryapi.enums;

import java.util.List;

public enum GenreEnum {

    SCIENCE_FICTION(MainGenreEnum.FIRST),
    SATIRE(MainGenreEnum.SECOND),
    DRAMA(MainGenreEnum.THIRD),
    ACTION_AND_ADVENTURE(MainGenreEnum.FOURTH),
    ROMANCE(MainGenreEnum.FIFTH),
    MYSTERY(MainGenreEnum.SIXTH),
    HORROR(MainGenreEnum.SEVENTH),
    SELF_HELP(MainGenreEnum.EIGHT),
    GUIDE(MainGenreEnum.NINTH),
    TRAVEL(MainGenreEnum.TENTH),
    CHILDREN(MainGenreEnum.FIRST),
    RELIGION_SPIRITUALITY_AND_NEW_AGE(MainGenreEnum.SECOND),
    SCIENCE(MainGenreEnum.THIRD),
    HISTORY(MainGenreEnum.FOURTH),
    MATH(MainGenreEnum.FIFTH),
    ANTHOLOGY(MainGenreEnum.SIXTH),
    POETRY(MainGenreEnum.SEVENTH),
    ENCYCLOPEDIAS(MainGenreEnum.EIGHT),
    DICTIONARIES(MainGenreEnum.NINTH),
    COMICS(MainGenreEnum.TENTH),
    ART(MainGenreEnum.FIRST),
    COOKBOOKS(MainGenreEnum.SECOND),
    DIARIES(MainGenreEnum.THIRD),
    JOURNALS(MainGenreEnum.FOURTH),
    PRAYER_BOOKS(MainGenreEnum.FIFTH),
    SERIES(MainGenreEnum.SIXTH),
    TRILOGY(MainGenreEnum.SEVENTH),
    BIOGRAPHIES(MainGenreEnum.EIGHT),
    AUTOBIOGRAPHIES(MainGenreEnum.NINTH),
    FANTASY(MainGenreEnum.TENTH);

    final MainGenreEnum mainGenreEnum;

    GenreEnum(MainGenreEnum mainGenreEnum) {
        this.mainGenreEnum = mainGenreEnum;
    }
    public List<GenreEnum> getGenres() {
        return List.of(FANTASY, ART);
    }

    public MainGenreEnum getMainGenreEnum() {
        return mainGenreEnum;
    }
}
