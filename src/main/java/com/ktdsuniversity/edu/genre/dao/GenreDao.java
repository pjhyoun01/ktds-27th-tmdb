package com.ktdsuniversity.edu.genre.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ktdsuniversity.edu.genre.vo.GenreVO;

@Mapper
public interface GenreDao {

	int insertGenre(GenreVO genreVO);
	
	List<GenreVO> selectAllGenre();
}
