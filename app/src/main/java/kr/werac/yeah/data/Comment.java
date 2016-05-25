package kr.werac.yeah.data;

/**
 * Created by Tacademy on 2016-05-18.
 */
public class Comment {
//    int mid;
    int like;
    int uid;
    String date;
    String content;

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

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
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
}
