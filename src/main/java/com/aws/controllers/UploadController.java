package com.aws.controllers;



import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.model.PutObjectResult;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.aws.s3.S3Wrapper;
import com.ysyt.bean.Uploads;

@RestController
@RequestMapping("/api/aws/s3")
public class UploadController {

	@Autowired
	private S3Wrapper s3Wrapper;

	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public List<Uploads> upload(@RequestParam("image") MultipartFile[] multipartFiles) {
		return s3Wrapper.upload(multipartFiles);
	}
	
	@RequestMapping(value = "/upload/single", method = RequestMethod.POST)
	public PutObjectResult uploadSingle(@RequestParam("image") MultipartFile[] multipartFile) throws IOException {
		
		for (MultipartFile multipart : multipartFile){
			return s3Wrapper.upload(multipart.getInputStream(),multipart.getOriginalFilename());

		}
		return null;
	}

	@RequestMapping(value = "/download", method = RequestMethod.GET)
	public ResponseEntity<byte[]> download(@RequestParam String key) throws IOException {
		return s3Wrapper.download(key);
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public List<S3ObjectSummary> list() throws IOException {
		return s3Wrapper.list();
	}
}
