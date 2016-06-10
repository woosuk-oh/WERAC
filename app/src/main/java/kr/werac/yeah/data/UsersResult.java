package kr.werac.yeah.data;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Tacademy on 2016-06-10.
 */
public class UsersResult {
    @SerializedName("result")
    Users myusers;

    public Users getMyusers() {
        return myusers;
    }

    public void setMyusers(Users myusers) {
        this.myusers = myusers;
    }
}
