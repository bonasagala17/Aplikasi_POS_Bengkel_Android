package com.example.bonasagala.atmaauto.view;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.bonasagala.atmaauto.R;
import com.example.bonasagala.atmaauto.model.History_Masuk;

import java.util.ArrayList;
import java.util.List;

public class HistoryMasukAdapter extends RecyclerView.Adapter<HistoryMasukAdapter.HistoryMasukViewHolder>{

    List<History_Masuk> history_masuks, history_masuksFilter;
    private RecyclerViewHistoryMasukClickListener mListenerHistoryMasuk;
    CustomFilterHistoryMasuk filter;

    public HistoryMasukAdapter(List<History_Masuk> history_masuks, RecyclerViewHistoryMasukClickListener listenerHistoryMasuk) {
        this.history_masuks = history_masuks;
        this.history_masuksFilter = history_masuks;
        this.mListenerHistoryMasuk = listenerHistoryMasuk;
    }


    public HistoryMasukViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_history_masuk,parent,false);
        return new HistoryMasukViewHolder(view, mListenerHistoryMasuk);
    }

    public void onBindViewHolder(final HistoryMasukViewHolder holder, int position) {
        holder.mNamaSparepart.setText(history_masuks.get(position).getNama_sparepart());
        holder.mTanggalPembelian.setText(history_masuks.get(position).getTanggal_pembelian());

        RequestOptions requestOptions = new RequestOptions();
        requestOptions.skipMemoryCache(true);
        requestOptions.diskCacheStrategy(DiskCacheStrategy.NONE);
        requestOptions.placeholder(R.drawable.ic_create);
        requestOptions.error(R.drawable.ic_create);

    }

    public int getItemCount() {
        return history_masuks.size();
    }

    public Filter getFilter() {
        if (filter==null) {
            filter=new CustomFilterHistoryMasuk((ArrayList<History_Masuk>) history_masuksFilter,this);

        }
        return filter;
    }


    public class HistoryMasukViewHolder extends RecyclerView.ViewHolder {
        private TextView mNamaSparepart, mTanggalPembelian;
        private RecyclerViewHistoryMasukClickListener mListener;
        private RelativeLayout mRowContainer;

        public HistoryMasukViewHolder(View itemView, RecyclerViewHistoryMasukClickListener listener) {
            super(itemView);
            mNamaSparepart = itemView.findViewById(R.id.nama_sparepart);
            mTanggalPembelian = itemView.findViewById(R.id.tanggal_pembelian);
            mRowContainer = itemView.findViewById(R.id.row_container);

            mListener = listener;
        }
    }

    public interface RecyclerViewHistoryMasukClickListener {
        void onRowClick(View view, int position);
    }
}
