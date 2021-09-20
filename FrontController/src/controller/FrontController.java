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
		//System.out.println("Ȯ��");
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
		// ������� ��û��(�׼�) �и�(�м�)�ϴ� �޼��� => control�� �����ϴ� ����
		////// String action = request.getParameter("action"); => if(action.equals("main"{
		// �������� ����ڿ��� ó���� �����͸� ������־����
		
		
		String uri = request.getRequestURI();
		// System.out.println("uri: "+uri);
		String cp = request.getContextPath(); 
		// System.out.println("cp: "+cp); 			==> /FrontController
		String action = uri.substring(cp.length());
		// System.out.println("action: "+action);   ==> /main.do

		ActionForward forward =null ;

		// control�� �����ϴ� ����
		////// if(action.equals("main"){
		if (action.equals("/main.do")) {  //if (action.equals("/FrontController/main.do")) �̷��� �ص��ǳ�, ��Ʈ�н��� �ٲ���, ���������� ������
			System.out.println("�������� �̵� ");
			
			// ��Ʈ�ѷ����� �ٽ� ������ 
			forward = new MainAction().execute(request, response); 
			//����ڿ��� ����� ����������  (MainAction���� �� ������)
			
		} else if (action.equals("/insertmsg.do")) {
			System.out.println("�޼��� �߰�");
		} else {
			//���� �׼� ���� ��, ���� ������ �̵� (forward�� ���̱� ������)
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("NewFile1.jsp"); // error.jsp
		}
		
		// ����ڿ��� ó���� �����͸� ��� 
		if(forward.isRedirect()) { // �����̷�Ʈ���
			response.sendRedirect(forward.getPath());
		}else { // ������ ����̶�� 
			// pageContext.forward("main.jsp");
			// �ڵ���ó  : Ŭ���̾�Ʈ(������)�κ��� ���� ��û�� ó���ؼ� �ٽ� ����� ������ ���� == ��Ʋ�ѷ� ����
			// ������ �����Ǿ������� ����ó ��� (�ٽ� �������� �����ϱ� ����)
			RequestDispatcher dispatcher = request.getRequestDispatcher(forward.getPath());
			dispatcher.forward(request,response);
		}

	}

}










