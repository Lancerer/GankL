package com.example.lancer.gankl.adapter;

import android.content.Context;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import java.util.List;

/**
 * author: Lancer
 * date：2018/8/15
 * des:
 * email:tyk790406977@126.com
 */

public abstract class CommonAdapter <T,H,F> extends RecyclerView.Adapter<CommonHolder> {
    private Context mContext;
    private List<T> mBodyDatas;
   private H mHeaderData;//H 头部数据源的泛型
    private F mFooterData;//T 尾部数据源的泛型
    private int mBodyLayoutId,mHeaderLayoutId,mFooterLayoutId;
    private LayoutInflater mInflater;

    //item类型分三种
    public static final int ITEM_TYPE_HEADER = 0;//头
    public static final int ITEM_TYPE_CONTENT = 1;//主体
    public static final int ITEM_TYPE_BOTTOM = 2;//尾

    private int mHeaderCount;//头部View个数
    private int mBottomCount;//底部View个数


    private OnItemClickListener onItemClickListener;//item监听

    /**
     * 不添加头或尾的时候
     * @param mContext
     * @param mBodyDatas
     * @param bodyLayoutId
     */

    public CommonAdapter(Context mContext, List<T> mBodyDatas, int bodyLayoutId) {
        this(mContext,bodyLayoutId,mBodyDatas,0,null,0,null);
    }


    /**
     * 添加头或尾的时候
     * @param context
     * @param bodyLayoutId 主体布局文件
     * @param bodyDatas 主体数据源
     * @param headerLayoutId 头部布局
     * @param headerData 头部数据源
     * @param footerLayoutId 尾部布局
     * @param footerData 尾部数据源
     */
    public CommonAdapter(Context context, int bodyLayoutId, List<T> bodyDatas,int headerLayoutId,H headerData,int footerLayoutId,F footerData) {
        this.mContext = context;
        this.mBodyDatas = bodyDatas;
        this.mHeaderData=headerData;
        this.mFooterData=footerData;
        this.mBodyLayoutId = bodyLayoutId;
        this.mHeaderLayoutId = headerLayoutId;
        this.mFooterLayoutId = footerLayoutId;
        mInflater = LayoutInflater.from(mContext);
        //判断是否有头
        if (headerLayoutId==0){
            mHeaderCount=0;
        }else {
            mHeaderCount=1;
        }
        //判断是否有尾
        if (footerLayoutId==0){
            mBottomCount=0;
        }else {
            mBottomCount=1;
        }
    }

    @Override
    public CommonHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //根据item的类型加载不同布局文件
        if (viewType ==ITEM_TYPE_HEADER) {
            return new CommonHolder(mInflater.inflate(mHeaderLayoutId, parent, false));
        } else if (viewType == ITEM_TYPE_CONTENT) {
            return new CommonHolder(mInflater.inflate(mBodyLayoutId, parent, false));
        } else if (viewType == ITEM_TYPE_BOTTOM) {
            return new CommonHolder(mInflater.inflate(mFooterLayoutId, parent, false));
        }
        return null;

    }


    @Override
    public void onBindViewHolder(final CommonHolder holder, int position) {
        //根据item的类型实现与不同数据源进行衔接
        if (isHeaderView(position)){
            convertHeader(holder,mHeaderData,position);
        }else if (isBottomView(position)){
            convertFooter(holder,mFooterData,position);
        }else {
            convertBody( holder, mBodyDatas.get(position - mHeaderCount), position);
        }
        //为子项布局设置监听事件
        if (onItemClickListener != null) {
            //设置背景
            //holder.itemView.setBackgroundResource(R.drawable.recycler_bg);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //注意，这里的position不要用上面参数中的position，会出现位置错乱\
                    onItemClickListener.OnItemClickListener(holder.itemView, holder.getLayoutPosition());
                }
            });
        }

    }


    /**
     * 将主体数据源添加到主体上-----必须要重写
     * @param holder
     * @param data
     * @param position
     */
    public abstract void convertBody(CommonHolder holder, T data, int position);

    /**
     * 将头部数据源添加到头部-----recyclerView添加头部时重写
     * @param holder
     * @param data
     * @param position
     */
    public void convertHeader(CommonHolder holder, H data, int position){

    }

    /**
     * 将尾部数据源添加到尾部------recyclerView添加尾部时重写
     * @param holder
     * @param data
     * @param position
     */
    public void convertFooter(CommonHolder holder, F data, int position){

    }

    /**
     * 返回Item的个数要考虑添加头和尾的个数
     * @return
     */
    @Override
    public int getItemCount() {
        return mBodyDatas.size()+mHeaderCount+mBottomCount;
    }

    /**
     * 返回主体item的个数
     * @return
     */
    public int getContentItemCount(){
        return mBodyDatas.size();
    }

    /**
     * 判断当前item是否是HeadView
     * @param position
     * @return
     */
    public boolean isHeaderView(int position) {
        return mHeaderCount != 0 && position < mHeaderCount;
    }

    /**
     * 判断当前item是否是FootView
     * @param position
     * @return
     */
    public boolean isBottomView(int position) {
        return mBottomCount != 0 && position >= (mHeaderCount + getContentItemCount());
    }

    /**
     * 判断position对应的Item的类型
     * @param position
     * @return
     */
    @Override
    public int getItemViewType(int position) {
        int dataItemCount = getContentItemCount();
        if (mHeaderCount != 0 && position < mHeaderCount) {
//头部View
            return ITEM_TYPE_HEADER;
        } else if (mBottomCount != 0 && position >= (mHeaderCount + dataItemCount)) {
//底部View
            return ITEM_TYPE_BOTTOM;
        } else {
//内容View
            return ITEM_TYPE_CONTENT;
        }
    }

    /**
     * 为recycler View设置item的点击事件
     * @param onItemClickListener
     */
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    //点击事件监听接口
    public interface OnItemClickListener {
        void OnItemClickListener(View view, int position);
    }

}
