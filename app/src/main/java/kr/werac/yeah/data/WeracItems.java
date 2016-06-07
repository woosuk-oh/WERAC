package kr.werac.yeah.data;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Tacademy on 2016-05-23.
 */
public class WeracItems {
    @SerializedName("result")
    public List<WeracItem> weracs;
}
