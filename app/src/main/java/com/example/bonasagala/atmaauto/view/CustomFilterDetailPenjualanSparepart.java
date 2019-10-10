package com.example.bonasagala.atmaauto.view;

import android.widget.Filter;

import com.example.bonasagala.atmaauto.model.Detail_PenjualanSparepart;

import java.util.ArrayList;

public class CustomFilterDetailPenjualanSparepart extends Filter {
    DetailPenjualanSparepart_Adapter adapterDetailPenjualanSparepart;
    ArrayList<Detail_PenjualanSparepart> filterListDetailPenjualanSparepart;

    public CustomFilterDetailPenjualanSparepart(ArrayList<Detail_PenjualanSparepart> filterListDetailPenjualanSparepart, DetailPenjualanSparepart_Adapter adapterDetailPenjualanSparepart)
    {
        this.adapterDetailPenjualanSparepart=adapterDetailPenjualanSparepart;
        this.filterListDetailPenjualanSparepart=filterListDetailPenjualanSparepart;
    }

    protected FilterResults performFiltering(CharSequence constraint) {
        FilterResults results=new FilterResults();
        if(constraint != null && constraint.length() > 0)
        {
            //CHANGE TO UPPER
            constraint=constraint.toString().toUpperCase();
            //STORE OUR FILTERED PLAYERS
            ArrayList<Detail_PenjualanSparepart> filteredDetailPenjualanSparepart=new ArrayList<>();

            for (int i=0;i<filterListDetailPenjualanSparepart.size();i++)
            {
                if(filterListDetailPenjualanSparepart.get(i).getId_sparepart().toUpperCase().contains(constraint))
                {
                    filteredDetailPenjualanSparepart.add(filterListDetailPenjualanSparepart.get(i));
                }
            }
            results.count=filteredDetailPenjualanSparepart.size();
            results.values=filteredDetailPenjualanSparepart;
        }
        else{
            results.count=filterListDetailPenjualanSparepart.size();
            results.values=filterListDetailPenjualanSparepart;
        }
        return results;
    }

    protected void publishResults(CharSequence constraint, FilterResults results) {
        adapterDetailPenjualanSparepart.detail_penjualanSpareparts= (ArrayList<Detail_PenjualanSparepart>) results.values;
        //REFRESH
        adapterDetailPenjualanSparepart.notifyDataSetChanged();
    }
}
