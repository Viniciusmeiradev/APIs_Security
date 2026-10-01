package twitter.sistem.springsecurity.controller.dto;

import twitter.sistem.springsecurity.entities.User;

public record FeedItemDto(long tweetId, String conteudo, User usuario) {
    
}

