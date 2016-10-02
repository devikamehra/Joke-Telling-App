package awe.devikamehra.joke_telling_app.free;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

import awe.devikamehra.androiddisplay.JokeDisplayActivity;
import awe.devikamehra.androiddisplay.JokeDisplayFragment;
import awe.devikamehra.joke_telling_app.AsyncTask;
import awe.devikamehra.joke_telling_app.OnJokeReceivedListener;
import awe.devikamehra.joke_telling_app.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment implements OnJokeReceivedListener{

    private ProgressBar mSpinner;

    public MainFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);

        Button button = (Button) root.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity();
            }
        });

        mSpinner = (ProgressBar) root.findViewById(R.id.progressBar);

        return root;
    }

    @Override
    public void onReceived(String joke) {
        mSpinner.setVisibility(View.INVISIBLE);
        Intent intent = new Intent(getActivity(), JokeDisplayActivity.class);
        intent.putExtra(JokeDisplayFragment.KEY, joke);
        startActivity(intent);
    }

    public void startActivity(){
        mSpinner.setVisibility(View.VISIBLE);
        new AsyncTask().execute(this);
    }

}
