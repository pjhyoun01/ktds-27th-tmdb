package com.ktdsuniversity.edu.genre.service;

import com.ktdsuniversity.edu.genre.vo.request.InsertVO;
import com.ktdsuniversity.edu.genre.vo.response.SearchResultVO;

public interface GenreService {

	boolean insertGenre(InsertVO insertVO);
	
	SearchResultVO readAllGenre();

}
