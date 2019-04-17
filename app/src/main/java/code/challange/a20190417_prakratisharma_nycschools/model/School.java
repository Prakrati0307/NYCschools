package code.challange.a20190417_prakratisharma_nycschools.model;

import com.google.gson.annotations.SerializedName;

public class School {


    @SerializedName("school_name")
    private String name;

    public School(String name)
    {
        this.name=name;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
