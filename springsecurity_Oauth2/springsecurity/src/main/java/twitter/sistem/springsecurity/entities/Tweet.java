package twitter.sistem.springsecurity.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_tweets")
public class Tweet{
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long tweetId;

    private User usuario;
    private String conteudo;

    @CreationTimestamp
    private Instant CreationTimestamp;
}
