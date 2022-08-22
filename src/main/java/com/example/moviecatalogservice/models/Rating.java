package com.example.moviecatalogservice.models;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class Rating {
   private final int  movieId;
    private final int  rating;
}
