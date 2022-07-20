package com.ajou.prcoding.myweb.dto;

import com.ajou.prcoding.myweb.entity.FavoriteMusic;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class FavoriteMusicRequestDto {
    private String collectionId;
    private String collectionType;
    private String artistId;
    private String artistName;
    private String artistViewUrl;
    private String collectionName;
    private String collectionViewUrl;

    public FavoriteMusic toEntity() {
        FavoriteMusic music = new FavoriteMusic();
        music.setCollectionId(this.collectionId);
        music.setCollectionId(this.collectionType);
        music.setCollectionId(this.artistId);
        music.setCollectionId(this.artistName);
        music.setCollectionId(this.artistViewUrl);
        music.setCollectionId(this.collectionName);
        music.setCollectionId(this.collectionViewUrl);
        return music;
    }
}
