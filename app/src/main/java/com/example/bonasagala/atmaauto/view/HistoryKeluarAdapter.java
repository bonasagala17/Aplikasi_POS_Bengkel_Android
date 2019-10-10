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
import com.example.bonasagala.atmaauto.model.History_Keluar;

import java.util.ArrayList;
import java.util.List;

public class HistoryKeluarAdapter extends RecyclerView.Adapter<HistoryKeluarAdapter.HistoryKeluarViewHolder>{

    List<History_Keluar> history_keluars, history_keluarsFilter;
    private RecyclerViewHistoryKeluarClickListener mListenerHistoryKeluar;
    CustomFilterHistoryKeluar filter;

    public HistoryKeluarAdapter(List<History_Keluar> history_keluars, RecyclerViewHistoryKeluarClickListener listenerHistoryKeluar) {
        this.history_keluars = history_keluars;
        this.history_keluarsFilter = history_keluars;
        this.mListenerHistoryKeluar = listenerHistoryKeluar;
    }

    public HistoryKeluarViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_history_keluar,parent,false);
        return new HistoryKeluarViewHolder(view, mListenerHistoryKeluar);
    }

    public void onBindViewHolder(final HistoryKeluarViewHolder holder, int position) {
        holder.mNamaSparepart.setText(history_keluars.get(position).getNama_sparepart());
        holder.mTanggalTransaksi.setText(history_keluars.get(position).getTanggal_transaksi());

        RequestOptions requestOptions = new RequestOptions();
        requestOptions.skipMemoryCache(true);
        requestOptions.diskCacheStrategy(DiskCacheStrategy.NONE);
        requestOptions.placeholder(R.drawable.ic_create);
        requestOptions.error(R.drawable.ic_create);

    }

    public int getItemCount() {
        return history_keluars.size();
    }

    public Filter getFilter() {
        if (filter==null) {
            filter=new CustomFilterHistoryKeluar((ArrayList<History_Keluar>) history_keluarsFilter,this);

        }
        return filter;
    }

    public class HistoryKeluarViewHolder extends RecyclerView.ViewHolder {
        private TextView mNamaSparepart, mTanggalTransaksi;
        private RecyclerViewHistoryKeluarClickListener mListener;
        private RelativeLayout mRowContainer;

        public HistoryKeluarViewHolder(View itemView, RecyclerViewHistoryKeluarClickListener listener) {
            super(itemView);
            mNamaSparepart = itemView.findViewById(R.id.nama_sparepart);
            mTanggalTransaksi = itemView.findViewById(R.id.tanggal_pembelian);
            mRowContainer = itemView.findViewById(R.id.row_container);

            mListener = listener;
        }
    }

    public interface RecyclerViewHistoryKeluarClickListener {
        void onRowClick(View view, int position);
    }
}
