package com.nhnacademy.helloservlet.file;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;

@MultipartConfig(
        fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024*1024*10,
        maxRequestSize = 1024*1024*100,
        location = "/Users/chosun-nhn29/IdeaProjects2/hello-servlet/file/upload"
)
@WebServlet(name="fileUploadServlet", urlPatterns = "/file/fileUpload")
@Slf4j
public class FileUploadServlet extends HttpServlet {
    private static final String CONTENT_DISPOSITION ="Content-Disposition";
    private  static final String UPLOAD_DIR="/Users/chosun-nhn29/IdeaProjects2/hello-servlet/file/upload";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            for(Part part: req.getParts()) {
                String contentDisposition=part.getHeader(CONTENT_DISPOSITION);
                if(contentDisposition.contains("filename=")) {
                    String fileName= extractFileName(contentDisposition);
                    if(part.getSize()>0) {
                        part.write(UPLOAD_DIR+ File.separator+fileName);
                        part.delete();
                    }
                } else {
                    String formValue=req.getParameter(part.getName());
                    log.info("{}={}", part.getName(), formValue);
                }
            }
            resp.sendRedirect("/");
        } catch (IOException e) {
            log.error("File upload failed", e);
        }
    }

    private String extractFileName(String contentDisposition) {
        log.info("content-Disposition: {}", contentDisposition);
        for(String token: contentDisposition.split(";")) {
            if(token.trim().startsWith("filename")) {
                return token.substring(token.indexOf("=")+2, token.length()-1);
            }
        }
        return null;
    }
}
