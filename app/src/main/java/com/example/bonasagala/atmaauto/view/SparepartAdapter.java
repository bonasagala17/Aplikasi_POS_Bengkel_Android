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
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.example.bonasagala.atmaauto.R;
import com.example.bonasagala.atmaauto.model.Sparepart;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class SparepartAdapter extends RecyclerView.Adapter<SparepartAdapter.SparepartViewHolder> implements Filterable{

    List<Sparepart> spareparts, sparepartsFilter;
    private Context context;
    private RecyclerViewSparepartClickListener mListenerSparepart;
    CustomFilterSparepart filterSparepart;

    public SparepartAdapter(List<Sparepart> spareparts, Context context, RecyclerViewSparepartClickListener listener){
        this.spareparts = spareparts;
        this.sparepartsFilter = spareparts;
        this.context = context;
        this.mListenerSparepart = listener;
    }

    @NonNull
    @Override
    public SparepartViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_sparepart,parent,false);
        return new SparepartViewHolder(view, mListenerSparepart);
    }

    @SuppressLint("CheckResult")
    @Override
    public void onBindViewHolder(final SparepartViewHolder holder, int position) {
        holder.mNama_sparepart.setText(spareparts.get(position).getNama_sparepart());
        holder.mTipe_sparepart.setText(spareparts.get(position).getTipe_sparepart());
        holder.mLetak_sparepart.setText(spareparts.get(position).getLetak_sparepart());

        RequestOptions requestOptions = new RequestOptions();
        requestOptions.skipMemoryCache(true);
        requestOptions.diskCacheStrategy(DiskCacheStrategy.NONE);
        requestOptions.placeholder(R.drawable.ic_create);
        requestOptions.error(R.drawable.ic_create);

        Glide.with(context)
                .load(spareparts.get(position).getGambar_sparepart())
                .apply(requestOptions)
                .into(holder.mGambar_sparepart);

    }

    @Override
    public int getItemCount() {
        return spareparts.size();
    }

    public Filter getFilter() {
        if (filterSparepart==null) {
            filterSparepart=new CustomFilterSparepart((ArrayList<Sparepart>) sparepartsFilter, this);
        }
        return filterSparepart;
    }

    public class SparepartViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private RecyclerViewSparepartClickListener mListenerSparepart;
        private CircleImageView mGambar_sparepart;
        private TextView mNama_sparepart, mMerk_sparepart, mTipe_sparepart, mHargabeli_sparepart,
                mHargajual_sparepart, mStok_sparepart, mStokoptimal_sparepart, mLetak_sparepart;
        private RelativeLayout mRowContainer;

        public SparepartViewHolder(View itemView, RecyclerViewSparepartClickListener listener){
            super(itemView);
            mGambar_sparepart = itemView.findViewById(R.id.gambar_sparepart);
            mNama_sparepart = itemView.findViewById(R.id.nama_sparepart);
            mTipe_sparepart = itemView.findViewById(R.id.tipe_sparepart);
            mLetak_sparepart = itemView.findViewById(R.id.letak_sparepart);

            mRowContainer = itemView.findViewById(R.id.row_container);
            mListenerSparepart = listener;
            mRowContainer.setOnClickListener(this);
        }

        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.row_container:
                    mListenerSparepart.onRowClick(mRowContainer, getAdapterPosition());
                    break;
                default:
                    break;
            }
        }
    }

    public interface RecyclerViewSparepartClickListener {
        void onRowClick(View view, int position);
    }
}
