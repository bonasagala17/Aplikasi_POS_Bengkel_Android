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
import com.example.bonasagala.atmaauto.model.Cabang;

import java.util.ArrayList;
import java.util.List;

public class CabangAdapter extends RecyclerView.Adapter<CabangAdapter.CabangViewHolder> implements Filterable{

    List<Cabang> cabangs, cabangsFilter;
   // private Context context;
    private RecyclerViewClickListener mListener;
    CustomFilterCabang filter;

    public CabangAdapter(List<Cabang> cabangs, RecyclerViewClickListener listener) {
        this.cabangs = cabangs;
        this.cabangsFilter = cabangs;
        //this.context = context;
        this.mListener = listener;
    }

    @Override
    public CabangViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_cabang,parent,false);
        return new CabangViewHolder(view, mListener);
    }

    @SuppressLint("CheckResult")
    @Override
    public void onBindViewHolder(final CabangViewHolder holder, int position) {
        holder.mNamaCabang.setText(cabangs.get(position).getNama_cabang());
        holder.mAlamatCabang.setText(cabangs.get(position).getAlamat_cabang());

        RequestOptions requestOptions = new RequestOptions();
        requestOptions.skipMemoryCache(true);
        requestOptions.diskCacheStrategy(DiskCacheStrategy.NONE);
        requestOptions.placeholder(R.drawable.ic_create);
        requestOptions.error(R.drawable.ic_create);

    }

    @Override
    public int getItemCount() {
        return cabangs.size();
    }

    public Filter getFilter() {
        if (filter==null) {
            filter=new CustomFilterCabang((ArrayList<Cabang>) cabangsFilter,this);

        }
        return filter;
    }


    public class CabangViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView mNamaCabang, mAlamatCabang;
        private RecyclerViewClickListener mListener;
        private RelativeLayout mRowContainer;

        public CabangViewHolder(View itemView, RecyclerViewClickListener listener) {
            super(itemView);
            mNamaCabang = itemView.findViewById(R.id.nama_cabang);
            mAlamatCabang = itemView.findViewById(R.id.alamat_cabang);
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

    public interface RecyclerViewClickListener {
        void onRowClick(View view, int position);
    }
}
