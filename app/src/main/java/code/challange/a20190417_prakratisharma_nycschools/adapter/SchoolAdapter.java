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

public class SchoolAdapter extends RecyclerView.Adapter<SchoolAdapter.RegionViewHolder> {

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
    public RegionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.school_item, parent, false);
        RegionViewHolder holder = new RegionViewHolder(v);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onClick(view);
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(RegionViewHolder holder, int position) {
        holder.setRegionItem(schoolList.get(position));
        holder.itemView.setOnClickListener(listener);
    }

    @Override
    public int getItemCount() {
        return schoolList != null ? schoolList.size() : 0;
    }

    class RegionViewHolder extends RecyclerView.ViewHolder {

        private TextView regionName;

        RegionViewHolder(View regionItem) {
            super(regionItem);
            regionName = regionItem.findViewById(R.id.region_name);
        }

        private void setRegionItem(School region) {
            regionName.setText(region.getName());
        }

    }
}