package com.example.lancer.gankl.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.lancer.gankl.R;
import com.example.lancer.gankl.bean.search.SearchBean;

import java.util.List;

/**
 * author: Lancer
 * date：2018/9/28
 * des:
 * email:tyk790406977@126.com
 */
public class SearchAdapter extends BaseAdapter {
    private List<SearchBean.ResultsBean> mSearchBeanList;
    private Context mContext;

    public SearchAdapter(List<SearchBean.ResultsBean> searchBeanList, Context context) {
        mSearchBeanList = searchBeanList;
        mContext = context;
    }

    @Override
    public int getCount() {
        return mSearchBeanList.size();
    }

    @Override
    public SearchBean.ResultsBean getItem(int position) {
        return mSearchBeanList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {

            convertView = View.inflate(mContext, R.layout.item_search, null);
            viewHolder = new ViewHolder();
            viewHolder.mTextView = convertView.findViewById(R.id.tv_des);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        SearchBean.ResultsBean item = getItem(position);
        viewHolder.mTextView.setText(item.getDesc());
        return convertView;
    }

    private class ViewHolder {
        TextView mTextView;
    }
}
