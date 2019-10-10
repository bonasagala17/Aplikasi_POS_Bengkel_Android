package com.example.bonasagala.atmaauto.view;

import android.annotation.SuppressLint;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.bonasagala.atmaauto.R;
import com.example.bonasagala.atmaauto.model.Konsumen;

import java.util.ArrayList;
import java.util.List;

public class KonsumenAdapter extends RecyclerView.Adapter<KonsumenAdapter.KonsumenViewHolder> implements Filterable{

    List<Konsumen> konsumens, konsumensFilter;
    private RecyclerViewKonsumenClickListener mListenerKonsumen;
    CustomFilterKonsumen filter;

    public KonsumenAdapter(List<Konsumen> konsumens, RecyclerViewKonsumenClickListener listenerKonsumen) {
        this.konsumens = konsumens;
        this.konsumensFilter = konsumens;
        this.mListenerKonsumen = listenerKonsumen;
    }


    public KonsumenViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_konsumen,parent,false);
        return new KonsumenViewHolder(view, mListenerKonsumen);
    }

    public void onBindViewHolder(final KonsumenViewHolder holder, int position) {
        holder.mNamaKonsumen.setText(konsumens.get(position).getNama_konsumen());
        holder.mAlamatKonsumen.setText(konsumens.get(position).getAlamat_konsumen());

        RequestOptions requestOptions = new RequestOptions();
        requestOptions.skipMemoryCache(true);
        requestOptions.diskCacheStrategy(DiskCacheStrategy.NONE);
        requestOptions.placeholder(R.drawable.ic_create);
        requestOptions.error(R.drawable.ic_create);

    }

    public int getItemCount() {
        return konsumens.size();
    }

    public Filter getFilter() {
        if (filter==null) {
            filter=new CustomFilterKonsumen((ArrayList<Konsumen>) konsumensFilter,this);

        }
        return filter;
    }


    public class KonsumenViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView mNamaKonsumen, mTelpKonsumen, mAlamatKonsumen;
        private RecyclerViewKonsumenClickListener mListener;
        private RelativeLayout mRowContainer;

        public KonsumenViewHolder(View itemView, RecyclerViewKonsumenClickListener listener) {
            super(itemView);
            mNamaKonsumen = itemView.findViewById(R.id.nama_konsumen);
            mTelpKonsumen = itemView.findViewById(R.id.telp_konsumen);
            mAlamatKonsumen = itemView.findViewById(R.id.alamat_konsumen);
            mRowContainer = itemView.findViewById(R.id.row_container);

            mListener = listener;
            mRowContainer.setOnClickListener(this);
        }

        public void onClick (View v){
            switch (v.getId()) {
                case R.id.row_container:
                    mListener.onRowClick(mRowContainer, getAdapterPosition());
                    break;
                default:
                    break;
            }
        }

    }

    public interface RecyclerViewKonsumenClickListener {
        void onRowClick(View view, int position);
    }

}


