package com.ktdsuniversity.edu.genre.vo.response;

import java.util.List;

import com.ktdsuniversity.edu.genre.vo.GenreVO;

public class SearchResultVO{
	
	private int count;
	private List<GenreVO> result;
	
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public List<GenreVO> getResult() {
		return result;
	}
	public void setResult(List<GenreVO> result) {
		this.result = result;
	}
}
