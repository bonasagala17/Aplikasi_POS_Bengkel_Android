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
import com.example.bonasagala.atmaauto.model.Detail_PembelianSparepart;

import java.util.ArrayList;
import java.util.List;

public class DetailPembelianAdapter extends RecyclerView.Adapter<DetailPembelianAdapter.DetailPembelianViewHolder> implements Filterable{

    List<Detail_PembelianSparepart> detail_pembelianSpareparts, detail_pembelianSparepartsFilter;
    private Context context;
    private RecyclerViewDetailPembelianClickListener mListenerDetailPembelian;
    CustomFilterDetailPembelian filterDetailPembelian;

    public DetailPembelianAdapter(List<Detail_PembelianSparepart> detail_pembelianSpareparts, RecyclerViewDetailPembelianClickListener listener) {
        this.detail_pembelianSpareparts = detail_pembelianSpareparts;
        this.detail_pembelianSparepartsFilter = detail_pembelianSpareparts;
        this.mListenerDetailPembelian = listener;
    }

    @NonNull
    @Override
    public DetailPembelianViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_detail_pembelian, parent, false);
        return new DetailPembelianViewHolder(view, mListenerDetailPembelian);
    }

    @SuppressLint("CheckResult")
    @Override
    public void onBindViewHolder(final DetailPembelianViewHolder holder, int position) {
        holder.mJumlah.setText(detail_pembelianSpareparts.get(position).getJumlah());
        holder.mHargaBeli.setText(detail_pembelianSpareparts.get(position).getHargabeli());

        RequestOptions requestOptions = new RequestOptions();
        requestOptions.skipMemoryCache(true);
        requestOptions.diskCacheStrategy(DiskCacheStrategy.NONE);
        requestOptions.placeholder(R.drawable.ic_create);
        requestOptions.error(R.drawable.ic_create);
    }

    @Override
    public int getItemCount() {
        return detail_pembelianSpareparts.size();
    }

    public Filter getFilter() {
        if (filterDetailPembelian==null) {
            filterDetailPembelian=new CustomFilterDetailPembelian((ArrayList<Detail_PembelianSparepart>) detail_pembelianSparepartsFilter,this);

        }
        return filterDetailPembelian;
    }

    public class DetailPembelianViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private RecyclerViewDetailPembelianClickListener mListenerDetailPembelian;
        private TextView mIdDetailPembelian, mHargaBeli, mJumlah, mIdSparepart, mIdPembelian;
        private RelativeLayout mRowContainer;

        public DetailPembelianViewHolder(View itemView, RecyclerViewDetailPembelianClickListener listener) {
            super(itemView);
            mHargaBeli = itemView.findViewById(R.id.hargabeli);
            mJumlah = itemView.findViewById(R.id.jumlah);
            mIdSparepart = itemView.findViewById(R.id.spinnerSparepart);
            mIdPembelian = itemView.findViewById(R.id.spinnerPembelian);

            mRowContainer = itemView.findViewById(R.id.row_container);
            mListenerDetailPembelian = listener;
            mRowContainer.setOnClickListener(this);
        }

        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.row_container:
                    mListenerDetailPembelian.onRowClick(mRowContainer, getAdapterPosition());
                    break;
                default:
                    break;
            }
        }
    }

    public interface RecyclerViewDetailPembelianClickListener {
        void onRowClick(View view, int position);
    }
}
