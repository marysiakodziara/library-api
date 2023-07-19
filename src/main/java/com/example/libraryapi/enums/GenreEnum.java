package com.example.libraryapi.enums;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public enum GenreEnum {

    @JsonProperty("SCIENCE_FICTION")
    SCIENCE_FICTION(MainGenreEnum.FIRST),
    @JsonProperty("SATIRE")
    SATIRE(MainGenreEnum.SECOND),
    @JsonProperty("DRAMA")
    DRAMA(MainGenreEnum.THIRD),
    @JsonProperty("ACTION_AND_ADVENTURE")
    ACTION_AND_ADVENTURE(MainGenreEnum.FOURTH),
    @JsonProperty("ROMANCE")
    ROMANCE(MainGenreEnum.FIFTH),
    @JsonProperty("MYSTERY")
    MYSTERY(MainGenreEnum.SIXTH),
    @JsonProperty("HORROR")
    HORROR(MainGenreEnum.SEVENTH),
    @JsonProperty("SELF_HELP")
    SELF_HELP(MainGenreEnum.EIGHT),
    @JsonProperty("GUIDE")
    GUIDE(MainGenreEnum.NINTH),
    @JsonProperty("TRAVEL")
    TRAVEL(MainGenreEnum.TENTH),
    @JsonProperty("CHILDREN")
    CHILDREN(MainGenreEnum.FIRST),
    @JsonProperty("RELIGION_SPIRITUALITY_AND_NEW_AGE")
    RELIGION_SPIRITUALITY_AND_NEW_AGE(MainGenreEnum.SECOND),
    @JsonProperty("SCIENCE")
    SCIENCE(MainGenreEnum.THIRD),
    @JsonProperty("HISTORY")
    HISTORY(MainGenreEnum.FOURTH),
    @JsonProperty("MATH")
    MATH(MainGenreEnum.FIFTH),
    @JsonProperty("ANTHOLOGY")
    ANTHOLOGY(MainGenreEnum.SIXTH),
    @JsonProperty("POETRY")
    POETRY(MainGenreEnum.SEVENTH),
    @JsonProperty("ENCYCLOPEDIAS")
    ENCYCLOPEDIAS(MainGenreEnum.EIGHT),
    @JsonProperty("DICTIONARIES")
    DICTIONARIES(MainGenreEnum.NINTH),
    @JsonProperty("COMICS")
    COMICS(MainGenreEnum.TENTH),
    @JsonProperty("ART")
    ART(MainGenreEnum.FIRST),
    @JsonProperty("COOKBOOKS")
    COOKBOOKS(MainGenreEnum.SECOND),
    @JsonProperty("DIARIES")
    DIARIES(MainGenreEnum.THIRD),
    @JsonProperty("JOURNALS")
    JOURNALS(MainGenreEnum.FOURTH),
    @JsonProperty("PRAYER_BOOKS")
    PRAYER_BOOKS(MainGenreEnum.FIFTH),
    @JsonProperty("SERIES")
    SERIES(MainGenreEnum.SIXTH),
    @JsonProperty("TRILOGY")
    TRILOGY(MainGenreEnum.SEVENTH),
    @JsonProperty("BIOGRAPHIES")
    BIOGRAPHIES(MainGenreEnum.EIGHT),
    @JsonProperty("AUTOBIOGRAPHIES")
    AUTOBIOGRAPHIES(MainGenreEnum.NINTH),
    @JsonProperty("FANTASY")
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
