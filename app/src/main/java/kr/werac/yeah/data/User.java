package kr.werac.yeah.data;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Tacademy on 2016-05-16.
 */
public class User {
    @SerializedName("_id")
    int uid;
    String email;
    String pw;
    String name;
    String comment;
    String phone;
    String ImageURL;
    String date;
    List<WeracItem> history_mc;
    List<WeracItem> history_create;
    List<WeracItem> history_join;
    List<WeracItem> history_like;
    List<Alarm> alarms;

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String id) {
        email = email;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public List<WeracItem> getHistory_mc() {
        return history_mc;
    }

    public void setHistory_mc(List<WeracItem> history_mc) {
        this.history_mc = history_mc;
    }

    public List<WeracItem> getHistory_create() {
        return history_create;
    }

    public void setHistory_create(List<WeracItem> history_create) {
        this.history_create = history_create;
    }

    public String getImageURL() {
        return ImageURL;
    }

    public void setImageURL(String imageURL) {
        ImageURL = imageURL;
    }
    public List<WeracItem> getHistory_join() {
        return history_join;
    }

    public void setHistory_join(List<WeracItem> history_join) {
        this.history_join = history_join;
    }

    public List<WeracItem> getHistory_like() {
        return history_like;
    }

    public void setHistory_like(List<WeracItem> history_like) {
        this.history_like = history_like;
    }

    public List<Alarm> getAlarms() {
        return alarms;
    }

    public void setAlarms(List<Alarm> alarms) {
        this.alarms = alarms;
    }
}
