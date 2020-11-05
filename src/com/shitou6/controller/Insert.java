package com.shitou6.controller;

import com.shitou6.service.Start_now;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class Insert  extends HttpServlet{
        Start_now a=new Start_now();

        @Override
        protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            System.out.println("新增号段为："+req.getParameter("number"));
            System.out.println("运营商为："+req.getParameter("operator"));
            System.out.println("开始执行新增");
            resp.setCharacterEncoding("UTF-8");
            resp.setHeader("Content-Type", "text/html;charset=UTF-8");
            PrintWriter printWriter = resp.getWriter();
            printWriter.println("新增号段为："+req.getParameter("number"));
            printWriter.println("运营商为："+req.getParameter("operator"));
            a.insertNumber(Integer.valueOf(req.getParameter("operator")),req.getParameter("number"));

        }
    }

