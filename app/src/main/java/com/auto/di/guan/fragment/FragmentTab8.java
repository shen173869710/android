package com.auto.di.guan.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.auto.di.guan.R;
import com.auto.di.guan.WebviewActivity;
import com.auto.di.guan.adapter.GunManagerAdapter;
import com.auto.di.guan.entity.GunManager;

import java.util.ArrayList;
import java.util.List;


public class FragmentTab8 extends BaseFragment  implements View.OnClickListener{
	RecyclerView fragment8List;
	private View view;
	GunManagerAdapter adapter;

	Button fragment_8_net;
	Button fragment_8_local;
	String [] titles = {
			"地表殇情",
			"气象信息",
			"气温",
			"气压",
			"日照",
			"风向",
			"风速",
			"降雨量"

	};
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.fragment_8, null);
		List<GunManager> list = new ArrayList<>();
		int length = titles.length;
		for (int i = 0; i < length; i++) {
			list.add(new GunManager(titles[i], "XXXX"));
		}

		fragment8List = view.findViewById(R.id.fragment_8_list);

		fragment_8_net = view.findViewById(R.id.fragment_8_net);
		fragment_8_local= view.findViewById(R.id.fragment_8_local);
		adapter = new GunManagerAdapter(list);
		fragment8List.setLayoutManager(new LinearLayoutManager(getContext()));
		fragment8List.setAdapter(adapter);


		fragment_8_net.setOnClickListener(this);
		fragment_8_local.setOnClickListener(this);
		return view;
	}

	@Override
	public void refreshData() {

	}

	@Override
	public void onClick(View v) {
		startActivity(new Intent(getContext(), WebviewActivity.class));
	}
}
