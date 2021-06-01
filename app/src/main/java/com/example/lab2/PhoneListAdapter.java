package com.example.lab2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PhoneListAdapter extends RecyclerView.Adapter<PhoneListAdapter.PhoneViewHolder> {
    private static final String TAG = PhoneListAdapter.class.getSimpleName();

    private LayoutInflater mLayoutInflater;
    private List<Phone> mPhoneList;

    private OnPhoneClickListener onPhoneClickListener;

    public PhoneListAdapter(Context context, OnPhoneClickListener onPhoneClickListener)
    {
        mLayoutInflater = LayoutInflater.from(context);
        this.mPhoneList = null;
        this.onPhoneClickListener = onPhoneClickListener;
    }



    @NonNull
    @Override
    public PhoneViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rootView = mLayoutInflater.inflate(R.layout.activity_recycler_view_item, parent, false);
        return new PhoneViewHolder(rootView, onPhoneClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull PhoneViewHolder holder, int position) {
        boolean isSelected = false;
        holder.bindToPhoneViewHolder(position, isSelected);
    }

    @Override
    public int getItemCount() {
        if(mPhoneList != null){
            return mPhoneList.size();
        }
        return 0;
    }

    public void setPhoneList(List<Phone> phoneList){
        this.mPhoneList = phoneList;
        notifyDataSetChanged();
    }

    public class PhoneViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView phoneTextView;
        private TextView modelTextView;

        private OnPhoneClickListener onPhoneClickListener;

        public PhoneViewHolder(@NonNull View itemView, OnPhoneClickListener onPhoneClickListener) {
            super(itemView);
            phoneTextView = itemView.findViewById(R.id.textview);
            modelTextView = itemView.findViewById(R.id.model_textView);

            this.onPhoneClickListener = onPhoneClickListener;

            itemView.setOnClickListener(this);
        }

        public void bindToPhoneViewHolder(int position, boolean isSelected){
            phoneTextView.setText(mPhoneList.get(position).getFie_mOEM());
            modelTextView.setText(mPhoneList.get(position).getFie_mModel());
            itemView.setActivated(isSelected);
        }

        @Override
        public void onClick(View v) {
            int adapterPosition = getAdapterPosition();
            onPhoneClickListener.onPhoneClick(mPhoneList.get(adapterPosition));

        }
    }

    public interface OnPhoneClickListener {
        void onPhoneClick(Phone phone);
    }
}