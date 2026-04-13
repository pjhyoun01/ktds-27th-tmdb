package com.ktdsuniversity.edu.members.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ktdsuniversity.edu.exceptions.HelloSpringException;
import com.ktdsuniversity.edu.helper.SHA256Util;
import com.ktdsuniversity.edu.members.dao.MembersDao;
import com.ktdsuniversity.edu.members.vo.MembersVO;
import com.ktdsuniversity.edu.members.vo.request.LoginVO;
import com.ktdsuniversity.edu.members.vo.request.RegistVO;
import com.ktdsuniversity.edu.members.vo.request.UpdateVO;
import com.ktdsuniversity.edu.members.vo.response.SearchResultMVO;

@Service
public class MembersServiceImpl implements MembersService {

	@Autowired
	private MembersDao membersDao;

	@Override
	public MembersVO findMemberByEmailAndPassword(LoginVO loginVO) {
		
		MembersVO searchResult = this.membersDao.selectMemberByEmail(loginVO.getEmail());
		
		if(searchResult == null) {
			throw new IllegalArgumentException("이메일 또는 비밀번호가 잘못되었습니다");
		
	}	
		String inputPassword = loginVO.getPassword();
		String storedSalt = searchResult.getSalt();
		String encryptedPassword = SHA256Util.getEncrypt(inputPassword, storedSalt);
	
	if(!encryptedPassword.equals(searchResult.getPassword())) {
		
		this.membersDao.updateIncreaseLoginFailCount(loginVO.getEmail());
		
		this.membersDao.updateBlock(loginVO.getEmail());
		
		throw new HelloSpringException("이메일 또는 비밀번호가 잘못되었습니다", "members/login", loginVO);
	}

	this.membersDao.updateSuccessLogin(loginVO);
	
	return searchResult;
	}
	
	@Override
	public boolean updateMemberByEmail(UpdateVO updateVO) {
		int updateCount = this.membersDao.updateMemberByEmail(updateVO);
		return updateCount == 1;
	}

	@Override
	public List<MembersVO> readAllMember() {
		List<MembersVO> memberList = this.membersDao.selectAllMember();

		return memberList;
	}

	@Override
	public MembersVO readMemberByEmail(String email) {
		MembersVO membersVO = this.membersDao.selectMembersByEmail(email);

		return membersVO;
	}

	@Override
	public boolean createNewMember(RegistVO registVO) {

		MembersVO membersVO = this.membersDao.selectMemberByEmail(registVO.getEmail());
		if (membersVO != null) {
			throw new IllegalArgumentException(registVO.getEmail() + "은 이미 사용 중입니다");
		}

		String newSalt = SHA256Util.generateSalt();
		String usersPassword = registVO.getPassword();

		usersPassword = SHA256Util.getEncrypt(usersPassword, newSalt);

		registVO.setSalt(newSalt);
		registVO.setPassword(usersPassword);

		int insertCount = this.membersDao.insertNewMember(registVO);
		return insertCount == 1;
	}
	
	@Override
	public boolean deleteMemberByEmail(String email) {
		int deleteSuccessCount = this.membersDao.deleteMemerByEmail(email);

		return deleteSuccessCount > 0;
	}
	

}
