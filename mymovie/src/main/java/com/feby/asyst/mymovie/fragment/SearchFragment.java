package com.feby.asyst.mymovie.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.feby.asyst.mymovie.R;
import com.feby.asyst.mymovie.utility.Constant;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends BottomSheetDialogFragment implements View.OnClickListener {

    public interface OnSubmitButtonListener{
        void OnSubmitButton(String search);
    }

    EditText etSearch;
    Button btnSearch;
    OnSubmitButtonListener listener;
    String keyword;

    public SearchFragment() {
        // Required empty public constructor
    }

    public static SearchFragment Instance(String search){
        SearchFragment fragment = new SearchFragment();

        Bundle bundle = new Bundle();
        bundle.putString(Constant.KEY_SEARCH, search);

        fragment.setArguments(bundle);

        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search, container, false);

        etSearch = view.findViewById(R.id.search_edittext);
        btnSearch = view.findViewById(R.id.search_button);

        btnSearch.setOnClickListener(this);

        if (getArguments() != null){
            etSearch.setText(getArguments().getString(Constant.KEY_SEARCH, ""));
        }

        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.search_button:
                if (!TextUtils.isEmpty(etSearch.getText().toString())){
                keyword = etSearch.getText().toString();

                listener.OnSubmitButton(keyword);

                dismiss();
                } else{
                    Toast.makeText(getActivity(), "HARAP ISI FIELD!", Toast.LENGTH_SHORT).show();
                }

                break;
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof SearchFragment.OnSubmitButtonListener){
            listener = (SearchFragment.OnSubmitButtonListener)context;
        }
        else {
            throw new RuntimeException(context.toString()+ "activity harus implemen OnSubmitListener");
        }
    }
}
