package com.shitou6.controller;

import com.shitou6.service.Start_now;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class Init extends HttpServlet {
    Start_now a=new Start_now();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("开始初始化操作");
        System.out.println("得到一个get请求");
        resp.setCharacterEncoding("UTF-8");
        resp.setHeader("Content-Type", "text/html;charset=UTF-8");
        PrintWriter printWriter = resp.getWriter();
        printWriter.println("立即开始初始化");

        a.newInit();
    }
}
