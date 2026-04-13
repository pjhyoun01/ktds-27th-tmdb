package com.ktdsuniversity.edu.actor.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.ktdsuniversity.edu.actor.service.ActorService;
import com.ktdsuniversity.edu.actor.vo.ActorVO;
import com.ktdsuniversity.edu.actor.vo.request.ActorWriteVO;
import com.ktdsuniversity.edu.actor.vo.request.InsertVO;
import com.ktdsuniversity.edu.actor.vo.request.UpdateVO;
import com.ktdsuniversity.edu.members.vo.MembersVO;

import jakarta.validation.Valid;

@Controller
public class ActorController {

	@Autowired
	private ActorService actorService;

	@GetMapping("/actor/view")
	public String viewActorListPage(Model model) {
		List<ActorVO> actorList = this.actorService.readAllActor();
		model.addAttribute("actorList", actorList);

		return "actor/list";
	}

	@GetMapping("/actor/view/{actorId}")
	public String viewActorPage(@PathVariable String actorId, Model model) {
		ActorVO actor = this.actorService.readActorByActorId(actorId);
		model.addAttribute("actor", actor);

		return "actor/view" + actorId;
	}

	@GetMapping("/actor/insert")
	public String viewInsertPage() {
		return "actor/insert";
	}

	@PostMapping("/actor")
	public String doActorWriteAction(@Valid @ModelAttribute ActorWriteVO actorWriteVO, BindingResult bindingResult, Model model
			                       , @SessionAttribute(value="__LOGIN_DATA__", required=false) MembersVO loginMember) {
		
		if(bindingResult.hasErrors()) {
			model.addAttribute("inputData", actorWriteVO );
			
			return "actor/write";
		}
		
		boolean creatResult = this.actorService.createNewActor(actorWriteVO);
		
		
		return "redirect:/actor/view";
	}

	@GetMapping("/actor/update/{actorId}")
	public String viewUpdatePage(@PathVariable String actorId , Model model) {
		ActorVO actor = this.actorService.readActorByActorId(actorId);
		model.addAttribute("actor", actor);
		return "actor/update";
	}
	
	@PostMapping("/actor/update/{actorId}")
	public String doUpdate(@PathVariable String actorId, UpdateVO updateVO) {
		updateVO.setActorId(actorId);
		boolean updateSuccess = this.actorService.updateActorByActorId(updateVO);
		return "";
	}

	@PostMapping("/actor/delete/{actorId}")
	public String doDelete(@PathVariable String actorId) {
		boolean deleteSuccess = this.actorService.deleteActorByActorId(actorId);
		return "";
	}
}
