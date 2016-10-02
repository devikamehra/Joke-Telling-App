package awe.devikamehra.androiddisplay;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class JokeDisplayFragment extends Fragment {

    public static final String KEY = "key";

    public JokeDisplayFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_joke_display, container, false);
        Intent intent = getActivity().getIntent();
        String joke = intent.getStringExtra(KEY);
        TextView jokeTextView = (TextView) root.findViewById(R.id.textView);
        if (joke != null && joke.length() != 0) {
            jokeTextView.setText(joke);
        }

        return root;    }

}
