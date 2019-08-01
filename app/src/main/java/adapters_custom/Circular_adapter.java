package adapters_custom;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.my_project003.R;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.DocumentSnapshot;
import com.squareup.picasso.Picasso;

import cards.Circular_Item;


public  class Circular_adapter extends FirestoreRecyclerAdapter<Circular_Item, Circular_adapter.Circular_Holder> {
    private OnItemClickListener listener;
    public Circular_adapter(@NonNull FirestoreRecyclerOptions<Circular_Item> options) {
        super(options);
    }
    private static final String TAG = "MyActivity";
    @Override
    protected void onBindViewHolder(@NonNull Circular_Holder holder, int position, @NonNull Circular_Item model) {
        String img_url = model.getUrl();
        Picasso.get()
                .load(img_url)
                .fit()
                .into(holder.logoImg);
        Log.d(TAG, "Url is " + img_url);
       // holder.logoImg.setImageResource(R.drawable.aits_logo);
        holder.cr_title.setText(model.getTitle());
        holder.cr_description.setText(model.getDescription());

    }

    @NonNull
    @Override
    public Circular_Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.circular_card_item,viewGroup,false);
        return new Circular_Holder(v);
    }

    class Circular_Holder extends RecyclerView.ViewHolder {

        ImageView logoImg;
        TextView cr_title, cr_description;

        Circular_Holder(@NonNull View itemView)  {
            super(itemView);
            logoImg = itemView.findViewById(R.id.Circular_Image);
            cr_title = itemView.findViewById(R.id.Circular_title);
            cr_description = itemView.findViewById(R.id.Circular_description);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION && listener != null) {
                        listener.onItemClick(getSnapshots().getSnapshot(position), position);
                    }
                }
            });
        }

    }
    public interface OnItemClickListener {
        void onItemClick(DocumentSnapshot documentSnapshot, int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

}
