package com.example.texteditoperationmenu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

class PopupAdapter extends RecyclerView.Adapter<PopupAdapter.PopupViewHolder> {
    private final IPopupClickListener listener;
    List<StringWithIcon> items = new ArrayList<>();
    Context ctx;

    public interface IPopupClickListener {
        void itemClicked(MainActivity.TextEditOperations operation);
    }

    public PopupAdapter(List<StringWithIcon> items, Context ctx, @NonNull IPopupClickListener listener) {
        this.items = items;
        this.ctx = ctx;
        this.listener = listener;
    }


    class PopupViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public PopupViewHolder(@NonNull TextView itemView) {
            super(itemView);
        }

        TextView getTextView() {
            return (TextView) itemView;
        }

        @Override
        public void onClick(View v) {
            listener.itemClicked(items.get(getAdapterPosition()).getOperation());
        }
    }


    @NonNull
    @Override
    public PopupAdapter.PopupViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PopupViewHolder((TextView) LayoutInflater.from(ctx).inflate(R.layout.text_view, parent, false));
    }


    @Override
    public void onBindViewHolder(@NonNull PopupViewHolder holder, int position) {
        TextView view = holder.getTextView();
        view.setText(items.get(position).getText());
        view.setCompoundDrawablesWithIntrinsicBounds(0, items.get(position).getIconId(), 0, 0);
        holder.getTextView().setOnClickListener(holder);

        holder = new PopupViewHolder(view);
    }


    @Override
    public int getItemCount() {
        return items.size();
    }
}
