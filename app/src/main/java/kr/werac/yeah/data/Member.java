package kr.werac.yeah.data;

import java.util.List;

/**
 * Created by Tacademy on 2016-05-16.
 */
public class Member {
    int userId;;
    String Id;
    String password;
    String nickname;
    String phone;
    String introduce;
    List<WeracItem> historyMC;
    List<WeracItem> historyCreate;
    List<WeracItem> historyParticipate;
    List<WeracItem> historyLike;
    List<Alarm> Alarms;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public List<WeracItem> getHistoryMC() {
        return historyMC;
    }

    public void setHistoryMC(List<WeracItem> historyMC) {
        this.historyMC = historyMC;
    }

    public List<WeracItem> getHistoryCreate() {
        return historyCreate;
    }

    public void setHistoryCreate(List<WeracItem> historyCreate) {
        this.historyCreate = historyCreate;
    }

    public List<WeracItem> getHistoryParticipate() {
        return historyParticipate;
    }

    public void setHistoryParticipate(List<WeracItem> historyParticipate) {
        this.historyParticipate = historyParticipate;
    }

    public List<WeracItem> getHistoryLike() {
        return historyLike;
    }

    public void setHistoryLike(List<WeracItem> historyLike) {
        this.historyLike = historyLike;
    }

    public List<Alarm> getAlarms() {
        return Alarms;
    }

    public void setAlarms(List<Alarm> alarms) {
        Alarms = alarms;
    }
}
