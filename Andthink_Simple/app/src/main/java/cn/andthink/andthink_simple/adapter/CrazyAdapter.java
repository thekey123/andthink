package cn.andthink.andthink_simple.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import adapter.MyBaseAdapter;
import butterknife.ButterKnife;
import butterknife.InjectView;
import cn.andthink.andthink_simple.R;
import cn.andthink.andthink_simple.modle.Article;

/**
 * Created by thekey123 on 2015/8/27.
 */
public class CrazyAdapter extends MyBaseAdapter {


    private View currentView;

    public CrazyAdapter(Context context, List<? extends Object> data) {
        super(context, data);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder mHolder;
        if (convertView == null || convertView.getTag(R.mipmap.ic_launcher + position) == null) {
            currentView = getCurrentInflater.inflate(R.layout.item_crazy, null);
            mHolder = new ViewHolder(currentView);
            currentView.setTag(R.mipmap.ic_launcher + position);
        } else {
            currentView = convertView;
            mHolder = (ViewHolder) convertView.getTag(R.mipmap.ic_launcher + position);
        }
        Article article = (Article) getCurrentData.get(position);
        mHolder.tvTitle.setText(article.getTitle());
        mHolder.tvContent.setText(article.getContent());
        return currentView;
    }

    static class ViewHolder {
        @InjectView(R.id.tv_type)
        TextView tvType;
        @InjectView(R.id.tv_title)
        TextView tvTitle;
        @InjectView(R.id.image)
        ImageView image;
        @InjectView(R.id.tv_content)
        TextView tvContent;
        @InjectView(R.id.iv_like)
        ImageView ivLike;
        @InjectView(R.id.tv_like_num)
        TextView tvLikeNum;

        ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }
}
