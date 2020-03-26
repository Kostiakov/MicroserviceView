package com.example.view.entity;

import java.util.ArrayList;
import java.util.List;

public class LiteratureList {
	
	private List <LiteratureNew> list;
	
	public LiteratureList() {
		list = new ArrayList<>();
	}

	public List<LiteratureNew> getList() {
		return list;
	}

	public void setList(List<LiteratureNew> list) {
		this.list = list;
	}
	
	

}
