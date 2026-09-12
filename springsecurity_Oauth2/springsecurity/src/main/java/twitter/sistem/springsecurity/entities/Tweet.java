package twitter.sistem.springsecurity.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_tweets")
public class Tweet{
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "tweet_id")
    private Long tweetId;

    private User usuario;
    private String conteudo;

    @CreationTimestamp
    private Instant creationTimestamp;

    public Long getTweetId(){
        return tweetId;
    }
    public void setTweetId(Long tweetId){
        this.tweetId = tweetId;
    }

    public User getUsuario(){
        return usuario;
    }
    public void setUsuario(User usuario){
        this.usuario = usuario;
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
