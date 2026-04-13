package com.ktdsuniversity.edu.actor.vo.request;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.NotEmpty;


public class ActorWriteVO {
	
	private String actorId;
	@NotEmpty(message="이름을 입력하세요")
	private String actorName;
	@NotEmpty(message="URL을 입력하세요")
	private String actorProfileUrl;
	
	private List<MultipartFile> attachFiles;

	
	public String getActorId() {
		return this.actorId;
	}

	public void setActorId(String actorId) {
		this.actorId = actorId;
	}

	public String getActorName() {
		return this.actorName;
	}

	public void setActorName(String actorName) {
		this.actorName = actorName;
	}

	public String getActorProfileUrl() {
		return this.actorProfileUrl;
	}

	public void setActorProfileUrl(String actorProfileUrl) {
		this.actorProfileUrl = actorProfileUrl;
	}

	public List<MultipartFile> getAttachFiles() {
		return attachFiles;
	}

	public void setAttachFiles(List<MultipartFile> attachFiles) {
		this.attachFiles = attachFiles;
	}

	
	
	
	
}
