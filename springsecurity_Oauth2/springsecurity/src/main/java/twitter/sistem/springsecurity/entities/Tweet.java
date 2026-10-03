package twitter.sistem.springsecurity.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_tweets")
public class Tweet{
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "tweet_id")
    private Long tweetId;

    @ManyToOne
    @JoinColumn(name = 'user_id')
    private User user;


    private String conteudo;

    @CreationTimestamp
    private Instant creationTimestamp;

    public Long getTweetId(){
        return tweetId;
    }
    public void setTweetId(Long tweetId){
        this.tweetId = tweetId;
    }

    public User getUser(){
        return user;
    }
    public void setUsuario(User user){
        this.user = user;
    }

    public String getConteudo(){
        return conteudo;
    }
    public void setConteudo(String conteudo){
        this.conteudo = conteudo;
    }

    public Instant getCreationTimestamp(){
        return creationTimestamp;
    }

    public void setCreationTimestamp(Instant creationTimestamp){
        this.creationTimestamp = creationTimestamp;
    }
}
