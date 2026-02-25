package com.nhnacademy.helloservlet.listener;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.annotation.WebListener;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.nio.charset.StandardCharsets;

@WebListener
@Slf4j
public class ServletContextListener implements jakarta.servlet.ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext servletContext=sce.getServletContext();
        String counterFileName=servletContext.getInitParameter("counterFileName");
        String counterFilePath="/WEB_INF/classes/"+counterFileName;
        String realFilePath=servletContext.getRealPath(counterFilePath);

        log.error("path: {}", realFilePath);

        File target=new File(realFilePath);

        if(target.exists()) {
            try (FileInputStream fileInputStream=new FileInputStream(target);
                    InputStreamReader inputStreamReader=new InputStreamReader(fileInputStream, StandardCharsets.UTF_8);
                    BufferedReader reader=new BufferedReader(inputStreamReader);
                    ) {

                long c=Long.parseLong(reader.readLine());
                servletContext.setAttribute("counter",c);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        log.error("counter: {}", servletContext.getAttribute("counter"));
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ServletContext servletContext=sce.getServletContext();
        String counterFileName=servletContext.getInitParameter("counterFileName");
        String counterFilePath="/WEB_INF/classes/"+counterFileName;
        String realFilePath=servletContext.getRealPath(counterFilePath);

        try(FileOutputStream fileOutputStream=new FileOutputStream(realFilePath);
        OutputStreamWriter outputStreamWriter=new OutputStreamWriter(fileOutputStream, StandardCharsets.UTF_8);
        BufferedWriter writer=new BufferedWriter(outputStreamWriter);) {
            writer.write(String.valueOf(servletContext.getAttribute("counter")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("distroy counter: "+servletContext.getAttribute("counter"));
    }
}
