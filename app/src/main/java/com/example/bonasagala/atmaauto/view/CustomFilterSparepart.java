package com.example.bonasagala.atmaauto.view;

import android.widget.Filter;

import com.example.bonasagala.atmaauto.model.Sparepart;

import java.util.ArrayList;

public class CustomFilterSparepart extends Filter {
    SparepartAdapter adapterSparepart;
    ArrayList<Sparepart> filterListSparepart;

    public CustomFilterSparepart(ArrayList<Sparepart> filterListSparepart, SparepartAdapter adapterSparepart)
    {
        this.adapterSparepart=adapterSparepart;
        this.filterListSparepart=filterListSparepart;
    }

    protected FilterResults performFiltering(CharSequence constraint) {
        FilterResults results=new FilterResults();
        if(constraint != null && constraint.length() > 0)
        {
            //CHANGE TO UPPER
            constraint=constraint.toString().toUpperCase();
            //STORE OUR FILTERED PLAYERS
            ArrayList<Sparepart> filteredSparepart=new ArrayList<>();

            for (int i=0;i<filterListSparepart.size();i++)
            {
                if(filterListSparepart.get(i).getNama_sparepart().toUpperCase().contains(constraint))
                {
                    filteredSparepart.add(filterListSparepart.get(i));
                }
            }
            results.count=filteredSparepart.size();
            results.values=filteredSparepart;
        }
        else{
            results.count=filterListSparepart.size();
            results.values=filterListSparepart;
        }
        return results;
    }

    protected void publishResults(CharSequence constraint, FilterResults results) {
        adapterSparepart.spareparts= (ArrayList<Sparepart>) results.values;
        //REFRESH
        adapterSparepart.notifyDataSetChanged();
    }
}
