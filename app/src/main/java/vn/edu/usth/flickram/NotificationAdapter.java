package vn.edu.usth.flickram;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;


public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.CustomViewHolder> {
    private final List<String> mNotificationList;
    private final Activity mActivity;

    public NotificationAdapter(Activity activity, List<String> list){
        mActivity = activity;
        mNotificationList = list;
    }

    static class CustomViewHolder extends RecyclerView.ViewHolder{
        final TextView notification_content;
        CustomViewHolder (View itemView){
            super(itemView);
            this.notification_content = (TextView) itemView.findViewById(R.id.notification_content);
        }
        
        public TextView getTextView(){
            return notification_content;
        }
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.notification_item, parent, false);
        CustomViewHolder holder = new NotificationAdapter.CustomViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {
        holder.getTextView().setText(mNotificationList.get(position));
    }

    @Override
    public int getItemCount(){
        return mNotificationList.size();
    }


}
