package com.course.innopolis.mytaskmanager.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.course.innopolis.mytaskmanager.OnListItemCallback;
import com.course.innopolis.mytaskmanager.models.User;
import com.course.innopolis.mytaskmanager.R;

import java.util.List;

/**
 * Created by Cristina on 22.07.2017.
 *
 * Адаптер для вывода списка пользователей User в RecyclerView
 */

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserHolder>{

    private List<User> mUsersList;
    private LayoutInflater mInflater;
    private OnListItemCallback callback;

    public UserAdapter(Context context, List<User> mUsersList, OnListItemCallback callback) {
        this.mUsersList = mUsersList;
        this.mInflater = LayoutInflater.from(context);
        this.callback = callback;
    }

    @Override
    public UserHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.user_item, parent, false);
        UserHolder holder = new UserHolder(view, callback);
        return holder;
    }

    @Override
    public void onBindViewHolder(UserHolder holder, int position) {
        User currentUser = mUsersList.get(position);
        holder.setData(currentUser, position);
    }

    @Override
    public int getItemCount() {
        return mUsersList.size();
    }

    class UserHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView mLogin, mPassword, mStatus;
        int position;
        User current;
        private OnListItemCallback callback;


        public UserHolder(View itemView, OnListItemCallback callback) {
            super(itemView);
            mLogin = (TextView)itemView.findViewById(R.id.login);
            mPassword = (TextView)itemView.findViewById(R.id.password);
            mStatus = (TextView)itemView.findViewById(R.id.status);
            this.callback = callback;
            itemView.setOnClickListener(this);
        }

        public void setData(User current, int position) {
            this.mLogin.setText(current.getLogin());
            this.mPassword.setText(current.getPassword());
            this.mStatus.setText(current.getActive().toString());
            this.position = position;
            this.current = current;
        }

        @Override
        public void onClick(View v) {
            callback.onClick(current);
        }
    }

}
