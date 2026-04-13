package com.ktdsuniversity.edu.members.web;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ktdsuniversity.edu.members.service.MembersService;
import com.ktdsuniversity.edu.members.vo.MembersVO;
import com.ktdsuniversity.edu.members.vo.request.LoginVO;
import com.ktdsuniversity.edu.members.vo.request.RegistVO;
import com.ktdsuniversity.edu.members.vo.response.DuplicateResultVO;
import com.ktdsuniversity.edu.members.vo.response.DuplicateVO;
import com.ktdsuniversity.edu.members.vo.response.SearchResultMVO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class MembersController {
	
	private static final Logger logger = LoggerFactory.getLogger(MembersController.class);

	@Autowired
	private MembersService membersService;

	@GetMapping("/login")
	public String viewLoginPage() {
		return "members/login";
	}

	@PostMapping("/login")
	public String doLoginAction(@Valid @ModelAttribute LoginVO loginVO, BindingResult bindingResult, Model model,
			@RequestParam(required = false, defaultValue = "/list") String go, HttpServletRequest request) {

		if (bindingResult.hasErrors()) {
			model.addAttribute("loginData", loginVO);
			return "members/login";
		}
		String userIp = request.getRemoteAddr();
		loginVO.setIp(userIp);

		MembersVO member = this.membersService.findMemberByEmailAndPassword(loginVO);

		request.getSession().invalidate();

		HttpSession session = request.getSession(true);
		session.setAttribute("__LOGIN_DATA__", member);

		return "redirect:" + go;
	}

	@GetMapping("/logout")
	public String doLogout() {
		return "redirect:/";
	}

	@GetMapping("/member/view")
	public String viewMembers(Model model) {
		List<MembersVO> memberList = this.membersService.readAllMember();

		model.addAttribute("memberList", memberList);

		return "members/list";
	}

	@GetMapping("/member/view/{email}")
	public String viewOneMembers(@PathVariable String email, Model model) {
		MembersVO membersVO = this.membersService.readMemberByEmail(email);

		model.addAttribute("memberList", membersVO);

		return "members/view";
	}

	@GetMapping("/regist")
	public String viewRegistPage() {
		return "members/regist";
	}

	@ResponseBody
	@GetMapping("/regist/check/duplicate/{email}")
	public DuplicateResultVO doCheckDuplicateEmailAction(@PathVariable String email) {

		MembersVO membersVO = this.membersService.findMemberByEmail(email);
		DuplicateResultVO result = new DuplicateResultVO();

		result.setEmail(email);
		result.setDuplicate(membersVO != null);

		return result;
	}

	@PostMapping("/regist")
	public String doRegistAction(@Valid @ModelAttribute RegistVO registVO, BindingResult bindingResult, Model model) {

		if (bindingResult.hasErrors()) {

			model.addAttribute("inputData", registVO);
			return "members/regist";
		}

		boolean createResult = this.membersService.createNewMember(registVO);
		System.out.println("회원 가입 결과? " + createResult);

		return "redirect:/member";

	}

	@PostMapping("/member/delete")
	public String deleteMembersByEmail(HttpSession session) {
		MembersVO loginUser = (MembersVO) session.getAttribute("__USER__");
		boolean deleteSuccecc = this.membersService.deleteMemberByEmail(loginUser.getEmail());
		if (deleteSuccecc) {
			return "redirect:/";
		} else {
			return "error/404";
		}
	}
	
	@GetMapping("/member")
	public String viewListMember(Model model) {
		SearchResultMVO searchResultM = this.membersService.findAllMembers();

		List<MembersVO> list = searchResultM.getResult();

		int count = searchResultM.getCount();

		model.addAttribute("searchResultM", list);
		model.addAttribute("searchCountM", count);

		return "members/list";
	}
	
	
}
