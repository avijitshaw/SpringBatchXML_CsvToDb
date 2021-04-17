package com.springsecurity.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.apache.commons.io.IOUtils;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.configuration.JobRegistry;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.NoSuchJobException;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class FileUploadController {

	@Autowired
	JobLauncher jobLauncher;

	@Autowired
	JobRegistry jobRegistry;

	@PostMapping("/fileupload")
	public String fileUpload(@RequestParam("file") MultipartFile file) throws IOException {
		System.out.println("--------Inside Controller ------");
		String filePath = "D:\\Eclipse +Netbeans Projects\\TempFolder\\";
		File fileToImport = new File(filePath + file.getOriginalFilename());
		System.out.println(" :::::::::::::::::::  File name is :::::::::::::::::::" + fileToImport);
		OutputStream outputStream = new FileOutputStream(fileToImport);
		IOUtils.copy(file.getInputStream(), outputStream);
		outputStream.flush();

		try {
			JobExecution jobexecution = jobLauncher.run(jobRegistry.getJob("loanUpload"),
					new JobParametersBuilder().addString("file", fileToImport.getAbsolutePath()).toJobParameters());
			System.out.println(jobexecution);

		} catch (JobExecutionAlreadyRunningException | JobRestartException | JobInstanceAlreadyCompleteException
				| JobParametersInvalidException | NoSuchJobException e) {
			e.printStackTrace();
		}
		return "success";
	}
}
