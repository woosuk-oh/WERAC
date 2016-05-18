package kr.werac.yeah.data;

import android.graphics.drawable.Drawable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dongja94 on 2016-01-18.
 */
public class WeracItem {
    Drawable icon;
    int mid;
    int status;
    int uid;
    int mc_id;
    boolean has_mc;
    String reg_date;
    String image;
    String title;
    String title_sub;
    ArrayList<String> schedule;
    String location_detail;
    String location_area;
    String date;
    String start_time;
    String end_time;
    int fee;
    int limit_num;
    ArrayList<Integer> guests_id;
    ArrayList<Comment> comments;
    int like;


    public int getPicturePath() {
        return PicturePath;
    }

    public void setPicturePath(int picturePath) {
        PicturePath = picturePath;
    }

    int PicturePath;

    public Drawable getIcon() {
        return icon;
    }

    public void setIcon(Drawable icon) {
        this.icon = icon;
    }

    public int getMid() {
        return mid;
    }

    public void setMid(int mid) {
        this.mid = mid;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getMc_id() {
        return mc_id;
    }

    public void setMc_id(int mc_id) {
        this.mc_id = mc_id;
    }

    public boolean isHas_mc() {
        return has_mc;
    }

    public void setHas_mc(boolean has_mc) {
        this.has_mc = has_mc;
    }

    public String getReg_date() {
        return reg_date;
    }

    public void setReg_date(String reg_date) {
        this.reg_date = reg_date;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle_sub() {
        return title_sub;
    }

    public void setTitle_sub(String title_sub) {
        this.title_sub = title_sub;
    }

    public ArrayList<String> getSchedule() {
        return schedule;
    }

    public void setSchedule(ArrayList<String> schedule) {
        this.schedule = schedule;
    }

    public ArrayList<Integer> getGuests_id() {
        return guests_id;
    }

    public void setGuests_id(ArrayList<Integer> guests_id) {
        this.guests_id = guests_id;
    }

    public ArrayList<Comment> getComments() {
        return comments;
    }

    public void setComments(ArrayList<Comment> comments) {
        this.comments = comments;
    }

    public String getLocation_detail() {
        return location_detail;
    }

    public void setLocation_detail(String location_detail) {
        this.location_detail = location_detail;
    }

    public String getLocation_area() {
        return location_area;
    }

    public void setLocation_area(String location_area) {
        this.location_area = location_area;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public int getFee() {
        return fee;
    }

    public void setFee(int fee) {
        this.fee = fee;
    }

    public int getLimit_num() {
        return limit_num;
    }

    public void setLimit_num(int limit_num) {
        this.limit_num = limit_num;
    }

    public int getLike() {
        return like;
    }

    public void setLike(int like) {
        this.like = like;
    }
}
