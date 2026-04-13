package com.ktdsuniversity.edu.members.service;

import com.ktdsuniversity.edu.members.vo.MembersVO;
import com.ktdsuniversity.edu.members.vo.request.LoginVO;
import com.ktdsuniversity.edu.members.vo.request.RegistVO;
import com.ktdsuniversity.edu.members.vo.request.UpdateVO;
import com.ktdsuniversity.edu.members.vo.response.SearchResultMVO;

public interface MembersService {

	MembersVO findMemberByEmailAndPassword(LoginVO loginVO);

	List<MembersVO> readAllMember();

	MembersVO readMemberByEmail(String email);

	boolean createMember(RegistVO registVO);

	boolean deleteMemberByEmail(String email);
	
	boolean updateMemberByEmail(UpdateVO updateVO);

	MembersVO findMemberByEmail(String email);
	
	boolean createNewMember(RegistVO registVO);
}
