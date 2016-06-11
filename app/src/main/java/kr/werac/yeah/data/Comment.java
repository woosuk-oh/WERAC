package kr.werac.yeah.data;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Tacademy on 2016-05-18.
 */
public class Comment {
//    int mid;
    @SerializedName("_id")
    String cmmtid;
    int like;
    User user;
    String date;
    String content;
    List<Integer> likeList;
    List<Comment> reply;

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

    public List<Comment> getReply() {
        return reply;
    }

    public void setReply(List<Comment> reply) {
        this.reply = reply;
    }

    public List<Integer> getLikeList() {
        return likeList;
    }

    public void setLikeList(List<Integer> likeList) {
        this.likeList = likeList;
    }

    public String getCmmtid() {
        return cmmtid;
    }

    public void setCmmtid(String cmmtid) {
        this.cmmtid = cmmtid;
    }
}
