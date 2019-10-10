package com.example.bonasagala.atmaauto.view;

import android.widget.Filter;

import com.example.bonasagala.atmaauto.model.Konsumen;

import java.util.ArrayList;

public class CustomFilterKonsumen extends Filter {

    KonsumenAdapter adapterKonsumen;
    ArrayList<Konsumen> filterListKonsumen;

    public CustomFilterKonsumen(ArrayList<Konsumen> filterListKonsumen, KonsumenAdapter adapterKonsumen)
    {
        this.adapterKonsumen=adapterKonsumen;
        this.filterListKonsumen=filterListKonsumen;
    }

    protected FilterResults performFiltering(CharSequence constraint) {
        FilterResults results=new FilterResults();
        if(constraint != null && constraint.length() > 0)
        {
            //CHANGE TO UPPER
            constraint=constraint.toString().toUpperCase();
            //STORE OUR FILTERED PLAYERS
            ArrayList<Konsumen> filteredKonsumen=new ArrayList<>();

            for (int i=0;i<filterListKonsumen.size();i++)
            {
                if(filterListKonsumen.get(i).getNama_konsumen().toUpperCase().contains(constraint))
                {
                    filteredKonsumen.add(filterListKonsumen.get(i));
                }
            }
            results.count=filteredKonsumen.size();
            results.values=filteredKonsumen;
        }
        else{
            results.count=filterListKonsumen.size();
            results.values=filterListKonsumen;
        }
        return results;
    }

    protected void publishResults(CharSequence constraint, FilterResults results) {
        adapterKonsumen.konsumens= (ArrayList<Konsumen>) results.values;
        //REFRESH
        adapterKonsumen.notifyDataSetChanged();
    }

}
