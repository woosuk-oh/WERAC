package kr.werac.yeah.data;

import java.util.List;

/**
 * Created by Tacademy on 2016-05-18.
 */
public class Comment {
//    int mid;
    int like;
    User user;
    String date;
    String content;
    List<Comment> recomment;

//    public int getMid() {
//        return mid;
//    }

//    public void setMid(int mid) {
//        this.mid = mid;
//    }

    public int getLike() {
        return like;
    }

    public void setLike(int like) {
        this.like = like;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<Comment> getRecomment() {
        return recomment;
    }

    public void setRecomment(List<Comment> recomment) {
        this.recomment = recomment;
    }
}
