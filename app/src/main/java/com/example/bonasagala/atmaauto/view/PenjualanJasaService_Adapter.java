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
import com.example.bonasagala.atmaauto.model.Penjualan_JasaService;

import java.util.ArrayList;
import java.util.List;

public class PenjualanJasaService_Adapter extends RecyclerView.Adapter<PenjualanJasaService_Adapter.PenjualanJasaServiceViewHolder> implements Filterable{

    List<Penjualan_JasaService> detail_penjualanJasaservices, detail_penjualanJasaservicesFilter;
    private Context context;
    private RecyclerViewPenjualanJasaServiceClickListener mListenerPenjualanJasaservice;
    CustomFilterPenjualanJasaService filterPenjualanJasaservice;

    public PenjualanJasaService_Adapter(List<Penjualan_JasaService> detail_penjualanJasaservices, RecyclerViewPenjualanJasaServiceClickListener listener) {
        this.detail_penjualanJasaservices = detail_penjualanJasaservices;
        this.detail_penjualanJasaservicesFilter = detail_penjualanJasaservices;
        this.mListenerPenjualanJasaservice = listener;
    }

    @NonNull
    @Override
    public PenjualanJasaService_Adapter.PenjualanJasaServiceViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_penjualan_jasaservice, parent, false);
        return new PenjualanJasaService_Adapter.PenjualanJasaServiceViewHolder(view, mListenerPenjualanJasaservice);
    }

    @SuppressLint("CheckResult")
    @Override
    public void onBindViewHolder(final PenjualanJasaService_Adapter.PenjualanJasaServiceViewHolder holder, int position) {
        //holder.mSubtotal.setText(detail_penjualanJasaservices.get(position).getSubtotal());
        holder.mNamaJasa.setText(detail_penjualanJasaservices.get(position).getId_jasaservice());
        holder.mJumlah.setText(detail_penjualanJasaservices.get(position).getJumlah());

        RequestOptions requestOptions = new RequestOptions();
        requestOptions.skipMemoryCache(true);
        requestOptions.diskCacheStrategy(DiskCacheStrategy.NONE);
        requestOptions.placeholder(R.drawable.ic_create);
        requestOptions.error(R.drawable.ic_create);
    }

    @Override
    public int getItemCount() {
        return detail_penjualanJasaservices.size();
    }

    public Filter getFilter() {
        if (filterPenjualanJasaservice==null) {
            filterPenjualanJasaservice=new CustomFilterPenjualanJasaService((ArrayList<Penjualan_JasaService>) detail_penjualanJasaservicesFilter,this);

        }
        return filterPenjualanJasaservice;
    }

    public class PenjualanJasaServiceViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private RecyclerViewPenjualanJasaServiceClickListener mListenerPenjualanJasaservice;
        private TextView mNamaJasa, mSubtotal, mJumlah;
        private RelativeLayout mRowContainer;

        public PenjualanJasaServiceViewHolder(View itemView, RecyclerViewPenjualanJasaServiceClickListener listener) {
            super(itemView);
            mNamaJasa = itemView.findViewById(R.id.nama_jasa);
            mSubtotal = itemView.findViewById(R.id.subtotal);
            mJumlah = itemView.findViewById(R.id.jumlah);

            mRowContainer = itemView.findViewById(R.id.row_container);
            mListenerPenjualanJasaservice = listener;
            mRowContainer.setOnClickListener(this);
        }

        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.row_container:
                    mListenerPenjualanJasaservice.onRowClick(mRowContainer, getAdapterPosition());
                    break;
                default:
                    break;
            }
        }
    }


    public interface RecyclerViewPenjualanJasaServiceClickListener {
        void onRowClick(View view, int position);
    }
}
