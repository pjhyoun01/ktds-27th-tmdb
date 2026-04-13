package com.ktdsuniversity.edu.members.service;

import java.util.List;

import com.ktdsuniversity.edu.members.vo.MembersVO;
import com.ktdsuniversity.edu.members.vo.request.LoginVO;
import com.ktdsuniversity.edu.members.vo.request.RegistVO;
import com.ktdsuniversity.edu.members.vo.request.UpdateVO;
import com.ktdsuniversity.edu.members.vo.response.SearchResultMVO;

public interface MembersService {

	boolean createNewMember(RegistVO registVO);

	MembersVO findMemberByEmail(String email);

	boolean updateMemberByEmail(UpdateVO updateVO);

	boolean deleteMemberByEmail(String email);

	SearchResultMVO findAllMembers();

	MembersVO findMemberByEmailAndPassword(LoginVO loginVO);

	List<MembersVO> readAllMember();

	MembersVO readMemberByEmail(String email);
}
