package me.starlite.ratemyprof;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class NameListFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_name_list, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        ListView profList = (ListView) view.findViewById(R.id.profList);
        adaptList(profList, new String[]{"John Baugh", "Kiumi Akingbehin", "Bruce Elebogen", "David Yoon", "Jinhua Guo"});

        profList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Bundle pos = new Bundle();
                pos.putInt("position", position);

                DetailsFragment df = new DetailsFragment();
                df.setArguments(pos);

                android.app.FragmentManager fm = getFragmentManager();

                View fgDetail = getActivity().findViewById(R.id.fgDetail);

                if (fgDetail == null) {
                    fm.beginTransaction()
                            .replace(R.id.fg, df)
                            .addToBackStack("stateNameList")
                            .commit();
                }
                else {
                    fm.beginTransaction()
                            .replace(R.id.fgDetail, df)
                            .commit();
                }
            }
        });

    }

    private void adaptList(ListView listView, String[] list){
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this.getActivity(), android.R.layout.simple_list_item_1, list);
        listView.setAdapter(adapter);
    }
}