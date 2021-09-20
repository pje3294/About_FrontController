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
		
		ActionForward forward = new ActionForward(); // 20번째 줄 ActionForward 리턴해주니깐, 객체 생성 
		
		
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

		
		forward.setRedirect(false); // request,setAttribute된게 많으니 (전달할 정보가 많으니) forward방식 사용! 
		forward.setPath("main.jsp");  
		
		return forward;
	}
	
}


/*String action = request.getParameter("action");
	String url = "control.jsp?action=main";

	//페이징 위해 (페이지유지의 핵심!)
	String mcntt = request.getParameter("mcnt");
	int mcnt = 0; // 처음에 글 0개 보여주기
	if (mcntt != null) {
		mcnt = Integer.parseInt(mcntt);
	}
	url = url + "&mcnt=" + mcnt;
	String selUser = request.getParameter("selUser");
	if (selUser != null) {
		url = url + "&selUser=" + selUser; //selUser : 내글보기 / 검색한 사용자 보기 
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