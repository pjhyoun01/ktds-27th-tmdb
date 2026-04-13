package com.ktdsuniversity.edu.helper;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class MultipartFileHandler {
	
	public void upload(List<MultipartFile> attachFiles, String fileGroupId) {

		if (attachFiles.size() < 1) {
			return;
		}

		for (MultipartFile file : attachFiles) {

			if (file.isEmpty()) {
				continue;
			}

			String obfuscateName = UUID.randomUUID().toString();

			File storeFile = new File("C:\\uploadFiles", obfuscateName);

			if (!storeFile.getParentFile().exists()) {
				storeFile.getParentFile().mkdirs();
			}

			try {
				file.transferTo(storeFile);

				System.out.println("파일 저장 완료: " + storeFile.getAbsolutePath());

			} 
			catch (IllegalStateException | IOException e) {
				e.printStackTrace();
			}
		}
	}

}
