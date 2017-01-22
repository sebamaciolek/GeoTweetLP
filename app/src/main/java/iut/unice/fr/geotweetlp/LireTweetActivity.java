package iut.unice.fr.geotweetlp;

import android.app.ListActivity;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.twitter.sdk.android.tweetui.SearchTimeline;
import com.twitter.sdk.android.tweetui.TweetTimelineListAdapter;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


public class LireTweetActivity extends ListActivity {

    private Button localizeTweetButton;
    private ArrayList<TweetUser> listTweet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lire_tweet);
        listTweet = new ArrayList<>();
        localizeTweetButton = (Button) findViewById(R.id.localize_tweets_button);

        final SearchTimeline timeline = new SearchTimeline.Builder()
                .query("#LPDev")
                .build();


        final TweetTimelineListAdapter adapter = new TweetTimelineListAdapter.Builder(this)
                .setTimeline(timeline)
                .build();
        setListAdapter(adapter);
        final Geocoder geocoder = new Geocoder(this, Locale.getDefault());

        localizeTweetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < adapter.getCount() - 1; ++i) {
                    // Si le tweet a une position
                    if (adapter.getItem(i).place != null) {

                        String placeName = adapter.getItem(i).place.name;

                        // Je me sert ensuite d'un geocodeur pour demander à google les coordonnées de cette ville
                        List<Address> addresses = new ArrayList<>();
                        try {
                            // Je récupère le nom de la ville où il est situé ce qui va permettre de récupérer les coordonnées
                            addresses = geocoder.getFromLocationName(placeName, 1);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        String tweetText = adapter.getItem(i).text;

                        TweetUser tweet = new TweetUser(tweetText, addresses.get(0).getLongitude(), addresses.get(0).getLatitude());
                        listTweet.add(tweet);
                    }
                }

                Intent intent = new Intent(LireTweetActivity.this, LocaliserTweetActivity.class);

                intent.putExtra("listTweet", listTweet);
                startActivity(intent);

            }
        });


    }


}
