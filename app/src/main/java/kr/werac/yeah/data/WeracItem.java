package kr.werac.yeah.data;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by dongja94 on 2016-01-18.
 */
public class WeracItem {
    @SerializedName("_id")
    int mid;
    int status;
    User creator;
    User mc;
    boolean has_mc;
    String reg_date;
    String image;
    String title;
    String title_sub;
    List<String> schedule;
    String location_detail;
    String location_area;
    String date;
    String start_time;
    String end_time;
    int fee;
    int limit_num;
    List<User> guests;
    List<Comment> comments;
    int like;
    List<Integer> likeList;
    List<Integer> image_size;

    public int getPicturePath() {
        return PicturePath;
    }

    public void setPicturePath(int picturePath) {
        PicturePath = picturePath;
    }

    int PicturePath;

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

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public User getMc() {
        return mc;
    }

    public void setMc(User mc) {
        this.mc = mc;
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

    public List<String> getSchedule() {
        return schedule;
    }

    public void setSchedule(List<String> schedule) {
        this.schedule = schedule;
    }

    public List<User> getGuests() {
        return guests;
    }

    public void setGuests(List<User> guests) {
        this.guests = guests;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
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

    public List<Integer> getImage_size() {
        return image_size;
    }

    public void setImage_size(List<Integer> image_size) {
        this.image_size = image_size;
    }

    public List<Integer> getLikeList() {
        return likeList;
    }

    public void setLikeList(List<Integer> likeList) {
        this.likeList = likeList;
    }
}
