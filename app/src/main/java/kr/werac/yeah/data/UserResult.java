package kr.werac.yeah.data;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Tacademy on 2016-05-25.
 */
public class UserResult {
    @SerializedName("result")
    public User user;
    int success;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getSuccess() {
        return success;
    }

    public void setSuccess(int success) {
        this.success = success;
    }
}
