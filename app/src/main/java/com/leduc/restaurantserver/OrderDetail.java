package com.leduc.restaurantserver;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import com.leduc.restaurantserver.Common.Common;
import com.leduc.restaurantserver.ViewHolder.OrderDetailAdapter;

public class OrderDetail extends AppCompatActivity {

    TextView order_id, order_phone, order_address, order_total;
    String order_id_value="";
    RecyclerView listFoods;
    RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);

        order_id = (TextView) findViewById(R.id.order_id);
        order_phone = (TextView) findViewById(R.id.order_phone);
        order_address = (TextView) findViewById(R.id.order_address);
        order_total = (TextView) findViewById(R.id.order_total);

        listFoods = (RecyclerView) findViewById(R.id.listFoods);
        listFoods.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        listFoods.setLayoutManager(layoutManager);

        if (getIntent()!=null) {
            order_id_value = getIntent().getStringExtra("OrderId");

            order_id.setText(order_id_value);
            order_phone.setText(Common.currentRequest.getPhone());
            order_total.setText(Common.currentRequest.getTotal());
            order_address.setText(Common.currentRequest.getAddress());

            OrderDetailAdapter adapter = new OrderDetailAdapter(Common.currentRequest.getFoods());
            adapter.notifyDataSetChanged();
            listFoods.setAdapter(adapter);
        }
    }
}