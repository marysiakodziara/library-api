package com.example.libraryapi.enums;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public enum GenreEnum {

    @JsonProperty("SCIENCE_FICTION")
    SCIENCE_FICTION(MainGenreEnum.FICTION),
    @JsonProperty("SATIRE")
    SATIRE(MainGenreEnum.NON_FICTION),
    @JsonProperty("DRAMA")
    DRAMA(MainGenreEnum.POETRY),
    @JsonProperty("ACTION_AND_ADVENTURE")
    ACTION_AND_ADVENTURE(MainGenreEnum.DRAMA),
    @JsonProperty("ROMANCE")
    ROMANCE(MainGenreEnum.MYSTERY),
    @JsonProperty("MYSTERY")
    MYSTERY(MainGenreEnum.ROMANCE),
    @JsonProperty("HORROR")
    HORROR(MainGenreEnum.FANTASY),
    @JsonProperty("SELF_HELP")
    SELF_HELP(MainGenreEnum.SCIENCE),
    @JsonProperty("GUIDE")
    GUIDE(MainGenreEnum.HISTORY),
    @JsonProperty("TRAVEL")
    TRAVEL(MainGenreEnum.NON_FICTION),
    @JsonProperty("CHILDREN")
    CHILDREN(MainGenreEnum.FICTION),
    @JsonProperty("RELIGION_SPIRITUALITY_AND_NEW_AGE")
    RELIGION_SPIRITUALITY_AND_NEW_AGE(MainGenreEnum.NON_FICTION),
    @JsonProperty("HISTORY")
    HISTORY(MainGenreEnum.HISTORY),
    @JsonProperty("MATH")
    MATH(MainGenreEnum.SCIENCE),
    @JsonProperty("ANTHOLOGY")
    ANTHOLOGY(MainGenreEnum.ROMANCE),
    @JsonProperty("POETRY")
    POETRY(MainGenreEnum.POETRY),
    @JsonProperty("ENCYCLOPEDIAS")
    ENCYCLOPEDIAS(MainGenreEnum.NON_FICTION),
    @JsonProperty("DICTIONARIES")
    DICTIONARIES(MainGenreEnum.NON_FICTION),
    @JsonProperty("COMICS")
    COMICS(MainGenreEnum.FICTION),
    @JsonProperty("ART")
    ART(MainGenreEnum.HISTORY),
    @JsonProperty("COOKBOOKS")
    COOKBOOKS(MainGenreEnum.NON_FICTION),
    @JsonProperty("DIARIES")
    DIARIES(MainGenreEnum.BIOGRAPHY),
    @JsonProperty("JOURNALS")
    JOURNALS(MainGenreEnum.BIOGRAPHY),
    @JsonProperty("PRAYER_BOOKS")
    PRAYER_BOOKS(MainGenreEnum.NON_FICTION),
    @JsonProperty("SERIES")
    SERIES(MainGenreEnum.DRAMA),
    @JsonProperty("TRILOGY")
    TRILOGY(MainGenreEnum.MYSTERY),
    @JsonProperty("BIOGRAPHIES")
    BIOGRAPHIES(MainGenreEnum.BIOGRAPHY),
    @JsonProperty("AUTOBIOGRAPHIES")
    AUTOBIOGRAPHIES(MainGenreEnum.BIOGRAPHY),
    @JsonProperty("FANTASY")
    FANTASY(MainGenreEnum.FANTASY),
    @JsonProperty("BEST_SELLER")
    BEST_SELLER(MainGenreEnum.FICTION);

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
