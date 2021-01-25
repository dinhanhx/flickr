package vn.edu.usth.flickr;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;


public class FeedAdapter extends RecyclerView.Adapter<FeedAdapter.FeedViewHolder> {
    private ArrayList<String> mUrl = new ArrayList<String>();
    private ArrayList<String> mUsername = new ArrayList<String>();
    private ArrayList<String> mAvatar = new ArrayList<String>();
    private ArrayList<String> mTitle = new ArrayList<String>();
    private ArrayList<String> mLike = new ArrayList<String>();
    private final Activity mActivity;

    public FeedAdapter(Activity activity,
                       ArrayList<String> Url,
                       ArrayList<String> Username,
                       ArrayList<String> Avatar,
                       ArrayList<String> Title,
                       ArrayList<String> Like){
        mActivity = activity;
        mUrl = Url;
        mUsername = Username;
        mAvatar = Avatar;
        mTitle = Title;
        mLike = Like;
    }

    static class FeedViewHolder extends RecyclerView.ViewHolder{
        private ImageView image;
        private TextView username;
        private CircleImageView avatar;
        private TextView title;
        private TextView likes;

        FeedViewHolder (View itemView){
            super(itemView);
            this.image = (ImageView) itemView.findViewById(R.id.image_feed);
            this.username = (TextView) itemView.findViewById(R.id.username);
            this.avatar = (CircleImageView) itemView.findViewById(R.id.profile_image);
            this.title = (TextView) itemView.findViewById(R.id.title);
            this.likes = (TextView) itemView.findViewById(R.id.number_likes);
        }

    }

    @Override
    public FeedViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.feed_item, parent, false);
        return new FeedViewHolder(view);
    }

    @Override
    public void onBindViewHolder(FeedViewHolder holder, int position) {
        int w = holder.image.getMeasuredWidth();
        Log.i("fuck this shit", mUsername.get(position));
//        Context mContext= mContext.getApplicationContext();
        Glide
                .with(mActivity)
                .load(mUrl.get(position))
                .override(w, w)
                .centerCrop()
                .into(holder.image);

        holder.username.setText(mUsername.get(position));

        holder.title.setText(mTitle.get(position));

        Glide
                .with(mActivity)
                .load(mAvatar.get(position))
                .into(holder.avatar);

        holder.likes.setText(mLike.get(position));
    }

    @Override
    public int getItemCount(){
        return mUrl.size();
    }
}
