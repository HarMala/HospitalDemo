
package com.example.hahamala.hospitaldemo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

public class XYZDataGraphFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.xyzdatagraph, container, false);
        GraphView graph = (GraphView) view.findViewById(R.id.xyz_data_graph);

        LineGraphSeries<DataPoint> series1 = new LineGraphSeries<>(new DataPoint[] {
                new DataPoint(0, 0),
                new DataPoint(1, 0),
                new DataPoint(2, 0),
                new DataPoint(3, 0),
                new DataPoint(4, 0),
                new DataPoint(5, 0),
                new DataPoint(6, 0),
                new DataPoint(7, 0),
                new DataPoint(8, 0),
                new DataPoint(9, 0),
                new DataPoint(10, 0),
                new DataPoint(11, 0),
                new DataPoint(12, 0),
                new DataPoint(13, 0),
                new DataPoint(14, 0),
                new DataPoint(15, 0),
                new DataPoint(16, 0),
                new DataPoint(17, 0),
                new DataPoint(18, 0),
                new DataPoint(19, 0)
        });

        LineGraphSeries<DataPoint> series2 = new LineGraphSeries<>(new DataPoint[] {
                new DataPoint(0, 0),
                new DataPoint(1, 0),
                new DataPoint(2, 0),
                new DataPoint(3, 0),
                new DataPoint(4, 0),
                new DataPoint(5, 0),
                new DataPoint(6, 0),
                new DataPoint(7, 0),
                new DataPoint(8, 0),
                new DataPoint(9, 0),
                new DataPoint(10, 0),
                new DataPoint(11, 0),
                new DataPoint(12, 0),
                new DataPoint(13, 0),
                new DataPoint(14, 0),
                new DataPoint(15, 0),
                new DataPoint(16, 0),
                new DataPoint(17, 0),
                new DataPoint(18, 0),
                new DataPoint(19, 0)
        });

        LineGraphSeries<DataPoint> series3 = new LineGraphSeries<>(new DataPoint[] {
                new DataPoint(0, 0),
                new DataPoint(1, 0),
                new DataPoint(2, 0),
                new DataPoint(3, 0),
                new DataPoint(4, 0),
                new DataPoint(5, 0),
                new DataPoint(6, 0),
                new DataPoint(7, 0),
                new DataPoint(8, 0),
                new DataPoint(9, 0),
                new DataPoint(10, 0),
                new DataPoint(11, 0),
                new DataPoint(12, 0),
                new DataPoint(13, 0),
                new DataPoint(14, 0),
                new DataPoint(15, 0),
                new DataPoint(16, 0),
                new DataPoint(17, 0),
                new DataPoint(18, 0),
                new DataPoint(19, 0)
        });

        graph.getViewport().setYAxisBoundsManual(true);
        graph.getViewport().setXAxisBoundsManual(true);
        graph.getViewport().setMinY(-15);
        graph.getViewport().setMaxY(15);
        graph.getViewport().setMinX(0);
        graph.getViewport().setMaxX(20);
        graph.getGridLabelRenderer().setNumHorizontalLabels(6);
        graph.getGridLabelRenderer().setNumVerticalLabels(4);
        graph.getGridLabelRenderer().setHorizontalLabelsVisible(false);
        //graph.getGridLabelRenderer().setVerticalLabelsVisible(false);
        //graph.getGridLabelRenderer().setGridStyle( GridLabelRenderer.GridStyle.NONE );
        graph.getGridLabelRenderer().setHighlightZeroLines(false);
        graph.addSeries(series1);
        graph.addSeries(series2);
        graph.addSeries(series3);
        return view;
    }
}
