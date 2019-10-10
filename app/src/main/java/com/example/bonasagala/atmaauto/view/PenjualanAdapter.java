package com.example.bonasagala.atmaauto.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
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
import com.example.bonasagala.atmaauto.model.Penjualan;

import java.util.ArrayList;
import java.util.List;

public class PenjualanAdapter extends RecyclerView.Adapter<PenjualanAdapter.PenjualanSparepartViewHolder> implements Filterable{

    List<Penjualan> penjualan_s, penjualan_sparepartsFilter;
    private Context context;
    private RecyclerViewPenjualanSparepartClickListener mListenerPenjualanSparepart;
    CustomFilterPenjualan filterPenjualanSparepart;

    public PenjualanAdapter(List<Penjualan> penjualan_s, RecyclerViewPenjualanSparepartClickListener listener) {
        this.penjualan_s = penjualan_s;
        this.penjualan_sparepartsFilter = penjualan_s;
        this.mListenerPenjualanSparepart = listener;
    }

    @NonNull
    @Override
    public PenjualanSparepartViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_penjualan_sparepart, parent, false);
        return new PenjualanSparepartViewHolder(view, mListenerPenjualanSparepart);
    }

    @SuppressLint("CheckResult")
    @Override
    public void onBindViewHolder(final PenjualanSparepartViewHolder holder, int position) {
        holder.mNoTransaksi.setText(penjualan_s.get(position).getNo_transaksi());
        holder.mStatusPembayaran.setText(penjualan_s.get(position).getStatus_pembayaran());

        RequestOptions requestOptions = new RequestOptions();
        requestOptions.skipMemoryCache(true);
        requestOptions.diskCacheStrategy(DiskCacheStrategy.NONE);
        requestOptions.placeholder(R.drawable.ic_create);
        requestOptions.error(R.drawable.ic_create);
    }

    @Override
    public int getItemCount() {
        return penjualan_s.size();
    }

    public Filter getFilter() {
        if (filterPenjualanSparepart==null) {
            filterPenjualanSparepart=new CustomFilterPenjualan((ArrayList<Penjualan>) penjualan_sparepartsFilter,this);

        }
        return filterPenjualanSparepart;
    }

    public class PenjualanSparepartViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private RecyclerViewPenjualanSparepartClickListener mListenerPenjualan;
        private TextView mNoTransaksi, mStatusPembayaran;
        private RelativeLayout mRowContainer;

        public PenjualanSparepartViewHolder(View itemView, RecyclerViewPenjualanSparepartClickListener listener) {
            super(itemView);
            mNoTransaksi = itemView.findViewById(R.id.no_transaksi);
            mStatusPembayaran = itemView.findViewById(R.id.status_pembayaran);

            mRowContainer = itemView.findViewById(R.id.row_container);
            mListenerPenjualan = listener;
            mRowContainer.setOnClickListener(this);
        }

        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.row_container:
                    mListenerPenjualan.onRowClick(mRowContainer, getAdapterPosition());
                    break;
                default:
                    break;
            }
        }
    }


    public interface RecyclerViewPenjualanSparepartClickListener {
        void onRowClick(View view, int position);
    }
}
