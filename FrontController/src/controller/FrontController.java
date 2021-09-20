package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sun.rmi.server.Dispatcher;

/**
 * Servlet implementation class FrontController
 */
@WebServlet("/FrontController")
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FrontController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//System.out.println("확인");
		doAction(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
		doAction(request, response);
	}

	private void doAction(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 사용자의 요청을(액션) 분리(분석)하는 메서드 => control을 매핑하는 역할
		////// String action = request.getParameter("action"); => if(action.equals("main"{
		// 마지막에 사용자에게 처리된 데이터를 출력해주어야함
		
		
		String uri = request.getRequestURI();
		// System.out.println("uri: "+uri);
		String cp = request.getContextPath(); 
		// System.out.println("cp: "+cp); 			==> /FrontController
		String action = uri.substring(cp.length());
		// System.out.println("action: "+action);   ==> /main.do

		ActionForward forward =null ;

		// control을 매핑하는 역할
		////// if(action.equals("main"){
		if (action.equals("/main.do")) {  //if (action.equals("/FrontController/main.do")) 이렇게 해도되나, 루트패스가 바뀔경우, 유지보수성 안좋음
			System.out.println("메인으로 이동 ");
			
			// 컨트롤러에게 다시 보내줌 
			forward = new MainAction().execute(request, response); 
			//사용자에게 결과를 출력해줘야함  (MainAction에서 다 셋해줌)
			
		} else if (action.equals("/insertmsg.do")) {
			System.out.println("메세지 추가");
		} else {
			//없는 액션 실행 시, 에러 페이지 이동 (forward가 널이기 때문에)
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("NewFile1.jsp"); // error.jsp
		}
		
		// 사용자에게 처리된 데이터를 출력 
		if(forward.isRedirect()) { // 리다이렉트라면
			response.sendRedirect(forward.getPath());
		}else { // 포워딩 방식이라면 
			// pageContext.forward("main.jsp");
			// ★디스패처  : 클라이언트(브라우저)로부터 들어온 요청을 처리해서 다시 결과를 보내는 역할 == 컨틀롤러 역할
			// 데이터 유지되야함으로 디스패처 사용 (다시 메인으로 연결하기 위해)
			RequestDispatcher dispatcher = request.getRequestDispatcher(forward.getPath());
			dispatcher.forward(request,response);
		}

	}

}










