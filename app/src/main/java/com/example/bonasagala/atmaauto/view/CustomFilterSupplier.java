package com.example.bonasagala.atmaauto.view;

import android.widget.Filter;

import com.example.bonasagala.atmaauto.model.Supplier;

import java.util.ArrayList;

public class CustomFilterSupplier extends Filter {
    SupplierAdapter adapterSupplier;
    ArrayList<Supplier> filterListSupplier;

    public CustomFilterSupplier(ArrayList<Supplier> filterListSupplier, SupplierAdapter adapterSupplier)
    {
        this.adapterSupplier=adapterSupplier;
        this.filterListSupplier=filterListSupplier;
    }

    protected FilterResults performFiltering(CharSequence constraint) {
        FilterResults results=new FilterResults();
        if(constraint != null && constraint.length() > 0)
        {
            //CHANGE TO UPPER
            constraint=constraint.toString().toUpperCase();
            //STORE OUR FILTERED PLAYERS
            ArrayList<Supplier> filteredSupplier=new ArrayList<>();

            for (int i=0;i<filterListSupplier.size();i++)
            {
                if(filterListSupplier.get(i).getNama_supplier().toUpperCase().contains(constraint))
                {
                    filteredSupplier.add(filterListSupplier.get(i));
                }
            }
            results.count=filteredSupplier.size();
            results.values=filteredSupplier;
        }
        else{
            results.count=filterListSupplier.size();
            results.values=filterListSupplier;
        }
        return results;
    }

    protected void publishResults(CharSequence constraint, FilterResults results) {
        adapterSupplier.suppliers= (ArrayList<Supplier>) results.values;
        //REFRESH
        adapterSupplier.notifyDataSetChanged();
    }
}
