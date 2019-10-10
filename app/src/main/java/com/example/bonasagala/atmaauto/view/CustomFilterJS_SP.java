package com.example.bonasagala.atmaauto.view;

import android.widget.Filter;

import com.example.bonasagala.atmaauto.model.JS_SP;

import java.util.ArrayList;

public class CustomFilterJS_SP extends Filter {

    Penjualan_JS_SP_Adapter adapterPenjualanJS_SP;
    ArrayList<JS_SP> filterListPenjualanJS_SP;

    public CustomFilterJS_SP(ArrayList<JS_SP> filterListPenjualanJS_SP, Penjualan_JS_SP_Adapter adapterPenjualanJS_SP)
    {
        this.adapterPenjualanJS_SP=adapterPenjualanJS_SP;
        this.filterListPenjualanJS_SP=filterListPenjualanJS_SP;
    }

    protected FilterResults performFiltering(CharSequence constraint) {
        FilterResults results=new FilterResults();
        if(constraint != null && constraint.length() > 0)
        {
            //CHANGE TO UPPER
            constraint=constraint.toString().toUpperCase();
            //STORE OUR FILTERED PLAYERS
            ArrayList<JS_SP> filteredPenjualanJS_SP=new ArrayList<>();

            for (int i=0;i<filterListPenjualanJS_SP.size();i++)
            {
                if(filterListPenjualanJS_SP.get(i).getSubtotal().toUpperCase().contains(constraint))
                {
                    filteredPenjualanJS_SP.add(filterListPenjualanJS_SP.get(i));
                }
            }
            results.count=filteredPenjualanJS_SP.size();
            results.values=filteredPenjualanJS_SP;
        }
        else{
            results.count=filterListPenjualanJS_SP.size();
            results.values=filterListPenjualanJS_SP;
        }
        return results;
    }

    protected void publishResults(CharSequence constraint, FilterResults results) {
        adapterPenjualanJS_SP.detailPenjualanJS_SP= (ArrayList<JS_SP>) results.values;
        //REFRESH
        adapterPenjualanJS_SP.notifyDataSetChanged();
    }
}
