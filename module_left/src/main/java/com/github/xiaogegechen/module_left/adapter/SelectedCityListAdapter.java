package com.github.xiaogegechen.module_left.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.github.xiaogegechen.common.util.ImageParam;
import com.github.xiaogegechen.common.util.ImageUtil;
import com.github.xiaogegechen.module_left.Constants;
import com.github.xiaogegechen.module_left.R;
import com.github.xiaogegechen.module_left.model.SelectedCity;

import java.util.List;

/**
 * {@link com.github.xiaogegechen.module_left.view.impl.ManageCityActivity}中recyclerView的adapter
 */
public class SelectedCityListAdapter extends RecyclerView.Adapter<SelectedCityListAdapter.ViewHolder> {

    private List<SelectedCity> mSelectedCityList;
    private Context mContext;

    public SelectedCityListAdapter(List<SelectedCity> selectedCityList, Context context) {
        mSelectedCityList = selectedCityList;
        mContext = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.module_left_activity_manage_city_item, parent, false);
        final ViewHolder holder = new ViewHolder(view);
        holder.mDeleteImageView.setOnClickListener(v -> {
            // TODO 点击事件，移除这个城市
            final int position = holder.getAdapterPosition();
            SelectedCity selectedCity = mSelectedCityList.get(position);
        });
        holder.itemView.setOnClickListener(v -> {
            // TODO 点击事件，跳转到weatherActivity界面。
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        SelectedCity selectedCity = mSelectedCityList.get(position);
        // 城市名
        String location = selectedCity.getLocation();
        // 天气数据
        StringBuilder weatherBuilder = new StringBuilder();
        if(selectedCity.getWeatherDescription() == null){
            weatherBuilder.append(Constants.NULL_DATA);
        }else{
            weatherBuilder.append(selectedCity.getWeatherDescription());
        }
        weatherBuilder.append(" ");
        if(selectedCity.getTemp() == null){
            weatherBuilder.append(Constants.NULL_DATA);
        }else{
            weatherBuilder.append(selectedCity.getTemp());
        }
        weatherBuilder.append("°");
        // 填充
        holder.mCityTextView.setText(location);
        holder.mWeatherTextView.setText(weatherBuilder.toString());
        ImageParam param = new ImageParam.Builder()
                .context(mContext)
                .imageView(holder.mWeatherImageView)
                .url(Constants.WEATHER_ICON_URL + "")
                .error(mContext.getResources().getDrawable(R.drawable.module_left_weather_na))
                .placeholder(mContext.getResources().getDrawable(R.drawable.module_left_weather_na))
                .build();
        ImageUtil.INSTANCE.displayImage(param);
    }

    @Override
    public int getItemCount() {
        return mSelectedCityList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{

        ImageView mWeatherImageView;
        ImageView mDeleteImageView;
        TextView mCityTextView;
        TextView mWeatherTextView;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            mWeatherImageView = itemView.findViewById(R.id.module_left_activity_manage_city_item_icon);
            mDeleteImageView = itemView.findViewById(R.id.module_left_activity_manage_city_item_delete);
            mCityTextView = itemView.findViewById(R.id.module_left_activity_manage_city_item_city);
            mWeatherTextView = itemView.findViewById(R.id.module_left_activity_manage_city_item_weather);
        }
    }

}
