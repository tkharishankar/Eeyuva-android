package com.eeyuva.screens.home.coverflow;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.eeyuva.R;
import com.eeyuva.screens.home.ResponseItem;
import com.eeyuva.screens.home.loadmore.RoundedTransformation;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hari on 09/09/16.
 */
public class ArticlesAdapter extends RecyclerView.Adapter<ArticlesAdapter.ViewHolder> {
    private List<ResponseItem> mArticlesList = new ArrayList<ResponseItem>();
    private Context mContext;

    public ArticlesAdapter(Context context, List<ResponseItem> responseItem) {
        this.mContext = context;
        this.mArticlesList = responseItem;
    }

    @Override
    public ArticlesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_article_details, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final ResponseItem articles = mArticlesList.get(position);
        try {
            holder.txtTitle.setText(articles.getTitle());
            holder.txtSubDesc.setText(getSubString(articles.getSummary()));
            Picasso.with(mContext).load(articles.getPicpath()).transform(new RoundedTransformation(8, 0)).resize(80, 80).into(holder.imgArticle);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String getSubString(String summary) {
        if (summary.length() > 190)
            return summary.substring(0, 190) + "...";
        return summary;
    }

    @Override
    public int getItemCount() {
        return mArticlesList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView txtTitle;
        public TextView txtSubDesc;
        public ImageView imgArticle;

        public ViewHolder(View v) {
            super(v);
            txtTitle = (TextView) v.findViewById(R.id.txtTitle);
            txtSubDesc = (TextView) v.findViewById(R.id.txtSubDesc);
            imgArticle = (ImageView) v.findViewById(R.id.imgArticle);
        }
    }
}
