package com.vedmitryapps.testapp.view.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.vedmitryapps.testapp.R;
import com.vedmitryapps.testapp.model.Institution;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder>{

    private Context mContext;
    private View.OnClickListener mOnClickListener;
    private List<Institution> mInstitutions;

    public RecyclerAdapter(List<Institution> institutions, View.OnClickListener onClickListener) {
        this.mInstitutions = institutions;
        this.mOnClickListener = onClickListener;
    }

    @Override
    public RecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if(mContext == null){
            mContext = parent.getContext();
        }

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.institution_recycler_item, parent, false);
        view.setOnClickListener(mOnClickListener);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(RecyclerAdapter.ViewHolder holder, int position) {

        holder.name.setText(mInstitutions.get(position).getName());
        holder.address.setText(mInstitutions.get(position).getAddress());
        Log.i("TAG", "Bind " + mInstitutions.get(position).getName());

        Glide.with(mContext)
                .load(mInstitutions.get(position).getImageUrl())
                .into(holder.image);
    }

    @Override
    public int getItemCount() {
        return mInstitutions.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.image)
        ImageView image;

        @BindView(R.id.textViewName)
        TextView name;

        @BindView(R.id.textViewAddress)
        TextView address;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
