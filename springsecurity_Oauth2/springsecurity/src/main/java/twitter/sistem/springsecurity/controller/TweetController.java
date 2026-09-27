package twitter.sistem.springsecurity.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import twitter.sistem.springsecurity.repository.TweetRepository;

@RestController 
public class TweetController {
    private final TweetRepository tweetRepository;

    public TweetController(TweetRepository tweetRepository){
        this.tweetRepository = tweetRepository;
    }

    @PostMapping("/tweets")
    public ResponseEntity<Void> createTweet(){

    }
}
