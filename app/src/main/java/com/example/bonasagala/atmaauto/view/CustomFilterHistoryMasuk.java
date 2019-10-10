package com.example.bonasagala.atmaauto.view;

import android.widget.Filter;

import com.example.bonasagala.atmaauto.model.History_Masuk;

import java.util.ArrayList;

public class CustomFilterHistoryMasuk extends Filter {

    HistoryMasukAdapter adapterHistoryMasuk;
    ArrayList<History_Masuk> filterListHistoryMasuk;

    public CustomFilterHistoryMasuk(ArrayList<History_Masuk> filterListHistoryMasuk, HistoryMasukAdapter adapterHistoryMasuk)
    {
        this.adapterHistoryMasuk=adapterHistoryMasuk;
        this.filterListHistoryMasuk=filterListHistoryMasuk;
    }

    protected FilterResults performFiltering(CharSequence constraint) {
        FilterResults results=new FilterResults();
        if(constraint != null && constraint.length() > 0)
        {
            //CHANGE TO UPPER
            constraint=constraint.toString().toUpperCase();
            //STORE OUR FILTERED PLAYERS
            ArrayList<History_Masuk> filteredHistoryMasuk=new ArrayList<>();

            for (int i=0;i<filterListHistoryMasuk.size();i++)
            {
                if(filterListHistoryMasuk.get(i).getNama_sparepart().toUpperCase().contains(constraint))
                {
                    filteredHistoryMasuk.add(filterListHistoryMasuk.get(i));
                }
            }
            results.count=filteredHistoryMasuk.size();
            results.values=filteredHistoryMasuk;
        }
        else{
            results.count=filterListHistoryMasuk.size();
            results.values=filterListHistoryMasuk;
        }
        return results;
    }

    protected void publishResults(CharSequence constraint, FilterResults results) {
        adapterHistoryMasuk.history_masuks= (ArrayList<History_Masuk>) results.values;
        //REFRESH
        adapterHistoryMasuk.notifyDataSetChanged();
    }
}
