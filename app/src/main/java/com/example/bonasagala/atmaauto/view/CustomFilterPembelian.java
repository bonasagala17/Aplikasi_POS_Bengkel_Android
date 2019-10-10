package com.example.bonasagala.atmaauto.view;

import android.widget.Filter;

import com.example.bonasagala.atmaauto.model.Pembelian_Sparepart;

import java.util.ArrayList;

public class CustomFilterPembelian extends Filter {

    PembelianAdapter adapterPembelian;
    ArrayList<Pembelian_Sparepart> filterListPembelian;

    public CustomFilterPembelian(ArrayList<Pembelian_Sparepart> filterListPembelian, PembelianAdapter adapterPembelian)
    {
        this.adapterPembelian=adapterPembelian;
        this.filterListPembelian=filterListPembelian;
    }

    protected FilterResults performFiltering(CharSequence constraint) {
        FilterResults results=new FilterResults();
        if(constraint != null && constraint.length() > 0)
        {
            //CHANGE TO UPPER
            constraint=constraint.toString().toUpperCase();
            //STORE OUR FILTERED PLAYERS
            ArrayList<Pembelian_Sparepart> filteredPembelian = new ArrayList<>();

            for (int i=0;i<filterListPembelian.size();i++)
            {
                if(filterListPembelian.get(i).getTanggal_pembelian().toUpperCase().contains(constraint))
                {
                    filteredPembelian.add(filterListPembelian.get(i));
                }
            }
            results.count=filteredPembelian.size();
            results.values=filteredPembelian;
        }
        else{
            results.count=filterListPembelian.size();
            results.values=filterListPembelian;
        }
        return results;
    }

    protected void publishResults(CharSequence constraint, FilterResults results) {
        adapterPembelian.pembelian_spareparts= (ArrayList<Pembelian_Sparepart>) results.values;
        //REFRESH
        adapterPembelian.notifyDataSetChanged();
    }

}
