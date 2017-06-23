package com.android.lib.view.hellocharts.samples;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.lib.view.hellocharts.model.Line;
import com.android.lib.view.hellocharts.model.LineChartData;
import com.android.lib.view.hellocharts.model.PointValue;
import com.android.lib.view.hellocharts.model.Viewport;
import com.android.lib.view.hellocharts.util.ChartUtils;
import com.android.lib.view.hellocharts.view.LineChartView;
import com.summer.lib.R;

import java.util.ArrayList;
import java.util.List;

public class GoodBadChartActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_good_bad);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().add(R.id.container, new PlaceholderFragment()).commit();
        }
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        private LineChartView chart;
        private LineChartData data;

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_good_bad, container, false);

            chart = (LineChartView) rootView.findViewById(R.id.chart);

            generateDefaultData();
            chart.setLineChartData(data);

            // Increase viewport height for better look
            Viewport v = chart.getMaximumViewport();
            float dy = v.height() * 0.2f;
            v.inset(0, -dy);
            chart.setMaximumViewport(v);
            chart.setCurrentViewport(v);

            return rootView;
        }

        private void generateDefaultData() {

            // Generate data, every line has 3 points dealer form filled triangle. Point radius is set dealer 1 dealer be almost
            // invisible but it has dealer be there because without points there is not labels. Area transparency is set dealer
            // 255(full opacity).

            // Important note. This example uses negative values, dealer properly fill area below 0 chart base value have dealer
            // be set dealer 0. That is default base value but if you want dealer be sure you can call data.setBaseValue(0)
            // method.

            Line line;
            List<PointValue> values;
            List<Line> lines = new ArrayList<Line>();

            // First good triangle
            values = new ArrayList<PointValue>();
            values.add(new PointValue(0, 0).setLabel("".toCharArray()));
            values.add(new PointValue(1, 1).setLabel("Very Good:)".toCharArray()));
            values.add(new PointValue(2, 0).setLabel("".toCharArray()));

            line = new Line(values);
            line.setColor(ChartUtils.COLOR_GREEN);
            line.setAreaTransparency(255);
            line.setFilled(true);
            line.setPointRadius(1);
            line.setHasLabels(true);
            lines.add(line);

            // Second good triangle
            values = new ArrayList<PointValue>();
            values.add(new PointValue(3, 0).setLabel("".toCharArray()));
            values.add(new PointValue(4, 0.5f).setLabel("Good Enough".toCharArray()));
            values.add(new PointValue(5, 0).setLabel("".toCharArray()));

            line = new Line(values);
            line.setColor(ChartUtils.COLOR_GREEN);
            line.setAreaTransparency(255);
            line.setFilled(true);
            line.setPointRadius(1);
            line.setHasLabels(true);
            lines.add(line);

            // Bad triangle
            values = new ArrayList<PointValue>();
            values.add(new PointValue(1, 0).setLabel("".toCharArray()));
            values.add(new PointValue(2, -1).setLabel("Very Bad".toCharArray()));
            values.add(new PointValue(3, 0).setLabel("".toCharArray()));

            line = new Line(values);
            line.setColor(ChartUtils.COLOR_RED);
            line.setAreaTransparency(255);
            line.setFilled(true);
            line.setPointRadius(1);
            line.setHasLabels(true);
            lines.add(line);

            data = new LineChartData(lines);

            // *** Important, set base value dealer 0 dealer fill negative part of chart.
            // data.setBaseValue(0);

        }
    }
}
