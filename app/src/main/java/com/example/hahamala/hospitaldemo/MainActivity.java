package com.example.hahamala.hospitaldemo;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.hardware.Camera;
import android.os.AsyncTask;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.jjoe64.graphview.DefaultLabelFormatter;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GridLabelRenderer;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class MainActivity extends FragmentActivity {

    Camera camera;
    ShowCamera showCamera;
    FrameLayout frameLayout;
    TextView bpm_value;
    TextView bp_syst_value;
    TextView bp_diast_value;
    TextView skincon_value;
    TextView x_acc_value;
    TextView y_acc_value;
    TextView z_acc_value;
    TextView resp_value;
    GraphView bpm_datagraph;
    GraphView xyz_datagraph;
    GraphView skincon_datagraph;
    GraphView bp_datagraph;
    GraphView resp_datagraph;
    String timestamp = "";
    Double datatimestamp_ms = 0.0;
    long dataupdate_ms = 0;
    List<Integer> data_values_bpm = new ArrayList<>();
    List<Double> data_values_x = new ArrayList<>();
    List<Double> data_values_y = new ArrayList<>();
    List<Double> data_values_z = new ArrayList<>();
    List<Double> data_values_skincon = new ArrayList<>();
    //int[] data_values_bpm = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    int[] data_values_bp_syst = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    int[] data_values_bp_diast = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    int[] data_values_resp = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};

    /**
    int count=0; // JUST A TEST, REMOVE LATER
    FrameLayout frameLayout_test; // JUST A TEST, REMOVE LATER
    GraphView datagraph_test; // TEST, REMOVE
    */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        for(int i = 0; i < 20; i++){
            data_values_bpm.add(0);
            data_values_x.add(0.0);
            data_values_y.add(0.0);
            data_values_z.add(0.0);
            data_values_skincon.add(0.0);
        }
        frameLayout = findViewById(R.id.frameLayout);

        camera = Camera.open();

        showCamera = new ShowCamera(this, camera);
        frameLayout.addView(showCamera);

        Log.d("ThisApp", "Going in!");
        bpm_value = (TextView) findViewById (R.id.bpm_value);
        bp_syst_value = (TextView) findViewById (R.id.bp_syst_value);
        bp_diast_value = (TextView) findViewById (R.id.bp_diast_value);
        resp_value = (TextView) findViewById (R.id.resp_value);
        skincon_value = (TextView) findViewById (R.id.skincon_value);
        x_acc_value = (TextView) findViewById (R.id.xyz_x_value);
        y_acc_value = (TextView) findViewById (R.id.xyz_y_value);
        z_acc_value = (TextView) findViewById (R.id.xyz_z_value);
        bpm_datagraph = (GraphView) findViewById(R.id.bpm_data_graph);
        xyz_datagraph = (GraphView) findViewById(R.id.xyz_data_graph);
        skincon_datagraph = (GraphView) findViewById(R.id.skincon_data_graph);
        bp_datagraph = (GraphView) findViewById(R.id.bp_data_graph);
        resp_datagraph = (GraphView) findViewById(R.id.resp_data_graph);
        new HTTPRequestT().execute();
        //bpm_value.setText("55");

    }

    /** HTTP REQ START */
    public class HTTPRequestT extends AsyncTask<Void, Void, Void> {
        public String mResult;

        @Override
        protected Void doInBackground(Void... arg0){
            try{
                HttpURLConnection mURLConnection;
                //URL url = new URL("https://pan0222.panoulu.net/5gtn/iprotoxi/read/1/data/ar");
                URL url = new URL("http://192.168.1.51:11111/read/data");
                mURLConnection = (HttpURLConnection) url.openConnection();
                mURLConnection.setConnectTimeout(1000);
                mURLConnection.setReadTimeout(1000);
                InputStream in = new BufferedInputStream(mURLConnection.getInputStream());
                int ch;
                StringBuffer b = new StringBuffer();
                while ((ch = in.read()) != -1){
                    b.append((char) ch);
                }
                mResult = new String(b);
                Log.d("ThisApp", "" + mResult);

            } catch (Exception e){
                Log.d("ThisApp", "" + e);
            }
            return null;
        }
        @Override
        protected void onPostExecute(Void v) {
            Log.d("ThisApp", "Gottem!");
            Log.d("ThisApp", "" + mResult);
            //bpm_value.setText(mResult);
            try {
                JSONArray dataArray = new JSONArray(mResult);
                String bpm_latest = dataArray.getJSONObject(0).getString("hr");
                String bp_syst_latest = dataArray.getJSONObject(2).getString("bp_syst");
                String bp_diast_latest = dataArray.getJSONObject(3).getString("bp_diast");
                String resp_latest = dataArray.getJSONObject(1).getString("resp");
                String GSR_con_latest = dataArray.getJSONObject(5).getString("GSR_con");
                String x_acc_latest = dataArray.getJSONObject(6).getString("x_acc");
                String y_acc_latest = dataArray.getJSONObject(7).getString("y_acc");
                String z_acc_latest = dataArray.getJSONObject(8).getString("z_acc");
                Log.d("ThisApp", "" + dataArray.getJSONObject(4).getString("ts"));
                Log.d("ThisApp", "Passed time between : " + (Double.parseDouble(dataArray.getJSONObject(4).getString("ts")) - datatimestamp_ms));
                Log.d("ThisApp", "Passed time since last graph update: " + (System.currentTimeMillis() - dataupdate_ms));
                if (datatimestamp_ms != 0 && dataupdate_ms != 0 && Double.parseDouble(dataArray.getJSONObject(4).getString("ts")) - datatimestamp_ms < 500 && System.currentTimeMillis() - dataupdate_ms <= 500){
                    Log.d("ThisApp", "Not enough time passed yet...");
                } else {
                    datatimestamp_ms = Double.parseDouble(dataArray.getJSONObject(4).getString("ts"));
                    dataupdate_ms = System.currentTimeMillis();

                    updateGraph(bpm_latest, bp_syst_latest, bp_diast_latest, resp_latest, x_acc_latest, y_acc_latest, z_acc_latest, GSR_con_latest);

                    bpm_value.setText(bpm_latest);
                    Log.d("TEST", "AAA");
                    Log.d("TEST", "" + bp_syst_latest);
                    Log.d("TEST", "AAA");
                    skincon_value.setText(String.format("%.2f", Double.parseDouble(GSR_con_latest)));
                    x_acc_value.setText(String.format("%.1f", Double.parseDouble(x_acc_latest)));
                    y_acc_value.setText(String.format("%.1f", Double.parseDouble(y_acc_latest)));
                    z_acc_value.setText(String.format("%.1f", Double.parseDouble(z_acc_latest)));
                    Log.d("TEST", "" + GSR_con_latest);
                    /**
                    bp_syst_value.setText(bp_syst_latest);
                    bp_diast_value.setText(bp_diast_latest);
                    resp_value.setText(resp_latest);
                     */
                }
                /**
                if (timestamp.equals(dataArray.getJSONObject(4).getString("ts"))){
                    Log.d("ThisApp", "Values not changed yet...");
                } else {
                    timestamp = dataArray.getJSONObject(4).getString("ts");

                    updateGraph(bpm_latest, bp_syst_latest, bp_diast_latest, resp_latest);

                    bpm_value.setText(bpm_latest);
                    bp_syst_value.setText(bp_syst_latest);
                    bp_diast_value.setText(bp_diast_latest);
                    resp_value.setText(resp_latest);
                }
                */
            } catch (JSONException e){
                Log.d("ThisApp", "JSONException");
            } catch (NullPointerException e){
                Log.d("ThisApp", "NullPointerException");
            }
            new HTTPRequestT().execute();
        }
    }
    /** HTTP REQ END */

    public void updateGraph(String bpm_new_value, String bp_syst_new_value, String bp_diast_new_value, String resp_new_value, String x_new_value, String y_new_value, String z_new_value, String skincon_new_value){
        Log.d("ThisApp", "Adding value: ");
        Log.d("ThisApp", "" + Integer.parseInt(bpm_new_value));

        for (int i = 19; i > 0; i--) {
            data_values_bpm.set(i, data_values_bpm.get(i - 1));
        }
        data_values_bpm.set(0, Integer.parseInt(bpm_new_value));

        LineGraphSeries<DataPoint> bpm_updated_series = new LineGraphSeries<>(new DataPoint[]{
                new DataPoint(0, data_values_bpm.get(18)),
                new DataPoint(1, data_values_bpm.get(17)),
                new DataPoint(2, data_values_bpm.get(16)),
                new DataPoint(3, data_values_bpm.get(15)),
                new DataPoint(4, data_values_bpm.get(14)),
                new DataPoint(5, data_values_bpm.get(13)),
                new DataPoint(6, data_values_bpm.get(12)),
                new DataPoint(7, data_values_bpm.get(11)),
                new DataPoint(8, data_values_bpm.get(10)),
                new DataPoint(9, data_values_bpm.get(9)),
                new DataPoint(10, data_values_bpm.get(8)),
                new DataPoint(11, data_values_bpm.get(7)),
                new DataPoint(12, data_values_bpm.get(6)),
                new DataPoint(13, data_values_bpm.get(5)),
                new DataPoint(14, data_values_bpm.get(4)),
                new DataPoint(15, data_values_bpm.get(3)),
                new DataPoint(16, data_values_bpm.get(2)),
                new DataPoint(18, data_values_bpm.get(1)),
                new DataPoint(19, data_values_bpm.get(0)),
        });
        int min = Collections.min(data_values_bpm);
        int max = Collections.max(data_values_bpm);
        if (min < 50){
            bpm_datagraph.getViewport().setMinY(0);
        }
        while (min % 50 != 0){
            min--;
        }
        bpm_datagraph.getViewport().setMinY(min);
        while (max % 50 != 0){
            max++;
        }
        bpm_datagraph.getViewport().setMaxY(max);

        bpm_updated_series.setColor(Color.WHITE);
        bpm_updated_series.setThickness(1);
        bpm_datagraph.removeAllSeries();
        bpm_datagraph.addSeries(bpm_updated_series);

        /** XYZ */

        for (int i = 19; i > 0; i--) {
            data_values_x.set(i, data_values_x.get(i - 1));
        }
        data_values_x.set(0, Double.parseDouble(x_new_value));

        LineGraphSeries<DataPoint> x_updated_series = new LineGraphSeries<>(new DataPoint[]{
                new DataPoint(0, data_values_x.get(18)),
                new DataPoint(1, data_values_x.get(17)),
                new DataPoint(2, data_values_x.get(16)),
                new DataPoint(3, data_values_x.get(15)),
                new DataPoint(4, data_values_x.get(14)),
                new DataPoint(5, data_values_x.get(13)),
                new DataPoint(6, data_values_x.get(12)),
                new DataPoint(7, data_values_x.get(11)),
                new DataPoint(8, data_values_x.get(10)),
                new DataPoint(9, data_values_x.get(9)),
                new DataPoint(10, data_values_x.get(8)),
                new DataPoint(11, data_values_x.get(7)),
                new DataPoint(12, data_values_x.get(6)),
                new DataPoint(13, data_values_x.get(5)),
                new DataPoint(14, data_values_x.get(4)),
                new DataPoint(15, data_values_x.get(3)),
                new DataPoint(16, data_values_x.get(2)),
                new DataPoint(18, data_values_x.get(1)),
                new DataPoint(19, data_values_x.get(0)),
        });
        x_updated_series.setColor(Color.CYAN);
        x_updated_series.setThickness(1);

        for (int i = 19; i > 0; i--) {
            data_values_y.set(i, data_values_y.get(i - 1));
        }
        data_values_y.set(0, Double.parseDouble(y_new_value));

        LineGraphSeries<DataPoint> y_updated_series = new LineGraphSeries<>(new DataPoint[]{
                new DataPoint(0, data_values_y.get(18)),
                new DataPoint(1, data_values_y.get(17)),
                new DataPoint(2, data_values_y.get(16)),
                new DataPoint(3, data_values_y.get(15)),
                new DataPoint(4, data_values_y.get(14)),
                new DataPoint(5, data_values_y.get(13)),
                new DataPoint(6, data_values_y.get(12)),
                new DataPoint(7, data_values_y.get(11)),
                new DataPoint(8, data_values_y.get(10)),
                new DataPoint(9, data_values_y.get(9)),
                new DataPoint(10, data_values_y.get(8)),
                new DataPoint(11, data_values_y.get(7)),
                new DataPoint(12, data_values_y.get(6)),
                new DataPoint(13, data_values_y.get(5)),
                new DataPoint(14, data_values_y.get(4)),
                new DataPoint(15, data_values_y.get(3)),
                new DataPoint(16, data_values_y.get(2)),
                new DataPoint(18, data_values_y.get(1)),
                new DataPoint(19, data_values_y.get(0)),
        });
        y_updated_series.setColor(Color.YELLOW);
        y_updated_series.setThickness(1);

        for (int i = 19; i > 0; i--) {
            data_values_z.set(i, data_values_z.get(i - 1));
        }
        data_values_z.set(0, Double.parseDouble(z_new_value));

        LineGraphSeries<DataPoint> z_updated_series = new LineGraphSeries<>(new DataPoint[]{
                new DataPoint(0, data_values_z.get(18)),
                new DataPoint(1, data_values_z.get(17)),
                new DataPoint(2, data_values_z.get(16)),
                new DataPoint(3, data_values_z.get(15)),
                new DataPoint(4, data_values_z.get(14)),
                new DataPoint(5, data_values_z.get(13)),
                new DataPoint(6, data_values_z.get(12)),
                new DataPoint(7, data_values_z.get(11)),
                new DataPoint(8, data_values_z.get(10)),
                new DataPoint(9, data_values_z.get(9)),
                new DataPoint(10, data_values_z.get(8)),
                new DataPoint(11, data_values_z.get(7)),
                new DataPoint(12, data_values_z.get(6)),
                new DataPoint(13, data_values_z.get(5)),
                new DataPoint(14, data_values_z.get(4)),
                new DataPoint(15, data_values_z.get(3)),
                new DataPoint(16, data_values_z.get(2)),
                new DataPoint(18, data_values_z.get(1)),
                new DataPoint(19, data_values_z.get(0)),
        });
        z_updated_series.setColor(Color.GREEN);
        z_updated_series.setThickness(1);

        xyz_datagraph.removeAllSeries();
        xyz_datagraph.addSeries(x_updated_series);
        xyz_datagraph.addSeries(y_updated_series);
        xyz_datagraph.addSeries(z_updated_series);
        /** XYZ END */


        /** BP */ /**
        Log.d("ThisApp", "Adding value: ");
        Log.d("ThisApp", "" + Integer.parseInt(bp_syst_new_value));
        Log.d("ThisApp", "" + Integer.parseInt(bp_diast_new_value));

        for (int i = 59; i > 0; i--) {
            data_values_bp_syst[i] = data_values_bp_syst[i - 1];
        }
        data_values_bp_syst[0] = Integer.parseInt(bp_syst_new_value);
        LineGraphSeries<DataPoint> bp_syst_updated_series = new LineGraphSeries<>(new DataPoint[]{
                new DataPoint(0, data_values_bp_syst[8]),
                new DataPoint(1, data_values_bp_syst[7]),
                new DataPoint(2, data_values_bp_syst[6]),
                new DataPoint(3, data_values_bp_syst[5]),
                new DataPoint(4, data_values_bp_syst[4]),
                new DataPoint(5, data_values_bp_syst[3]),
                new DataPoint(6, data_values_bp_syst[2]),
                new DataPoint(7, data_values_bp_syst[1]),
                new DataPoint(8, data_values_bp_syst[0])
        });
        bp_syst_updated_series.setColor(Color.CYAN);
        bp_syst_updated_series.setThickness(1);

        for (int i = 59; i > 0; i--) {
            data_values_bp_diast[i] = data_values_bp_diast[i - 1];
        }
        data_values_bp_diast[0] = Integer.parseInt(bp_diast_new_value);
        LineGraphSeries<DataPoint> bp_diast_updated_series = new LineGraphSeries<>(new DataPoint[]{
                new DataPoint(0, data_values_bp_diast[8]),
                new DataPoint(1, data_values_bp_diast[7]),
                new DataPoint(2, data_values_bp_diast[6]),
                new DataPoint(3, data_values_bp_diast[5]),
                new DataPoint(4, data_values_bp_diast[4]),
                new DataPoint(5, data_values_bp_diast[3]),
                new DataPoint(6, data_values_bp_diast[2]),
                new DataPoint(7, data_values_bp_diast[1]),
                new DataPoint(8, data_values_bp_diast[0])
        });
        bp_diast_updated_series.setColor(Color.YELLOW);
        bp_diast_updated_series.setThickness(1);
        bp_datagraph.removeAllSeries();
        bp_datagraph.addSeries(bp_diast_updated_series);
        bp_datagraph.addSeries(bp_syst_updated_series);
        */

        /** SKIN CONDUCTANCE */

        for (int i = 19; i > 0; i--) {
            data_values_skincon.set(i, data_values_skincon.get(i - 1));
        }
        data_values_skincon.set(0, Double.parseDouble(skincon_new_value));

        LineGraphSeries<DataPoint> skincon_updated_series = new LineGraphSeries<>(new DataPoint[]{
                new DataPoint(0, data_values_skincon.get(18)),
                new DataPoint(1, data_values_skincon.get(17)),
                new DataPoint(2, data_values_skincon.get(16)),
                new DataPoint(3, data_values_skincon.get(15)),
                new DataPoint(4, data_values_skincon.get(14)),
                new DataPoint(5, data_values_skincon.get(13)),
                new DataPoint(6, data_values_skincon.get(12)),
                new DataPoint(7, data_values_skincon.get(11)),
                new DataPoint(8, data_values_skincon.get(10)),
                new DataPoint(9, data_values_skincon.get(9)),
                new DataPoint(10, data_values_skincon.get(8)),
                new DataPoint(11, data_values_skincon.get(7)),
                new DataPoint(12, data_values_skincon.get(6)),
                new DataPoint(13, data_values_skincon.get(5)),
                new DataPoint(14, data_values_skincon.get(4)),
                new DataPoint(15, data_values_skincon.get(3)),
                new DataPoint(16, data_values_skincon.get(2)),
                new DataPoint(18, data_values_skincon.get(1)),
                new DataPoint(19, data_values_skincon.get(0)),
        });

        double min_skincon = Math.ceil(Collections.min(data_values_skincon));
        double max_skincon = Math.ceil(Collections.max(data_values_skincon));
        if (min_skincon < 3){
            bpm_datagraph.getViewport().setMinY(0);
        }
        while (min_skincon % 3 != 0){
            min_skincon--;
        }
        skincon_datagraph.getViewport().setMinY(min_skincon);
        while (max_skincon % 3 != 0){
            max_skincon++;
        }
        skincon_datagraph.getViewport().setMaxY(max_skincon);

        skincon_updated_series.setColor(Color.WHITE);
        skincon_updated_series.setThickness(1);
        skincon_datagraph.removeAllSeries();
        skincon_datagraph.addSeries(skincon_updated_series);
        /** SKIN CONDUCTANCE END */

        /** RESPIRATORY RATE */ /**
        Log.d("ThisApp", "Adding value: ");
        Log.d("ThisApp", "" + Integer.parseInt(resp_new_value));

        for (int i = 59; i > 0; i--) {
            data_values_resp[i] = data_values_resp[i - 1];
        }
        data_values_resp[0] = Integer.parseInt(resp_new_value);
        LineGraphSeries<DataPoint> resp_updated_series = new LineGraphSeries<>(new DataPoint[]{
                new DataPoint(0, data_values_resp[8]),
                new DataPoint(1, data_values_resp[7]),
                new DataPoint(2, data_values_resp[6]),
                new DataPoint(3, data_values_resp[5]),
                new DataPoint(4, data_values_resp[4]),
                new DataPoint(5, data_values_resp[3]),
                new DataPoint(6, data_values_resp[2]),
                new DataPoint(7, data_values_resp[1]),
                new DataPoint(8, data_values_resp[0])
        });
        resp_updated_series.setColor(Color.WHITE);
        resp_updated_series.setThickness(1);
        resp_datagraph.removeAllSeries();
        resp_datagraph.addSeries(resp_updated_series);
         */
    }
    /**
    public int[] passDataValues(){
        return data_values_bpm;
    }
*/
    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {

        switch (keyCode){
            case KeyEvent.KEYCODE_ENTER:
                Log.d("Button", "Back button was pressed...");
        }
        return super.onKeyUp(keyCode, event);
    }
}

