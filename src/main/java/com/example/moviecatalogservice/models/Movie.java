package com.example.moviecatalogservice.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Movie {

    private  int movieId;
    private  String name;
    public Movie(){

    }
    @Override
    public String toString(){
        return "movieId "+Integer.toString(movieId)+" name "+name;
    }

}
