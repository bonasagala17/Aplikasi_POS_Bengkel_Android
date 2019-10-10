package com.example.bonasagala.atmaauto.view;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.bonasagala.atmaauto.R;
import com.example.bonasagala.atmaauto.model.Supplier;

public class SupplierAdapter extends RecyclerView.Adapter<SupplierAdapter.SupplierViewHolder> implements Filterable{

    List<Supplier> suppliers, suppliersFilter;
    private RecyclerViewSupplierClickListener mListenerSupplier;
    CustomFilterSupplier filterSupplier;

    public SupplierAdapter(List<Supplier> suppliers, RecyclerViewSupplierClickListener listenerSupplier) {
        this.suppliers = suppliers;
        this.suppliersFilter = suppliers;
        this.mListenerSupplier = listenerSupplier;
    }

    @Override
    public SupplierViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_supplier,parent,false);
        return new SupplierViewHolder(view, mListenerSupplier);
    }

    @Override
    public void onBindViewHolder(final SupplierViewHolder holder, int position) {
        holder.mNamaSupplier.setText(suppliers.get(position).getNama_supplier());
        holder.mAlamatSupplier.setText(suppliers.get(position).getAlamat_supplier());


        RequestOptions requestOptions = new RequestOptions();
        requestOptions.skipMemoryCache(true);
        requestOptions.diskCacheStrategy(DiskCacheStrategy.NONE);
        requestOptions.placeholder(R.drawable.ic_create);
        requestOptions.error(R.drawable.ic_create);

    }

    @Override
    public int getItemCount() {
        return suppliers.size();
    }

    public Filter getFilter() {
        if (filterSupplier==null) {
            filterSupplier=new CustomFilterSupplier((ArrayList<Supplier>) suppliersFilter, this);
        }
        return filterSupplier;
    }

    public class SupplierViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView mNamaSupplier, mAlamatSupplier, mTelpSupplier, mNamaSales, mTelpSales;
        private RecyclerViewSupplierClickListener mListener;
        private RelativeLayout mRowContainer;

        public SupplierViewHolder(View itemView, RecyclerViewSupplierClickListener listener) {
            super(itemView);
            mNamaSupplier = itemView.findViewById(R.id.nama_supplier);
            mAlamatSupplier = itemView.findViewById(R.id.alamat_supplier);
            mTelpSupplier = itemView.findViewById(R.id.telp_supplier);
            mNamaSales = itemView.findViewById(R.id.nama_sales);
            mTelpSales = itemView.findViewById(R.id.telp_sales);
            mRowContainer = itemView.findViewById(R.id.row_container);

            mListener = listener;
            mRowContainer.setOnClickListener(this);
        }

        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.row_container:
                    mListener.onRowClick(mRowContainer, getAdapterPosition());
                    break;
                default:
                    break;
            }
        }
    }

    public interface RecyclerViewSupplierClickListener {
        void onRowClick(View view, int position);
    }


}
