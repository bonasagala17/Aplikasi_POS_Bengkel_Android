package com.example.bonasagala.atmaauto.view;

import android.widget.Filter;

import com.example.bonasagala.atmaauto.model.Penjualan;

import java.util.ArrayList;

public class CustomFilterPenjualan extends Filter {

    PenjualanAdapter adapterPenjualanSparepart;
    ArrayList<Penjualan> filterListPenjualanSparepart;

    public CustomFilterPenjualan(ArrayList<Penjualan> filterListPenjualanSparepart, PenjualanAdapter adapterPenjualanSparepart)
    {
        this.adapterPenjualanSparepart=adapterPenjualanSparepart;
        this.filterListPenjualanSparepart=filterListPenjualanSparepart;
    }

    protected FilterResults performFiltering(CharSequence constraint) {
        FilterResults results=new FilterResults();
        if(constraint != null && constraint.length() > 0)
        {
            //CHANGE TO UPPER
            constraint=constraint.toString().toUpperCase();
            //STORE OUR FILTERED PLAYERS
            ArrayList<Penjualan> filteredPenjualanSparepart=new ArrayList<>();

            for (int i=0;i<filterListPenjualanSparepart.size();i++)
            {
                if(filterListPenjualanSparepart.get(i).getTanggal_transaksi().toUpperCase().contains(constraint))
                {
                    filteredPenjualanSparepart.add(filterListPenjualanSparepart.get(i));
                }
            }
            results.count=filteredPenjualanSparepart.size();
            results.values=filteredPenjualanSparepart;
        }
        else{
            results.count=filterListPenjualanSparepart.size();
            results.values=filterListPenjualanSparepart;
        }
        return results;
    }

    protected void publishResults(CharSequence constraint, FilterResults results) {
        adapterPenjualanSparepart.penjualan_s = (ArrayList<Penjualan>) results.values;
        //REFRESH
        adapterPenjualanSparepart.notifyDataSetChanged();
    }
}
