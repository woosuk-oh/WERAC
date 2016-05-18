package kr.werac.yeah.data;

import android.graphics.drawable.Drawable;

import java.util.List;

/**
 * Created by dongja94 on 2016-01-18.
 */
public class WeracItem {
    Drawable icon;
    String title;
    String subtitle;
    int state;
    String Creater;
    String MC;
    String descrbe;
    String schedule;
    String spot;
    String region;
    String date;
    String time;

    public int getPicturePath() {
        return PicturePath;
    }

    public void setPicturePath(int picturePath) {
        PicturePath = picturePath;
    }

    int PicturePath;
    String PictureURL;
    String fee;
    Boolean hasMC;
    int NowMemberNum;
    List<Member> Members;
    int FixedMemberNum;
    List<Reply> Replys;
    int ReplyCOunt;

    public int getReplyCOunt() {
        return ReplyCOunt;
    }

    public void setReplyCOunt(int replyCOunt) {
        ReplyCOunt = replyCOunt;
    }

    public Drawable getIcon() {
        return icon;
    }

    public void setIcon(Drawable icon) {
        this.icon = icon;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getCreater() {
        return Creater;
    }

    public void setCreater(String creater) {
        Creater = creater;
    }

    public String getMC() {
        return MC;
    }

    public void setMC(String MC) {
        this.MC = MC;
    }

    public String getDescrbe() {
        return descrbe;
    }

    public void setDescrbe(String descrbe) {
        this.descrbe = descrbe;
    }

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    public String getSpot() {
        return spot;
    }

    public void setSpot(String spot) {
        this.spot = spot;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPictureURL() {
        return PictureURL;
    }

    public void setPictureURL(String pictureURL) {
        PictureURL = pictureURL;
    }

    public String getFee() {
        return fee;
    }

    public void setFee(String fee) {
        this.fee = fee;
    }

    public Boolean getHasMC() {
        return hasMC;
    }

    public void setHasMC(Boolean hasMC) {
        this.hasMC = hasMC;
    }

    public int getNowMemberNum() {
        return NowMemberNum;
    }

    public void setNowMemberNum(int nowMemberNum) {
        NowMemberNum = nowMemberNum;
    }

    public List<Member> getMembers() {
        return Members;
    }

    public void setMembers(List<Member> members) {
        Members = members;
    }

    public int getFixedMemberNum() {
        return FixedMemberNum;
    }

    public void setFixedMemberNum(int fixedMemberNum) {
        FixedMemberNum = fixedMemberNum;
    }

    public List<Reply> getReplys() {
        return Replys;
    }

    public void setReplys(List<Reply> replys) {
        Replys = replys;
    }
}
