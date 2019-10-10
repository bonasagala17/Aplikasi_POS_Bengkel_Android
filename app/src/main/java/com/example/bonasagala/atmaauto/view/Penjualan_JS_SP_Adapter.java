package com.example.bonasagala.atmaauto.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
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
import com.example.bonasagala.atmaauto.model.JS_SP;

import java.util.ArrayList;
import java.util.List;

public class Penjualan_JS_SP_Adapter extends RecyclerView.Adapter<Penjualan_JS_SP_Adapter.PenjualanJS_SPViewHolder>{

    List<JS_SP> detailPenjualanJS_SP, detailPenjualanJS_SPFilter;
    private Context context;
    private RecyclerViewPenjualanJS_SPClickListener mListenerPenjualanJS_SP;
    CustomFilterJS_SP filterPenjualanJS_SP;

    public Penjualan_JS_SP_Adapter(List<JS_SP> detailPenjualanJS_SP, RecyclerViewPenjualanJS_SPClickListener listener) {
        this.detailPenjualanJS_SP = detailPenjualanJS_SP;
        this.detailPenjualanJS_SPFilter = detailPenjualanJS_SP;
        this.mListenerPenjualanJS_SP = listener;
    }

    @NonNull
    @Override
    public Penjualan_JS_SP_Adapter.PenjualanJS_SPViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_penjualan_js_sp, parent, false);
        return new Penjualan_JS_SP_Adapter.PenjualanJS_SPViewHolder(view, mListenerPenjualanJS_SP);
    }

    @SuppressLint("CheckResult")
    @Override
    public void onBindViewHolder(final Penjualan_JS_SP_Adapter.PenjualanJS_SPViewHolder holder, int position) {
        holder.mSubtotal.setText(detailPenjualanJS_SP.get(position).getSubtotal());
        holder.mJumlah.setText(detailPenjualanJS_SP.get(position).getJumlah());

        RequestOptions requestOptions = new RequestOptions();
        requestOptions.skipMemoryCache(true);
        requestOptions.diskCacheStrategy(DiskCacheStrategy.NONE);
        requestOptions.placeholder(R.drawable.ic_create);
        requestOptions.error(R.drawable.ic_create);
    }

    public int getItemCount() {
        return detailPenjualanJS_SP.size();
    }

   public Filter getFilter() {
        if (filterPenjualanJS_SP==null) {
            filterPenjualanJS_SP=new CustomFilterJS_SP((ArrayList<JS_SP>) detailPenjualanJS_SPFilter,this);

        }
        return filterPenjualanJS_SP;
    }

    public class PenjualanJS_SPViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private RecyclerViewPenjualanJS_SPClickListener mListenerPenjualanJS_SP;
        private TextView mSubtotal, mJumlah;
        private RelativeLayout mRowContainer;

        public PenjualanJS_SPViewHolder(View itemView, RecyclerViewPenjualanJS_SPClickListener listener) {
            super(itemView);
            mSubtotal = itemView.findViewById(R.id.subtotal);
            mJumlah = itemView.findViewById(R.id.jumlah);

            mRowContainer = itemView.findViewById(R.id.row_container);
            mListenerPenjualanJS_SP = listener;
            mRowContainer.setOnClickListener(this);
        }

        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.row_container:
                    mListenerPenjualanJS_SP.onRowClick(mRowContainer, getAdapterPosition());
                    break;
                default:
                    break;
            }
        }
    }

    public interface RecyclerViewPenjualanJS_SPClickListener {
        void onRowClick(View view, int position);
    }
}
