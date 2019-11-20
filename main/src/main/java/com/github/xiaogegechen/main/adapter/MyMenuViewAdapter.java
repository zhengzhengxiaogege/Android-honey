package com.github.xiaogegechen.main.adapter;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.xiaogegechen.library.MenuView;
import com.github.xiaogegechen.main.R;
import com.github.xiaogegechen.main.event.NotifyMenuClickEvent;
import com.github.xiaogegechen.main.model.MenuItem;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

public class MyMenuViewAdapter extends MenuView.Adapter {

    private List<MenuItem> mDataList;
    private SparseArray<View> mViewMap;

    private Context mContext;

    // 目前被选中的位置
    private int mCurrentSelectedPosition = -1;
    // 上一个被选中的位置
    private int mLastSelectedPosition = -1;

    public MyMenuViewAdapter(List<MenuItem> list, Context context) {
        mContext = context;
        mDataList = list;
        mViewMap = new SparseArray<>(list.size());
    }

    @Override
    public View getView(int position, ViewGroup parent) {
        final MenuItem item = mDataList.get(position);
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.main_activity_menu_item, parent, false);
        // 配置子项的显示
        final ImageView iconImageView = view.findViewById(R.id.main_activity_menu_item_image);
        final TextView descriptionTextView = view.findViewById(R.id.main_activity_menu_item_text);
        final View splitView = view.findViewById(R.id.main_activity_menu_item_split);
        final int iconId = item.getIconId();
        boolean isCloseItem = true;
        // 有的子项没有图标，关闭按钮就没有图标
        if(iconId != 0){
            iconImageView.setImageResource(item.getIconId());
            isCloseItem = false;
        }
        iconImageView.setBackgroundColor(mContext.getResources().getColor(item.getNormalColor()));
        descriptionTextView.setText(item.getDescription());
        descriptionTextView.setTextColor(mContext.getResources().getColor(item.getNormalColor()));
        // 点击事件
        final boolean finalIsCloseItem = isCloseItem;
        iconImageView.setOnClickListener(v -> {
            // 关闭按钮只需要响应点击，不需要改变样式
            if(!finalIsCloseItem){
                // 更新两个position
                if(position != mCurrentSelectedPosition){
                    mLastSelectedPosition = mCurrentSelectedPosition;
                }
                mCurrentSelectedPosition = position;
                v.setBackgroundColor(mContext.getResources().getColor(item.getSelectedColor()));
                descriptionTextView.setTextColor(mContext.getResources().getColor(item.getSelectedColor()));
                splitView.setBackgroundColor(mContext.getResources().getColor(item.getSelectedColor()));
                // 还原上一个view
                if(mLastSelectedPosition != -1){
                    View lastSelectedView = mViewMap.get(mLastSelectedPosition);
                    if(lastSelectedView != null){
                        lastSelectedView.findViewById(R.id.main_activity_menu_item_image)
                                .setBackgroundColor(mContext.getResources().getColor(item.getNormalColor()));
                        ((TextView) lastSelectedView.findViewById(R.id.main_activity_menu_item_text))
                                .setTextColor(mContext.getResources().getColor(item.getNormalColor()));
                        lastSelectedView.findViewById(R.id.main_activity_menu_item_split)
                                .setBackgroundColor(mContext.getResources().getColor(item.getNormalColor()));
                    }
                }
                EventBus.getDefault().post(new NotifyMenuClickEvent(mCurrentSelectedPosition, mLastSelectedPosition));
            }else{
                // 关闭按钮比较特殊，它并不影响 mCurrentSelectedPosition，mLastSelectedPosition
                EventBus.getDefault().post(new NotifyMenuClickEvent(position, -1));
            }
        });
        mViewMap.put(position, view);
        return view;
    }

    @Override
    public int getCount() {
        return mDataList.size();
    }

    @Override
    public void makeViewSelected(int position) {
        super.makeViewSelected(position);
        if(mCurrentSelectedPosition == position){
            // 不需要设置
            return;
        }
        // 更新两个位置
        mLastSelectedPosition = mCurrentSelectedPosition;
        mCurrentSelectedPosition = position;
        final View view = mViewMap.get(position);
        final MenuItem item = mDataList.get(position);
        if (view != null) {
            view.findViewById(R.id.main_activity_menu_item_image).setBackgroundColor(item.getSelectedColor());
            ((TextView)view.findViewById(R.id.main_activity_menu_item_text)).setTextColor(item.getSelectedColor());
        }
    }
}
