package com.ktdsuniversity.edu.genre.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.ktdsuniversity.edu.genre.service.GenreService;
import com.ktdsuniversity.edu.genre.vo.GenreVO;
import com.ktdsuniversity.edu.genre.vo.request.InsertVO;
import com.ktdsuniversity.edu.genre.vo.response.SearchResultVO;

@Controller
public class GenreController {

	@Autowired
	private GenreService genreService;

	@PostMapping("/genre/insert")
	public String doInsertGenre(InsertVO insertVO) {
		boolean insertSuccess = this.genreService.insertGenre(insertVO);
		if (insertSuccess) {
			return "redirect:/";
		} else {
			return "error/404";
		}
	}
	
	@GetMapping("/genre")
	public String viewGenreListPage(Model model) {
		SearchResultVO result = this.genreService.readAllGenre();
		List<GenreVO> list = result.getResult();
		
		model.addAttribute("result", list);
		
		return "/genre/genreList";
	}
}
