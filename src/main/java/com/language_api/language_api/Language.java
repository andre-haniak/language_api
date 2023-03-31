package com.language_api.language_api;

public record Language (String title, String image, int ranking){

    public Language(String title, String image, int ranking) {
        this.title = title;
        this.image = image;
        this.ranking = ranking;
    }
}
