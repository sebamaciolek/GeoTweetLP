package iut.unice.fr.geotweetlp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

public class LocaliserTweetActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private List<TweetUser> listeTweetUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        listeTweetUser = new ArrayList<>();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_geolocalisation);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        Intent intent = getIntent();
        listeTweetUser = (ArrayList<TweetUser>) intent.getSerializableExtra("listTweet");

    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        for(TweetUser tweetText : listeTweetUser){
            LatLng tweetPosition = new LatLng(tweetText.getLatitude(), tweetText.getLongitude());
            mMap.addMarker(new MarkerOptions().position(tweetPosition).title(tweetText.getTweetName()));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(tweetPosition));
        }

    }
}
