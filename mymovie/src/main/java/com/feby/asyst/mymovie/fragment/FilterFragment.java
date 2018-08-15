package com.feby.asyst.mymovie.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.feby.asyst.mymovie.R;
import com.feby.asyst.mymovie.utility.Constant;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class FilterFragment extends BottomSheetDialogFragment implements View.OnClickListener, AdapterView.OnItemSelectedListener{

    String year = "";
    String sort = "";

    public interface OnSubmitButtonListener{
        void OnSubmitButton(String year, String sort_by);
    }


    Spinner spinnerYear, spinnerSort;
    Button btnFilter;
    ArrayList<String> listSort = new ArrayList<>();
    ArrayList<String> listYear = new ArrayList<>();
    OnSubmitButtonListener listener;
    String selectedSort, selectedYear;


    public FilterFragment() {
        // Required empty public constructor
    }

    public static FilterFragment newInstance(String year, String sort){
        FilterFragment fragment = new FilterFragment();

        Bundle bundle = new Bundle();
        bundle.putString(Constant.KEY_YEAR, year);
        bundle.putString(Constant.KEY_SORT, sort);

        fragment.setArguments(bundle);

        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_filter, container, false);

        spinnerYear = view.findViewById(R.id.year_spinner);
        spinnerSort = view.findViewById(R.id.sort_by_spinner);
        btnFilter = view.findViewById(R.id.filter_button);



        listSort.add("popularity.desc");
        listSort.add("popularity.asc");
        listSort.add("release_date.desc");
        listSort.add("release_date.asc");

        ArrayAdapter<String> sortAdapter = new ArrayAdapter<>(this.getActivity(),android.R.layout.simple_spinner_item, listSort);
        spinnerSort.setAdapter(sortAdapter);

        listYear.add("none");
        for (int i = 2020; i >= 1900; i--)
        {
            listYear.add(i+"");
        }


        ArrayAdapter<String> yearAdapter = new ArrayAdapter<>(this.getActivity(),android.R.layout.simple_spinner_item, listYear);
        spinnerYear.setAdapter(yearAdapter);

        int yearIndex = getIndex(spinnerYear, year);
        spinnerYear.setSelection(yearIndex);

        int sortIndex = getIndex(spinnerSort, sort);
        spinnerSort.setSelection(sortIndex);

        spinnerSort.setOnItemSelectedListener(this);
        spinnerYear.setOnItemSelectedListener(this);

        btnFilter.setOnClickListener(this);

        if (getArguments() != null){
            selectedSort = getArguments().getString(Constant.KEY_SORT, "");
            selectedYear = getArguments().getString(Constant.KEY_YEAR,"");

            int positionYear = yearAdapter.getPosition(selectedYear);
            spinnerYear.setSelection(positionYear);

            int positionSort = sortAdapter.getPosition(selectedSort);
            spinnerSort.setSelection(positionSort);

        }

        return view;
    }
    
    public int getIndex(Spinner spinner, String value){
        for (int i = 0; i < spinner.getCount(); i++) {
            if (spinner.getItemAtPosition(i).toString().equalsIgnoreCase(value)) {
                return i;
            }
        }

        return 0;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.filter_button:

                selectedSort = spinnerSort.getSelectedItem().toString();
                selectedYear = spinnerYear.getSelectedItem().toString();

                if (selectedYear == "none"){
                    listener.OnSubmitButton("", selectedSort);
                }
                else {
                    listener.OnSubmitButton(selectedYear, selectedSort);
                }
                dismiss();
                break;
        }

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof FilterFragment.OnSubmitButtonListener){
            listener = (FilterFragment.OnSubmitButtonListener)context;
        }
        else {
            throw new RuntimeException(context.toString()+ "activity harus implemen OnSubmitListener");
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
