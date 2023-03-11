package com.ptit.springbootdepartmentstore.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.Bucket;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import com.ptit.springbootdepartmentstore.dto.response.FileUploadDto;

@Component
public class DataBucketUtil {

	private static final Logger logger = LoggerFactory.getLogger(DataBucketUtil.class);

	@Value("${gcp.config.file}")
	private String gcpConfigFile;

	@Value("${gcp.project.id}")
	private String gcpProjectId;

	@Value("${gcp.bucket.id}")
	private String gcpBucketId;

	@Value("${gcp.dir.name}")
	private String gcpDirectoryName;

	public FileUploadDto uploadFile(MultipartFile multipartFile, String fileName, String contentType) {

		try {

			logger.info("Start file uploading process on GCS");
			File file = convertFile(multipartFile);
			byte[] fileData = FileUtils.readFileToByteArray(file);

			InputStream inputStream = new ClassPathResource(gcpConfigFile).getInputStream();

			StorageOptions options = StorageOptions.newBuilder().setProjectId(gcpProjectId).setCredentials(GoogleCredentials.fromStream(inputStream)).build();

			Storage storage = options.getService();
			Bucket bucket = storage.get(gcpBucketId, Storage.BucketGetOption.fields());
			
			// file must be rename, except file duplicate
			String fileExtexsion = FilenameUtils.getExtension(fileName);
			String generateFileName = UUID.randomUUID().toString().replace("-", "");
			generateFileName = generateFileName + "." + fileExtexsion;	
			Blob blob = bucket.create(gcpDirectoryName + "/" + generateFileName, fileData, contentType);
			if (blob != null) {
				logger.info("File successfully uploaded to GCS");
				if (file.delete()) {
					logger.info("Delete file in local after upload: {}", true);					
				}
				return new FileUploadDto(blob.getName(), blob.getMediaLink());
			}

		} catch (Exception e) {
			logger.error("An error occurred while uploading data. Exception: ", e);
			throw new RuntimeException("An error occurred while storing data to GCS");
		}
		throw new RuntimeException("An error occurred while storing data to GCS");
	}

	private File convertFile(MultipartFile file) {

		try {
			if (file.getOriginalFilename() == null) {
				throw new RuntimeException("Original file name is null");
			}
			File convertedFile = new File(file.getOriginalFilename());
			// save file in project
			FileOutputStream outputStream = new FileOutputStream(convertedFile);
			outputStream.write(file.getBytes());
			outputStream.close();
			logger.debug("Converting multipart file : {}", convertedFile);
			return convertedFile;
		} catch (Exception e) {
			throw new RuntimeException("An error has occurred while converting the file");
		}
	}
}
