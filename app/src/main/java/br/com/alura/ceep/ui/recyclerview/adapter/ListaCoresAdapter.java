package br.com.alura.ceep.ui.recyclerview.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import br.com.alura.ceep.R;
import br.com.alura.ceep.model.Nota;
import br.com.alura.ceep.ui.activity.NotaCores;
import br.com.alura.ceep.ui.recyclerview.adapter.listener.OnItemClickListener;

public class ListaCoresAdapter extends RecyclerView.Adapter<ListaCoresAdapter.CorViewHolder>{

    private final Context context;
    private final List<NotaCores> todasCores;
    private OnItemClickListener onItemClickListener;

    public ListaCoresAdapter(Context context, List<NotaCores> todasCores) {
        this.context = context;
        this.todasCores = todasCores;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public ListaCoresAdapter.CorViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View viewCriada = LayoutInflater.from(parent.getContext()).inflate(R.layout.cor_nota, parent, false);
        return new ListaCoresAdapter.CorViewHolder(viewCriada);
    }

    @Override
    public void onBindViewHolder(@NonNull CorViewHolder holder, int position) {
        NotaCores cor = todasCores.get(position);
        holder.vincula(cor);
    }

    @Override
    public int getItemCount() {
        return todasCores.size();
    }


    public class CorViewHolder extends RecyclerView.ViewHolder {

        private final ImageView palheta_cor;
        private NotaCores cor;
        public CorViewHolder(View view) {
            super(view);
            palheta_cor = view.findViewById(R.id.palheta_cor);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClickListener.onItemClick(cor, getAdapterPosition());
                }
            });
        }

        public void vincula(NotaCores cor) {
            this.cor = cor;
            preencheCampo(cor);
        }

        private void preencheCampo(NotaCores cor) {
            int color = Color.parseColor(cor.getCor());
            ((GradientDrawable)palheta_cor.getBackground()).setColor(color);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(NotaCores cores, int posicao);
    }
}
