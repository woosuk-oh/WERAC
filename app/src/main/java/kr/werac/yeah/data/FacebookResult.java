package kr.werac.yeah.data;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Tacademy on 2016-06-13.
 */
public class FacebookResult {
    @SerializedName("result")
    User myuser;
    int success;

    public int getSuccess() {
        return success;
    }

    public void setSuccess(int success) {
        this.success = success;
    }

    public User getMyuser() {
        return myuser;
    }

    public void setMyuser(User myuser) {
        this.myuser = myuser;
    }
}
