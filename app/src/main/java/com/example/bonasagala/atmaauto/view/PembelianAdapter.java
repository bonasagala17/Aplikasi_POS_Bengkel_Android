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
import com.example.bonasagala.atmaauto.model.Pembelian_Sparepart;

import java.util.ArrayList;
import java.util.List;

public class PembelianAdapter extends RecyclerView.Adapter<PembelianAdapter.PembelianViewHolder> implements Filterable{

    List<Pembelian_Sparepart> pembelian_spareparts, pembelian_sparepartsFilter;
    private Context context;
    private RecyclerViewPembelianClickListener mListenerPembelian;
    CustomFilterPembelian filterPembelian;

    public PembelianAdapter(List<Pembelian_Sparepart> pembelian_spareparts, RecyclerViewPembelianClickListener listener) {
        this.pembelian_spareparts = pembelian_spareparts;
        this.pembelian_sparepartsFilter = pembelian_spareparts;
        this.mListenerPembelian = listener;
    }

    @NonNull
    @Override
    public PembelianViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_pembelian, parent, false);
        return new PembelianViewHolder(view, mListenerPembelian);
    }

    @SuppressLint("CheckResult")
    @Override
    public void onBindViewHolder(final PembelianViewHolder holder, int position) {
        holder.mTanggalPembelian.setText(pembelian_spareparts.get(position).getTanggal_pembelian());
        holder.mStatus.setText(pembelian_spareparts.get(position).getStatus());

        RequestOptions requestOptions = new RequestOptions();
        requestOptions.skipMemoryCache(true);
        requestOptions.diskCacheStrategy(DiskCacheStrategy.NONE);
        requestOptions.placeholder(R.drawable.ic_create);
        requestOptions.error(R.drawable.ic_create);
    }

    @Override
    public int getItemCount() {
        return pembelian_spareparts.size();
    }

    public Filter getFilter() {
        if (filterPembelian==null) {
            filterPembelian=new CustomFilterPembelian((ArrayList<Pembelian_Sparepart>) pembelian_sparepartsFilter, this);
        }
        return filterPembelian;
    }


    public class PembelianViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private RecyclerViewPembelianClickListener mListenerPembelian;
        private TextView mIdPembelian, mIdSupplier, mTanggalPembelian, mTotalPembayaran, mStatus;
        private RelativeLayout mRowContainer;

        public PembelianViewHolder(View itemView, RecyclerViewPembelianClickListener listener) {
            super(itemView);
            mIdSupplier = itemView.findViewById(R.id.spinnerSupplier);
            mTanggalPembelian = itemView.findViewById(R.id.tanggal_pembelian);
            mTotalPembayaran = itemView.findViewById(R.id.total_pembayaran);
            mStatus = itemView.findViewById(R.id.status);

            mRowContainer = itemView.findViewById(R.id.row_container);
            mListenerPembelian = listener;
            mRowContainer.setOnClickListener(this);
        }

        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.row_container:
                    mListenerPembelian.onRowClick(mRowContainer, getAdapterPosition());
                    break;
                default:
                    break;
            }
        }

    }

    public interface RecyclerViewPembelianClickListener {
        void onRowClick(View view, int position);
    }
}
