package com.example.admin.testmap;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ShowMapActivity extends AppCompatActivity {
    private MapView mapView;
    @Bind(R.id.but_putong)
    Button puTongBt;
    @Bind(R.id.but_star)
    Button starBt;
    @Bind(R.id.but_traffic)
    Button trafficBt;
    @Bind(R.id.but_hot)
    Button hotBt;
    @Bind(R.id.but_marker)
    Button showMarkerBt;
    @Bind(R.id.but_tuozhuai)
    Button tuoZhuaiBt;
    private BaiduMap mBaiduMap;
    private boolean flag_hot = true;
    private boolean flag_traffic = true;
    private LatLng point;
    private Marker marker;
    OverlayOptions  option;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_map);
        ButterKnife.bind(this);
        mapView = (MapView) findViewById(R.id.bmapView);
        mBaiduMap = mapView.getMap();
        initView();
        initData();
    }

    private void initData() {

    }

    private void initView() {
        tuoZhuaiBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                point = new LatLng(39.963175, 116.400244);
                BitmapDescriptor bitmap = BitmapDescriptorFactory.fromResource(R.drawable.marker);
                option = new MarkerOptions().position(point).icon(bitmap).zIndex(9).draggable(true);

                marker = (Marker) mBaiduMap.addOverlay(option);
            }
        });
        showMarkerBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        mBaiduMap.setOnMarkerDragListener(new BaiduMap.OnMarkerDragListener() {
            @Override
            public void onMarkerDrag(Marker marker) {
                Toast.makeText(ShowMapActivity.this, "拖拽中", Toast.LENGTH_SHORT).show();
                marker = (Marker) (mBaiduMap.addOverlay(option));
            }

            @Override
            public void onMarkerDragEnd(Marker marker) {
                Toast.makeText(ShowMapActivity.this, "拖拽后", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onMarkerDragStart(Marker marker) {
                Toast.makeText(ShowMapActivity.this, "开始拖拽", Toast.LENGTH_SHORT).show();

            }
        });

        puTongBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mBaiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);
            }
        });
        starBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBaiduMap.setMapType(BaiduMap.MAP_TYPE_SATELLITE);


            }
        });
        trafficBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(flag_traffic){
                    flag_traffic = !flag_traffic;
                    mBaiduMap.setTrafficEnabled(true);


                }else{
                    flag_traffic = !flag_traffic;
                    mBaiduMap.setTrafficEnabled(false);


                }
            }
        });
        hotBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(flag_hot){
                    flag_hot = !flag_hot;
                    mBaiduMap.setBaiduHeatMapEnabled(true);

                }else{
                    flag_hot = !flag_hot;
                    mBaiduMap.setBaiduHeatMapEnabled(false);
                }
            }
        });
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }
}
