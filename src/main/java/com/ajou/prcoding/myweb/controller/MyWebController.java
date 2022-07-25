package com.ajou.prcoding.myweb.controller;

import com.ajou.prcoding.myweb.dto.FavoriteMusicRequestDto;
import com.ajou.prcoding.myweb.entity.FavoriteMusic;
import com.ajou.prcoding.myweb.repository.FavoriteRepository;
import com.ajou.prcoding.myweb.service.MusicService;
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
    @Autowired
    MusicService service;

    @GetMapping(value = "/musicSearch/{name}")
    public MusicList musicSearchByPath(@PathVariable String name){
        return service.searchMusic(name);
    }

    @GetMapping(value="/musicSearch")
    public MusicList musicSearchByParam(@RequestParam(value="term") String name) {
        return service.searchMusic(name);
    }

    @GetMapping(value="/likes")  //Get Favorite Music list from Database
    public List<FavoriteMusic> getLikes() {
        return service.getLikes();
    }

    @GetMapping(value = "/likes/{id}")
    public FavoriteMusic isLiked(@PathVariable String id) { return service.getExist(id); }

    @PostMapping(value="/likes")
    public int postLikes(@RequestBody FavoriteMusicRequestDto favorite) {
        return service.saveFavorite(favorite);
    }

    @DeleteMapping(value = "/likes/{id}")
    public void deleteFavorite(@PathVariable String id){
        service.deleteFavorite(id);
    }
}
