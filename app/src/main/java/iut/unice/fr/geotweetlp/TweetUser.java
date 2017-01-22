package iut.unice.fr.geotweetlp;

import java.io.Serializable;

/**
 * Created by Razor on 22/01/2017.
 */

public class TweetUser implements Serializable{

    private String tweetName;
    private double longitude;
    private double latitude;

    public TweetUser(String tweetName, double longitude, double latitude) {
        this.tweetName = tweetName;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public String getTweetName() {
        return tweetName;
    }

    public double getLongitude() {
        return longitude;
    }

    public double getLatitude() {
        return latitude;
    }
}
