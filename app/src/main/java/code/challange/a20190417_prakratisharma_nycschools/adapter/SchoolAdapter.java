package code.challange.a20190417_prakratisharma_nycschools.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import code.challange.a20190417_prakratisharma_nycschools.R;
import code.challange.a20190417_prakratisharma_nycschools.model.School;

public class SchoolAdapter extends RecyclerView.Adapter<SchoolAdapter.SchoolViewHolder> {

    private Context context;
    private List<School> schoolList;
    private View.OnClickListener listener;

    public SchoolAdapter(Context context, List<School> regions) {
        this.context = context;
        this.schoolList = regions;

    }

    public void setSchoolList(List<School> schoolList) {
        this.schoolList = schoolList;
        notifyDataSetChanged();
    }

    public void setClickListener(View.OnClickListener callback) {
        listener = callback;
    }
    @Override
    public SchoolViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.school_item, parent, false);
        SchoolViewHolder holder = new SchoolViewHolder(v);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onClick(view);
            }
        });
        return holder;
    }
// settting the data
    @Override
    public void onBindViewHolder(SchoolViewHolder holder, int position) {
        holder.setSchoolItem(schoolList.get(position));
        holder.itemView.setOnClickListener(listener);
    }

    @Override
    public int getItemCount() {

        return schoolList != null ? schoolList.size() : 0;
    }

// view holder class
    class SchoolViewHolder extends RecyclerView.ViewHolder {

        private TextView schoolName;

        SchoolViewHolder(View regionItem) {
            super(regionItem);
            schoolName = regionItem.findViewById(R.id.school_name);
        }

        private void setSchoolItem(School region) {
            schoolName.setText(region.getName());
        }

    }
}