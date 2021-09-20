package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.member.MemberDAO;
import model.member.MemberVO;
import model.msg.MessageDAO;
import model.msg.MsgSet;



public class MainAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		ActionForward forward = new ActionForward(); // 20��° �� ActionForward �������ִϱ�, ��ü ���� 
		
		
		String mcntt = request.getParameter("mcnt");
		int mcnt = 1; 
		if (mcntt != null) {
			mcnt = Integer.parseInt(mcntt);
		}
		
		String selUser = request.getParameter("selUser");
		
		MessageDAO mDAO = new MessageDAO();
		MemberDAO uDAO = new MemberDAO();
		
		ArrayList<MsgSet> datas = mDAO.selectAll(selUser, mcnt);
		ArrayList<MemberVO> newUsers = uDAO.selectAll();

		request.setAttribute("datas", datas);
		request.setAttribute("newUsers", newUsers);
		request.setAttribute("selUser", selUser);
		request.setAttribute("mcnt", mcnt);

		
		forward.setRedirect(false); // request,setAttribute�Ȱ� ������ (������ ������ ������) forward��� ���! 
		forward.setPath("main.jsp");  
		
		return forward;
	}
	
}


/*String action = request.getParameter("action");
	String url = "control.jsp?action=main";

	//����¡ ���� (������������ �ٽ�!)
	String mcntt = request.getParameter("mcnt");
	int mcnt = 0; // ó���� �� 0�� �����ֱ�
	if (mcntt != null) {
		mcnt = Integer.parseInt(mcntt);
	}
	url = url + "&mcnt=" + mcnt;
	String selUser = request.getParameter("selUser");
	if (selUser != null) {
		url = url + "&selUser=" + selUser; //selUser : ���ۺ��� / �˻��� ����� ���� 
	}
	if (action.equals("main")) {
		ArrayList<MsgSet> datas = mDAO.selectAll(selUser, mcnt);
		ArrayList<MemberVO> newUsers = uDAO.selectAll();

		request.setAttribute("datas", datas);
		request.setAttribute("newUsers", newUsers);
		request.setAttribute("selUser", selUser);
		request.setAttribute("mcnt", mcnt);

		pageContext.forward("main.jsp");

	}*/