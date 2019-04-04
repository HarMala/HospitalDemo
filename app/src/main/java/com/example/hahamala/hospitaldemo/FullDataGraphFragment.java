/**
package com.example.hahamala.hospitaldemo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

public class FullDataGraphFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        int[] data_values = ((MainActivity) getActivity()).passDataValues();
        Log.d("ThisApp", "data_values[0]: " + data_values[0]);
        Log.d("ThisApp", "data_values[59]: " + data_values[59]);
        View view = inflater.inflate(R.layout.fulldatagrapharea, container, false);
        GraphView graph = (GraphView) view.findViewById(R.id.full_data_graph);
        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(new DataPoint[]{
                new DataPoint(0, data_values[58]),
                new DataPoint(1, data_values[57]),
                new DataPoint(2, data_values[56]),
                new DataPoint(3, data_values[55]),
                new DataPoint(4, data_values[54]),
                new DataPoint(5, data_values[53]),
                new DataPoint(6, data_values[52]),
                new DataPoint(7, data_values[51]),
                new DataPoint(8, data_values[50]),
                new DataPoint(9, data_values[49]),
                new DataPoint(10, data_values[48]),
                new DataPoint(11, data_values[47]),
                new DataPoint(12, data_values[46]),
                new DataPoint(13, data_values[45]),
                new DataPoint(14, data_values[44]),
                new DataPoint(15, data_values[43]),
                new DataPoint(16, data_values[42]),
                new DataPoint(17, data_values[41]),
                new DataPoint(18, data_values[40]),
                new DataPoint(19, data_values[39]),
                new DataPoint(20, data_values[38]),
                new DataPoint(21, data_values[37]),
                new DataPoint(22, data_values[36]),
                new DataPoint(23, data_values[35]),
                new DataPoint(24, data_values[34]),
                new DataPoint(25, data_values[33]),
                new DataPoint(26, data_values[32]),
                new DataPoint(28, data_values[31]),
                new DataPoint(29, data_values[30]),
                new DataPoint(30, data_values[29]),
                new DataPoint(31, data_values[28]),
                new DataPoint(32, data_values[27]),
                new DataPoint(33, data_values[26]),
                new DataPoint(34, data_values[25]),
                new DataPoint(35, data_values[24]),
                new DataPoint(36, data_values[23]),
                new DataPoint(37, data_values[22]),
                new DataPoint(38, data_values[21]),
                new DataPoint(39, data_values[20]),
                new DataPoint(40, data_values[19]),
                new DataPoint(41, data_values[18]),
                new DataPoint(42, data_values[17]),
                new DataPoint(43, data_values[16]),
                new DataPoint(44, data_values[15]),
                new DataPoint(45, data_values[14]),
                new DataPoint(46, data_values[13]),
                new DataPoint(47, data_values[12]),
                new DataPoint(48, data_values[11]),
                new DataPoint(49, data_values[10]),
                new DataPoint(50, data_values[9]),
                new DataPoint(51, data_values[8]),
                new DataPoint(52, data_values[7]),
                new DataPoint(53, data_values[6]),
                new DataPoint(54, data_values[5]),
                new DataPoint(55, data_values[4]),
                new DataPoint(56, data_values[3]),
                new DataPoint(57, data_values[2]),
                new DataPoint(58, data_values[1]),
                new DataPoint(59, data_values[0])
        });
        graph.getViewport().setYAxisBoundsManual(true);
        graph.getViewport().setMinY(60);
        graph.getViewport().setMaxY(100);
        graph.getViewport().setXAxisBoundsManual(true);
        graph.getViewport().setMinX(0);
        graph.getViewport().setMaxX(60);
        graph.getGridLabelRenderer().setNumHorizontalLabels(5);
        graph.getGridLabelRenderer().setHorizontalLabelsVisible(false);
        //graph.getGridLabelRenderer().setVerticalLabelsVisible(false);
        //graph.getGridLabelRenderer().setGridStyle( GridLabelRenderer.GridStyle.NONE );
        graph.getGridLabelRenderer().setHighlightZeroLines(false);
        graph.addSeries(series);
        return view;
    }
}
*/
