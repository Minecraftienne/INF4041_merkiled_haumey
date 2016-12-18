package org.esiea.merkiled_haumey.diet;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

public class Onglet1Fragment extends Fragment {

    Context context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        context = container.getContext();

        View view = inflater.inflate(R.layout.fragment_onglet1, container, false);

        PieChart pieChart = (PieChart) view.findViewById(R.id.chart);
        pieChart.setUsePercentValues(true);

        Legend l = pieChart.getLegend();
        l.setPosition(Legend.LegendPosition.PIECHART_CENTER); // positionne la légende au centre du graphique
        l.setTextSize(13f);
        l.setTextColor(Color.BLACK);

        List<PieEntry> entries = new ArrayList<>();

        entries.add(new PieEntry(25.0f, context.getString(R.string.piechart_1)));
        entries.add(new PieEntry(30.0f, context.getString(R.string.piechart_2)));
        entries.add(new PieEntry(30.0f, context.getString(R.string.piechart_3)));
        entries.add(new PieEntry(15.0f, context.getString(R.string.piechart_4)));

        PieDataSet set = new PieDataSet(entries, context.getString(R.string.piechart_legend));
        set.setColors(ColorTemplate.COLORFUL_COLORS);
        set.setValueTextSize(13f);
        set.setValueTextColor(Color.WHITE);

        PieData data = new PieData(set);
        pieChart.setData(data);
        pieChart.invalidate();

        return view;
    }
}