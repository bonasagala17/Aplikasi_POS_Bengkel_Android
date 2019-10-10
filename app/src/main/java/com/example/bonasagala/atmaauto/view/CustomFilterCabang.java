package com.example.bonasagala.atmaauto.view;

import android.widget.Filter;

import com.example.bonasagala.atmaauto.model.Cabang;

import java.util.ArrayList;

public class CustomFilterCabang extends Filter {

    CabangAdapter adapter;
    ArrayList<Cabang> filterList;

    public CustomFilterCabang(ArrayList<Cabang> filterList, CabangAdapter adapter)
    {
        this.adapter=adapter;
        this.filterList=filterList;
    }

    protected FilterResults performFiltering(CharSequence constraint) {
        FilterResults results=new FilterResults();
        if(constraint != null && constraint.length() > 0)
        {
            //CHANGE TO UPPER
            constraint=constraint.toString().toUpperCase();
            //STORE OUR FILTERED PLAYERS
            ArrayList<Cabang> filteredCabang=new ArrayList<>();

            for (int i=0;i<filterList.size();i++)
            {
                //CHECK
                if(filterList.get(i).getNama_cabang().toUpperCase().contains(constraint))
                {
                    //ADD PLAYER TO FILTERED PLAYERS
                    filteredCabang.add(filterList.get(i));
                }
            }
            results.count=filteredCabang.size();
            results.values=filteredCabang;
        }else
        {
            results.count=filterList.size();
            results.values=filterList;
        }
        return results;
    }

    protected void publishResults(CharSequence constraint, FilterResults results) {
        adapter.cabangs= (ArrayList<Cabang>) results.values;
        //REFRESH
        adapter.notifyDataSetChanged();
    }
}
