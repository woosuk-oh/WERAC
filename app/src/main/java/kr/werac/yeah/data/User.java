package kr.werac.yeah.data;

import java.util.List;

/**
 * Created by Tacademy on 2016-05-16.
 */
public class User {
    int uid;
    String email;
    String pw;
    String name;
    String nickname;
    String comment;
    String phone;
    List<WeracItem> history_MC;
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

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
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

    public List<WeracItem> getHistory_MC() {
        return history_MC;
    }

    public void setHistory_MC(List<WeracItem> history_MC) {
        this.history_MC = history_MC;
    }

    public List<WeracItem> getHistory_create() {
        return history_create;
    }

    public void setHistory_create(List<WeracItem> history_create) {
        this.history_create = history_create;
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
