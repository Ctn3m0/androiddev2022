package vn.edu.usth.weather;

import java.util.UUID;

public class Weather {
    private UUID mId;
    private String mTitle;
    public Weather(){
        // Generate unique identifier
        mId = UUID.randomUUID();
    }

    public UUID getmId() {
        return mId;
    }

    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }
}
