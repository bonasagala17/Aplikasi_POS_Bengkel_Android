package com.example.bonasagala.atmaauto.view;

import android.widget.Filter;

import com.example.bonasagala.atmaauto.model.Penjualan_JasaService;

import java.util.ArrayList;

public class CustomFilterPenjualanJasaService extends Filter {

    PenjualanJasaService_Adapter adapterPenjualanJasaService;
    ArrayList<Penjualan_JasaService> filterListPenjualanJasaService;

    public CustomFilterPenjualanJasaService(ArrayList<Penjualan_JasaService> filterListPenjualanJasaService, PenjualanJasaService_Adapter adapterPenjualanJasaService)
    {
        this.adapterPenjualanJasaService=adapterPenjualanJasaService;
        this.filterListPenjualanJasaService=filterListPenjualanJasaService;
    }

    protected FilterResults performFiltering(CharSequence constraint) {
        FilterResults results=new FilterResults();
        if(constraint != null && constraint.length() > 0)
        {
            //CHANGE TO UPPER
            constraint=constraint.toString().toUpperCase();
            //STORE OUR FILTERED PLAYERS
            ArrayList<Penjualan_JasaService> filteredDetailPenjualanJasaService=new ArrayList<>();

            for (int i=0;i<filterListPenjualanJasaService.size();i++)
            {
                if(filterListPenjualanJasaService.get(i).getSubtotal().toUpperCase().contains(constraint))
                {
                    filteredDetailPenjualanJasaService.add(filterListPenjualanJasaService.get(i));
                }
            }
            results.count=filteredDetailPenjualanJasaService.size();
            results.values=filteredDetailPenjualanJasaService;
        }
        else{
            results.count=filterListPenjualanJasaService.size();
            results.values=filterListPenjualanJasaService;
        }
        return results;
    }

    protected void publishResults(CharSequence constraint, FilterResults results) {
        adapterPenjualanJasaService.detail_penjualanJasaservices= (ArrayList<Penjualan_JasaService>) results.values;
        //REFRESH
        adapterPenjualanJasaService.notifyDataSetChanged();
    }
}
