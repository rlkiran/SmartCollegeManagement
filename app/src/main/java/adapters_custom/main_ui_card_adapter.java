package adapters_custom;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.my_project003.Attendance;
import com.example.my_project003.Circulars_activity;
import com.example.my_project003.InternalMarks;
import com.example.my_project003.Library_activity;
import com.example.my_project003.Queries_and_Grievances;
import com.example.my_project003.R;
import com.example.my_project003.bus_tracking;
import com.example.my_project003.college_website;
import com.example.my_project003.second_activity;
import com.example.my_project003.timeTables;

import java.util.List;

import cards.main_ui_card;
import others.hostel_admission;

public class main_ui_card_adapter extends RecyclerView.Adapter<main_ui_card_adapter.MuViewHolder> {

    private Context mContext;
    private List<main_ui_card> mdata;

    public main_ui_card_adapter(Context mContext, List<main_ui_card> mdata) {
        this.mContext = mContext;
        this.mdata = mdata;
    }

    @NonNull
    @Override
    public MuViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view;
        LayoutInflater mInflater = LayoutInflater.from(mContext);
        view = mInflater.inflate(R.layout.main_ui_cards,viewGroup,false);

        return new MuViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MuViewHolder muViewHolder, @SuppressLint("RecyclerView") final int position) {
        muViewHolder.tv_title.setText(mdata.get(position).getTitle());
        muViewHolder.iv_thumbnail.setImageResource(mdata.get(position).getThumbnail());

        muViewHolder.cv_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                switch (position) {
                    case 0:
                    {  Intent main_second = new Intent(mContext, Circulars_activity.class);
                        mContext.startActivity(main_second);
                        break;
                    }
                    case 1:
                    {  Intent main_second = new Intent(mContext, timeTables.class);
                        mContext.startActivity(main_second);
                        break;
                    }
                    case 2:
                    {  Intent main_second = new Intent(mContext, InternalMarks.class);
                        mContext.startActivity(main_second);
                        break;
                    }
                    case 3:
                    {  Intent main_second = new Intent(mContext, Attendance.class);
                        mContext.startActivity(main_second);
                        break;
                    }
                    case 4:
                    {  Intent main_second = new Intent(mContext, Library_activity.class);
                        mContext.startActivity(main_second);
                        break;
                    }
                    case 5:
                    {  Intent main_second = new Intent(mContext, bus_tracking.class);
                        mContext.startActivity(main_second);
                        break;
                    }
                    case 6:
                    {  Intent main_second = new Intent(mContext, college_website.class);
                        mContext.startActivity(main_second);
                        break;
                    }
                    case 7:
                    {  Intent main_second = new Intent(mContext, hostel_admission.class);
                        mContext.startActivity(main_second);
                        break;
                    }
                    case 8:
                    {  Intent main_second = new Intent(mContext, Queries_and_Grievances.class);
                        mContext.startActivity(main_second);
                        break;
                    }

                    default:
                    {
                        Intent main_second = new Intent(mContext, second_activity.class);
                        main_second.putExtra("Title",mdata.get(position).getTitle());
                        main_second.putExtra("Thumbnail",mdata.get(position).getThumbnail());
                        main_second.putExtra("DescData","No DataSets available");
                        mContext.startActivity(main_second);
                    }

                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return mdata.size();
    }

    static class MuViewHolder extends RecyclerView.ViewHolder {

        TextView tv_title;
        ImageView iv_thumbnail;
        CardView cv_card;

        MuViewHolder(View itemView) {
            super(itemView);

            tv_title = itemView.findViewById(R.id.mTextId);
            iv_thumbnail = itemView.findViewById(R.id.mImageID);
            cv_card = itemView.findViewById(R.id.cardView_id);
        }
    }
}
