package com.ajou.prcoding.myweb.controller;

import com.ajou.prcoding.myweb.dto.FavoriteMusicRequestDto;
import com.ajou.prcoding.myweb.entity.FavoriteMusic;
import com.ajou.prcoding.myweb.repository.FavoriteRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import com.ajou.prcoding.myweb.dto.MusicList;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;

@RestController
public class MyWebController {
    RestTemplate restTemplate = new RestTemplate();

    @Autowired
    FavoriteRepository albumsRepo;


    @GetMapping(value="/musicSearch/{term}")
    public MusicList musicSearchByPath(@PathVariable String term) {
        try {
            String response = restTemplate.getForObject("https://itunes.apple.com/search?term=" + term + "&entity=album", String.class);
            ObjectMapper mapper = new ObjectMapper();
            MusicList list = mapper.readValue(response, MusicList.class);
            System.out.println(list.getResultCount());
            return list;
        } catch(IOException e) {
            System.out.println(e.toString());
            return null;
        }
    }

    @GetMapping(value="/musicSearch")
    public MusicList musicSearchByParam(@RequestParam String term) {
        try {
            String response = restTemplate.getForObject("https://itunes.apple.com/search?term=" + term + "&entity=album", String.class);
            ObjectMapper mapper = new ObjectMapper();
            MusicList list = mapper.readValue(response, MusicList.class);
            System.out.println(list.getResultCount());
            return list;
        } catch(IOException e) {
            System.out.println(e.toString());
            return null;
        }
    }

    //Get Favorite Music list from Database
    @GetMapping(value="/likes")
    public List<FavoriteMusic> getLikes() {

        try {
            return albumsRepo.findAll();
        } catch (Exception e) {
            System.out.println(e.toString());
            return null;
        }
    }

    @PostMapping(value="/likes")
    @Transactional
    public int postLikes(@RequestBody FavoriteMusicRequestDto favorite) {
        FavoriteMusic music = albumsRepo.save(favorite.toEntity());
        if(music != null) {
            return 1;
        }
        else {
            return 0;
        }
    }

}
