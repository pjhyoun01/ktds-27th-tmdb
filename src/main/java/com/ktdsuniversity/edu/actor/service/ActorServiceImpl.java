package com.ktdsuniversity.edu.actor.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.ktdsuniversity.edu.actor.dao.ActorDao;
import com.ktdsuniversity.edu.actor.vo.ActorVO;
import com.ktdsuniversity.edu.actor.vo.request.ActorWriteVO;
import com.ktdsuniversity.edu.actor.vo.request.InsertVO;
import com.ktdsuniversity.edu.actor.vo.request.UpdateVO;
import com.ktdsuniversity.edu.common.handler.FileHandler;
import com.ktdsuniversity.edu.file.dao.FileDao;
import com.ktdsuniversity.edu.file.vo.request.UploadVO;


@Service
public class ActorServiceImpl implements ActorService {

	@Autowired
	private ActorDao actorDao;

	@Autowired
	private FileDao fileDao;

	@Autowired
	private FileHandler fileHandler;

	@Override
	public List<ActorVO> readAllActor() {
		List<ActorVO> actorList = this.actorDao.seleteAllActor();
		return actorList;
	}

	@Override
	public List<ActorVO> readActorByMovieId(String movieId) {
		List<ActorVO> actorList = this.actorDao.selectActorByMovieId(movieId);
		return actorList;
	}

	@Override
	public ActorVO readActorByActorId(String movieId) {
		ActorVO actor = this.actorDao.selectActorByActorId(movieId);
		return actor;
	}


	@Override
	public boolean updateActorByActorId(UpdateVO updateVO) {
		if (updateVO.getFileGroupId() != null) {
			String path = this.fileDao.selectPathByFileGroupId(updateVO.getFileGroupId());
			new File(path).delete();

			this.fileDao.deleteFileByFileGroupId(updateVO.getFileGroupId());

			this.fileHandler.uploadOneFile(updateVO.getFile(), updateVO.getFileGroupId());
		} else {
			String fileGroupId = this.fileHandler.uploadOneFile(updateVO.getFile());
			updateVO.setFileGroupId(fileGroupId);
		}

		String path = this.fileDao.selectPathByFileGroupId(updateVO.getFileGroupId());
		updateVO.setActorProfileUrl(path);

		int updateSuccessCount = this.actorDao.updateActorByActorId(updateVO);
		return updateSuccessCount == 1;
	}

	@Override
	public boolean deleteActorByActorId(String actorId) {
		int deleteSuccessCount = this.actorDao.deleteActorByActorId(actorId);
		return deleteSuccessCount == 1;
	}
	
	@Transactional
	@Override
	public boolean createNewActor(ActorWriteVO actorWriteVO) {
		
		int insertCount = this.actorDao.insertNewActor(actorWriteVO);
		
		List<MultipartFile> attachFiles = actorWriteVO.getAttachFiles();

		if (attachFiles != null && attachFiles.size() > 0) {
			for (int i = 0; i < attachFiles.size(); i++) {
				
				
				File storeFile = new File("c://uploadfiles", attachFiles.get(i).getOriginalFilename());
				if (!storeFile.getParentFile().exists()) {
					storeFile.getParentFile().mkdirs();
				}
				try {
					attachFiles.get(i).transferTo(storeFile);
					UploadVO uploadVO = new UploadVO();
					String filename = attachFiles.get(i).getOriginalFilename();
					String ext = filename.substring(filename.lastIndexOf(".") + 1);
					uploadVO.setFileGroupId(actorWriteVO.getActorId());
					uploadVO.setObfuscateName(filename);
					uploadVO.setDisplayName(filename);
					uploadVO.setExtendName(ext);
					uploadVO.setFileLength(storeFile.length());
					uploadVO.setFilePath(storeFile.getAbsolutePath());

				this.fileDao.insertAttachFile(uploadVO);


				} catch (IllegalStateException | IOException e) {

					throw new RuntimeException("업로드 실패", e);
				}

			}

		}
		
		return insertCount == 1;

	}

	
	

}
