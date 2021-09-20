package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Action {
		// ActionForward : 전달할 페이지
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException;

	// request, response 인자로 받아서 페이지(페이지 전달방식, 경로)를 리턴함

}
