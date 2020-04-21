package com.example.myapplication.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.model.User;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    private List<User> mListUsers;
    private Context mContext;
    private OnItemClickListener onItemClickListener;

    private OnItemLongClickListener longClickListener;

    public void setLongClickListener(OnItemLongClickListener longClickListener) {
        this.longClickListener = longClickListener;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public Adapter(List mListUsers, Context mContext) {
        this.mListUsers = mListUsers;
        this.mContext = mContext;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user, parent, false);

        return new ViewHolder(view, onItemClickListener, longClickListener);
    }



    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        User user = mListUsers.get(position);
        holder.firstName.setText(user.getFirstName());
        holder.lastName.setText(user.getLastName());
        holder.email.setText(user.getEmail());

    }

    @Override
    public int getItemCount() {
        return mListUsers.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        TextView firstName, lastName, email;
        OnItemClickListener onItemClickListener;
        OnItemLongClickListener onItemLongClickListener;

        public ViewHolder(@NonNull View itemView, OnItemClickListener onItemClickListener, OnItemLongClickListener longClickListener) {
            super(itemView);
            this.onItemClickListener = onItemClickListener;
            this.onItemLongClickListener = longClickListener;
            firstName = itemView.findViewById(R.id.first);
            lastName = itemView.findViewById(R.id.last);
            email = itemView.findViewById(R.id.txtemail);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);

        }


        @Override
        public void onClick(View view) {
            if(onItemClickListener!= null)
            onItemClickListener.viewOnclick(view, getAdapterPosition());
        }

        @Override
        public boolean onLongClick(View view) {
            if (onItemLongClickListener!=null)
            onItemLongClickListener.viewLongClick(view, getAdapterPosition());
            return true;
        }
    }

    public interface OnItemClickListener {
        void viewOnclick(View v, int position);

    }

    public interface OnItemLongClickListener {
        void viewLongClick(View v, int position);
    }
}
