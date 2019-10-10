package com.example.bonasagala.atmaauto.view;

import android.widget.Filter;

import com.example.bonasagala.atmaauto.model.History_Keluar;

import java.util.ArrayList;

public class CustomFilterHistoryKeluar extends Filter {

    HistoryKeluarAdapter adapterHistoryKeluar;
    ArrayList<History_Keluar> filterListHistoryKeluar;

    public CustomFilterHistoryKeluar(ArrayList<History_Keluar> filterListHistoryKeluar, HistoryKeluarAdapter adapterHistoryKeluar)
    {
        this.adapterHistoryKeluar=adapterHistoryKeluar;
        this.filterListHistoryKeluar=filterListHistoryKeluar;
    }

    protected FilterResults performFiltering(CharSequence constraint) {
        FilterResults results=new FilterResults();
        if(constraint != null && constraint.length() > 0)
        {
            //CHANGE TO UPPER
            constraint=constraint.toString().toUpperCase();
            //STORE OUR FILTERED PLAYERS
            ArrayList<History_Keluar> filteredHistoryKeluar=new ArrayList<>();

            for (int i=0;i<filterListHistoryKeluar.size();i++)
            {
                if(filterListHistoryKeluar.get(i).getNama_sparepart().toUpperCase().contains(constraint))
                {
                    filteredHistoryKeluar.add(filterListHistoryKeluar.get(i));
                }
            }
            results.count=filteredHistoryKeluar.size();
            results.values=filteredHistoryKeluar;
        }
        else{
            results.count=filterListHistoryKeluar.size();
            results.values=filterListHistoryKeluar;
        }
        return results;
    }

    protected void publishResults(CharSequence constraint, FilterResults results) {
        adapterHistoryKeluar.history_keluars= (ArrayList<History_Keluar>) results.values;
        //REFRESH
        adapterHistoryKeluar.notifyDataSetChanged();
    }
}
