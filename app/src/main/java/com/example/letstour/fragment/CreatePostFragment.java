package com.example.letstour.fragment;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.letstour.R;
import com.example.letstour.model.Post;
import com.example.letstour.utils.CommonTask;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Calendar;

public class CreatePostFragment extends Fragment implements View.OnClickListener {

    private TextView tvDateShower;
    private EditText etLocation,etPerson,etCost,etBordering,etDescription;
    private LinearLayout linDatePicker;
    private MaterialButton btCreatePost;
    FirebaseFirestore db=FirebaseFirestore.getInstance();
    private String date,location,person,cost,bordering,description;
    public CreatePostFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        getActivity().setTitle("Create Post");
        return inflater.inflate(R.layout.fragment_create_post, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        linDatePicker=view.findViewById(R.id.lin_lay_create_post_date);
        tvDateShower=view.findViewById(R.id.tv_create_post_date_shower);
        btCreatePost=view.findViewById(R.id.bt_create_post);
        etLocation=view.findViewById(R.id.et_create_post_location);
        etPerson=view.findViewById(R.id.et_create_post_person);
        etCost=view.findViewById(R.id.et_create_post_cost);
        etBordering=view.findViewById(R.id.et_create_post_bordering);
        etDescription=view.findViewById(R.id.et_create_post_description);

    }

    @Override
    public void onStart() {
        super.onStart();
        linDatePicker.setOnClickListener(this);
        btCreatePost.setOnClickListener(this);
    }
    private void showDate() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(),
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        tvDateShower.setText(day + "/" + (month + 1) + "/" + year);
                        String day_name=Integer.toString(day);
                        String month_name=Integer.toString(month+1);
                        String year_name=Integer.toString(year);
                        date=day_name+"-"+month_name+"-"+year_name;

                    }
                }, year, month, dayOfMonth);
        datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
        datePickerDialog.show();
    }

    @Override
    public void onClick(View v) {
        if (v.getId()==R.id.lin_lay_create_post_date){
            showDate();
        }
        else if (v.getId()==R.id.bt_create_post){
            btCreatePost.setVisibility(View.GONE);
            saveData();
        }

    }

    private void saveData() {
        location=etLocation.getText().toString();
        person=etPerson.getText().toString();
        cost=etCost.getText().toString();
        bordering=etBordering.getText().toString();
        description=etDescription.getText().toString();
        if (date==null){
            tvDateShower.setText("Please Set Date");
            btCreatePost.setVisibility(View.VISIBLE);
        }
        if (location==null){
            etLocation.setError("Enter Location");
            btCreatePost.setVisibility(View.VISIBLE);
        }
        if (person==null){
            etPerson.setError("Enter Person");
            btCreatePost.setVisibility(View.VISIBLE);
        }
        if (cost==null)
        {
            etCost.setError("Enter Cost");
            btCreatePost.setVisibility(View.VISIBLE);
        }
        if (bordering==null){
            etBordering.setError("Enter Bordering");
            btCreatePost.setVisibility(View.VISIBLE);
        }

        if (date!=null && location!=null && person!=null && cost!=null && bordering!=null){
            Post post=new Post(Integer.parseInt(person),Integer.parseInt(cost),location,bordering,description,date, CommonTask.getDataFromSharedPreference(getContext(),CommonTask.AGENCY_NAME),CommonTask.getDataFromSharedPreference(getContext(),CommonTask.USER_KEY));
            db.collection("post").add(post)
                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {
                            Toast.makeText(getContext(),"Post Added",Toast.LENGTH_SHORT).show();
                            btCreatePost.setVisibility(View.VISIBLE);
                            tvDateShower.setText("");
                            etLocation.setText("");
                            etPerson.setText("");
                            etCost.setText("");
                            etBordering.setText("");
                            etDescription.setText("");
                        }
                    });
        }

    }
}