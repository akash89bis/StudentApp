package com.example.studentapp.view.ui.slideshow.rv;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.studentapp.R;
import com.example.studentapp.model.StudentDetailsResponse;

import java.util.List;

public class StudentListAdapter extends RecyclerView.Adapter<StudentListAdapter.StudentViewHolder> {

    private List<StudentDetailsResponse> studentList;

    public StudentListAdapter(List<StudentDetailsResponse> list) {
        this.studentList = list;
    }

    public void updateStudent(List<StudentDetailsResponse> list) {
        studentList.addAll(list);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public StudentListAdapter.StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_student, parent, false);
        return new StudentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentViewHolder holder, int position) {
        holder.bind(studentList.get(position));
    }

    @Override
    public int getItemCount() {
        return studentList.size();
    }

    class StudentViewHolder extends RecyclerView.ViewHolder {

        private TextView name;
        private TextView email;
        private TextView phn;

        public StudentViewHolder(@NonNull View itemView) {
            super(itemView);
            this.name = itemView.findViewById(R.id.txt_name);
            this.email = itemView.findViewById(R.id.txt_email);
            this.phn = itemView.findViewById(R.id.txt_phn);
        }

        void bind(StudentDetailsResponse student) {
            this.name.setText(String.format("%s %s", student.getFirstName(), student.getLastName()));
            this.email.setText(student.getEmail());
            this.phn.setText(student.getPhoneNumber());
        }

    }

}
