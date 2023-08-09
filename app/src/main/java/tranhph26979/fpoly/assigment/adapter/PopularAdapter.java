package tranhph26979.fpoly.assigment.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import tranhph26979.fpoly.assigment.databinding.ItemPopularBinding;
import tranhph26979.fpoly.assigment.listener.EventClickListener;
import tranhph26979.fpoly.assigment.models.Meals;

public class PopularAdapter extends RecyclerView.Adapter<PopularAdapter.MyViewHolder> {
    private List<Meals> list;
    private EventClickListener listener;

    public PopularAdapter(List<Meals> list, EventClickListener listener) {
        this.list = list;
        this.listener = listener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemPopularBinding itemPopularBinding = ItemPopularBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new MyViewHolder(itemPopularBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.setBinding(list.get(position));
        Glide.with(holder.itemView).load(list.get(position).getStrMealThumb()).into(holder.binding.imgPopular);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private ItemPopularBinding binding;

        public MyViewHolder(ItemPopularBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        private void setBinding(Meals meals) {
            binding.setPopular(meals);
            binding.executePendingBindings();
            binding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onPoPularClick(meals);
                }
            });
        }
    }
}
