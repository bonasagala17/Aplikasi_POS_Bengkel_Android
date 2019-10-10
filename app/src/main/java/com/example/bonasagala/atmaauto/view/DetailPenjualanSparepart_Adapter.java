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
import com.example.bonasagala.atmaauto.model.Detail_PenjualanSparepart;

import java.util.ArrayList;
import java.util.List;

public class DetailPenjualanSparepart_Adapter extends RecyclerView.Adapter<DetailPenjualanSparepart_Adapter.DetailPenjualanSparepartViewHolder> implements Filterable{

    List<Detail_PenjualanSparepart> detail_penjualanSpareparts, detail_penjualanSparepartsFilter;
    private Context context;
    private RecyclerViewDetailPenjualanSparepartClickListener mListenerDetailPenjualanSparepart;
    CustomFilterDetailPenjualanSparepart filterDetailPenjualanSparepart;

    public DetailPenjualanSparepart_Adapter(List<Detail_PenjualanSparepart> detail_penjualanSpareparts, RecyclerViewDetailPenjualanSparepartClickListener listener) {
        this.detail_penjualanSpareparts = detail_penjualanSpareparts;
        this.detail_penjualanSparepartsFilter = detail_penjualanSpareparts;
        this.mListenerDetailPenjualanSparepart = listener;
    }

    @NonNull
    @Override
    public DetailPenjualanSparepart_Adapter.DetailPenjualanSparepartViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_detail_penjualan_sparepart, parent, false);
        return new DetailPenjualanSparepart_Adapter.DetailPenjualanSparepartViewHolder(view, mListenerDetailPenjualanSparepart);
    }

    @SuppressLint("CheckResult")
    @Override
    public void onBindViewHolder(final DetailPenjualanSparepart_Adapter.DetailPenjualanSparepartViewHolder holder, int position) {
        holder.mNama_sparepart.setText(detail_penjualanSpareparts.get(position).getId_sparepart());
        holder.mJumlah.setText(detail_penjualanSpareparts.get(position).getJumlah());

        RequestOptions requestOptions = new RequestOptions();
        requestOptions.skipMemoryCache(true);
        requestOptions.diskCacheStrategy(DiskCacheStrategy.NONE);
        requestOptions.placeholder(R.drawable.ic_create);
        requestOptions.error(R.drawable.ic_create);
    }

    @Override
    public int getItemCount() {
        return detail_penjualanSpareparts.size();
    }

    public Filter getFilter() {
        if (filterDetailPenjualanSparepart==null) {
            filterDetailPenjualanSparepart=new CustomFilterDetailPenjualanSparepart((ArrayList<Detail_PenjualanSparepart>) detail_penjualanSparepartsFilter,this);

        }
        return filterDetailPenjualanSparepart;
    }

    public class DetailPenjualanSparepartViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private RecyclerViewDetailPenjualanSparepartClickListener mListenerDetailPenjualanSparepart;
        private TextView mNama_sparepart, mJumlah;
        private RelativeLayout mRowContainer;

        public DetailPenjualanSparepartViewHolder(View itemView, RecyclerViewDetailPenjualanSparepartClickListener listener) {
            super(itemView);
            mNama_sparepart = itemView.findViewById(R.id.nama_sparepart);
            mJumlah = itemView.findViewById(R.id.jumlah);

            mRowContainer = itemView.findViewById(R.id.row_container);
            mListenerDetailPenjualanSparepart = listener;
            mRowContainer.setOnClickListener(this);
        }

        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.row_container:
                    mListenerDetailPenjualanSparepart.onRowClick(mRowContainer, getAdapterPosition());
                    break;
                default:
                    break;
            }
        }
    }

    public interface RecyclerViewDetailPenjualanSparepartClickListener {
        void onRowClick(View view, int position);
    }
}
