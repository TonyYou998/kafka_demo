package com.example.moviecatalogservice.resource;

import com.example.moviecatalogservice.models.CatalogItem;
import com.example.moviecatalogservice.models.Movie;
import com.example.moviecatalogservice.models.Rating;
import com.example.moviecatalogservice.models.kafka.common.ApplicationConstant;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalog")
@RequiredArgsConstructor
public class MovieCatalogResource {
    private Movie movie;
    private final static Logger LOGGER= LoggerFactory.getLogger(MovieCatalogResource.class);
    private final  RestTemplate restTemplate;
    @GetMapping("/{userId}")
    public List<CatalogItem> getCatalog(@PathVariable("userId") String userId){


           List<Rating> ratings = Arrays.asList(new Rating(1,10),new Rating(2,8));
           return ratings.stream().map(rating -> {
//              Movie movie=restTemplate.getForObject("http://localhost:8081/movies/"+Integer.toString(rating.getMovieId()), Movie.class);
               Movie movie=getMovie();
              return new CatalogItem(movie.getName(), "exc", rating.getRating());
           }).collect(Collectors.toList());
//           return Collections.singletonList(new CatalogItem("avenger","exc",10));
    }
    @KafkaListener(topics = ApplicationConstant.TOPIC_NAME,groupId =ApplicationConstant.GROUP_ID_JSON)
    private void consume(Movie data){
        LOGGER.info(String.format("data receive -> %S",data.toString()));
        setMovie(data);

    }
    public void setMovie(Movie data){
        this.movie=data;

    }
    public Movie getMovie(){
        return this.movie;
    }
}
