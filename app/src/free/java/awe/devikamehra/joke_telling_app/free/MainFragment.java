package awe.devikamehra.joke_telling_app.free;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

import awe.devikamehra.androiddisplay.JokeDisplayActivity;
import awe.devikamehra.androiddisplay.JokeDisplayFragment;
import awe.devikamehra.joke_telling_app.AsyncTask;
import awe.devikamehra.joke_telling_app.OnJokeReceivedListener;
import awe.devikamehra.joke_telling_app.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment implements OnJokeReceivedListener {

    private ProgressBar mSpinner;
    private InterstitialAd mInterstitialAd;
    private String mJoke;

    public MainFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);

        mInterstitialAd = new InterstitialAd(getActivity());
        mInterstitialAd.setAdUnitId(getString(R.string.interstitial_ad_unit_id));
        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                startJokeActivity();
            }
        });
        loadInterstitialAd();

        Button button = (Button) root.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fetchJoke();
            }
        });

        mSpinner = (ProgressBar) root.findViewById(R.id.progressBar);

        AdView mAdView = (AdView) root.findViewById(R.id.adView);
        // Create an ad request. Check logcat output for the hashed device ID to
        // get test ads on a physical device. e.g.
        // "Use AdRequest.Builder.addTestDevice("ABCDEF012345") to get test ads on this device."
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        mAdView.loadAd(adRequest);
        return root;
    }

    private void loadInterstitialAd() {
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        mInterstitialAd.loadAd(adRequest);
    }

    @Override
    public void onReceived(String joke) {
        mSpinner.setVisibility(View.INVISIBLE);
        mJoke = joke;
        if (mInterstitialAd != null && mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        } else {
            startJokeActivity();
        }
    }

    private void startJokeActivity() {
        Intent intent = new Intent(getActivity(), JokeDisplayActivity.class);
        intent.putExtra(JokeDisplayFragment.KEY, mJoke);
        startActivity(intent);
    }

    public void fetchJoke(){
        mSpinner.setVisibility(View.VISIBLE);
        new AsyncTask().execute(this);
    }

}
