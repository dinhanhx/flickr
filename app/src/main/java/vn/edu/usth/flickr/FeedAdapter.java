package vn.edu.usth.flickr;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


public class FeedAdapter extends RecyclerView.Adapter<FeedAdapter.FeedViewHolder> {
    private final List<List<String>> mPostList;
    private final Activity mActivity;
    private Context mContext;

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        mContext = recyclerView.getContext();
    }

    public FeedAdapter(Activity activity, List<List<String>> postlist){
        mActivity = activity;
        mPostList = postlist;
    }

    static class FeedViewHolder extends RecyclerView.ViewHolder{
        final RecyclerView recyclerView;
        FeedViewHolder (View itemView){
            super(itemView);
            this.recyclerView = (RecyclerView) itemView.findViewById(R.id.recycler_view_post);
        }

    }

    @Override
    public FeedViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.feed_item, parent, false);
        return new FeedViewHolder(view);
    }

    @Override
    public void onBindViewHolder(FeedViewHolder holder, int position) {
//        Context mContext= mContext.getApplicationContext();

        LinearLayoutManager myLayout = new LinearLayoutManager(mContext);
        myLayout.setOrientation(LinearLayoutManager.VERTICAL);

        holder.recyclerView.setLayoutManager(myLayout);
        holder.recyclerView.setHasFixedSize(false);
        PostAdapter adapter = new PostAdapter(mActivity, mPostList.get(position));
        holder.recyclerView.setAdapter(adapter);
    }

    @Override
    public int getItemCount() {
        return mPostList.size();
    }
}
