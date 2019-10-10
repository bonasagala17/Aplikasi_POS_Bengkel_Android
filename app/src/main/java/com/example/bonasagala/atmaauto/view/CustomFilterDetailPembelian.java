package com.example.bonasagala.atmaauto.view;

import android.widget.Filter;

import com.example.bonasagala.atmaauto.model.Detail_PembelianSparepart;

import java.util.ArrayList;

public class CustomFilterDetailPembelian extends Filter {

    DetailPembelianAdapter adapterDetailPembelian;
    ArrayList<Detail_PembelianSparepart> filterListDetailPembelian;

    public CustomFilterDetailPembelian(ArrayList<Detail_PembelianSparepart> filterListDetailPembelian, DetailPembelianAdapter adapterDetailPembelian)
    {
        this.adapterDetailPembelian=adapterDetailPembelian;
        this.filterListDetailPembelian=filterListDetailPembelian;
    }

    protected FilterResults performFiltering(CharSequence constraint) {
        FilterResults results=new FilterResults();
        if(constraint != null && constraint.length() > 0)
        {
            //CHANGE TO UPPER
            constraint=constraint.toString().toUpperCase();
            //STORE OUR FILTERED PLAYERS
            ArrayList<Detail_PembelianSparepart> filteredDetailPembelian=new ArrayList<>();

            for (int i=0;i<filterListDetailPembelian.size();i++)
            {
                if(filterListDetailPembelian.get(i).getHargabeli().toUpperCase().contains(constraint))
                {
                    filteredDetailPembelian.add(filterListDetailPembelian.get(i));
                }
            }
            results.count=filteredDetailPembelian.size();
            results.values=filteredDetailPembelian;
        }
        else{
            results.count=filterListDetailPembelian.size();
            results.values=filterListDetailPembelian;
        }
        return results;
    }
    protected void publishResults(CharSequence constraint, FilterResults results) {
        adapterDetailPembelian.detail_pembelianSpareparts= (ArrayList<Detail_PembelianSparepart>) results.values;
        //REFRESH
        adapterDetailPembelian.notifyDataSetChanged();
    }
}
