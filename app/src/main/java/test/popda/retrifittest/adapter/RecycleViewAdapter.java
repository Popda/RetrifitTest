package test.popda.retrifittest.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import test.popda.retrifittest.R;
import test.popda.retrifittest.model.WeatherDay;


public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.MeteoViewHolder> {

    private List<WeatherDay> items;
    private Context mContext ;

    public RecycleViewAdapter(Context mContext, List<WeatherDay> items){

        this.mContext = mContext;
        this.items = items;
    }

    @NonNull
    @Override
    public MeteoViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cardview_for_forecast, viewGroup, false);
        return new MeteoViewHolder(v);
    }

    @Override
    public void onBindViewHolder( MeteoViewHolder meteoViewHolder, int position) {
        meteoViewHolder.tvDayOfWeek.setText(items.get(position).getDtTxt());
        meteoViewHolder.tvTempWithDegree.setText(items.get(position).getTempWithDegree());
        Glide.with(mContext).load(items.get(position).getIconUrl()).into(meteoViewHolder.meteoIcon);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class MeteoViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView tvTempWithDegree;
        TextView tvDayOfWeek;
        ImageView meteoIcon;

        MeteoViewHolder(View itemView) {
            super(itemView);
            cv = itemView.findViewById(R.id.cv);
            tvTempWithDegree = itemView.findViewById(R.id.tvTempWithDegree);
            tvDayOfWeek = itemView.findViewById(R.id.tvDayOfWeek);
            meteoIcon = itemView.findViewById(R.id.meteo_icon);
        }
    }
}
