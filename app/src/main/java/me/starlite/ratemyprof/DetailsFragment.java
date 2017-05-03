package me.starlite.ratemyprof;

import android.app.Fragment;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import java.net.URI;

public class DetailsFragment extends Fragment{

    private int position;
    private SharedPreferences prefs;

    private static String[] name = {"John Baugh", "Kiumi Akingbehin", "Bruce Elebogen", "David Yoon", "Jinhua Guo", };
    private static int photoRes[] = {R.mipmap.jb, R.mipmap.ka, R.mipmap.be, R.mipmap.dy, R.mipmap.jg};
    private static String[][] degree = {
            {"Ph.D (in progress), Information Systems Engineering", "MS, Computer Science", "BS, Computer Science", },
            {"Ph.D, Computer Science", "MA, Computer Science", "MS, Nuclear Engineering", "BS, Electrical Engineering", },
            {"Ph.D, Applied Mathematics", "MS, Computer Science", "BA, Math and Physics", },
            {"Ph.D, Computer Science", },
            {"Ph.D, Computer Science", "M.Eng, Computer Science", "B.Eng, Computer Science", },
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        prefs = PreferenceManager.getDefaultSharedPreferences(this.getActivity());

        this.position = getArguments().getInt("position");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_details, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setName();
        setPhoto();
        setDegreeList();
        setRatingAndRatingBar();
    }

    private void setName() {
        TextView tvName = (TextView) this.getActivity().findViewById(R.id.tvName);
        tvName.setText(name[position]);
    }

    private void setPhoto() {
        ImageView ivPhoto = (ImageView) this.getActivity().findViewById(R.id.ivPhoto);
        ivPhoto.setImageResource(photoRes[position]);
    }

    private void setDegreeList() {
        ListView listView = (ListView) this.getActivity().findViewById(R.id.lvDegree);
        adaptList(listView, degree[position]);
    }

    private void setRatingAndRatingBar() {

        float rating = Float.valueOf(prefs.getString(name[position], "3.5"));

        TextView tvRating = (TextView) this.getActivity().findViewById(R.id.tvRating);
        tvRating.setText((String.valueOf(rating) + "/ 5.0"));
        prefs.edit().putString(name[position], String.valueOf(rating)).apply();

        RatingBar rb = (RatingBar) this.getActivity().findViewById(R.id.rbRating);
        rb.setRating(rating);

        rb.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                prefs.edit().putString(name[position], String.valueOf(rating)).apply();
                ((TextView) getActivity().findViewById(R.id.tvRating)).setText((String.valueOf(rating) + "/ 5.0"));
                ratingBar.setRating(rating);
            }
        });
    }

    private void adaptList(ListView listView, String[] list){
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this.getActivity(), android.R.layout.simple_list_item_1, list);
        listView.setAdapter(adapter);
    }
}
