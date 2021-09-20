package controller;

//페이지 전달방식, 경로
public class ActionForward {

	private boolean redirect;
	// true일때 대체적으로 전달할 정보가 없다  -> 그러나 contorl.jsp?action=main 으로 가능하다
	// false = 전달한 정보가 많다.
	private String path;

	public boolean isRedirect() {
		return redirect;
	}

	public void setRedirect(boolean redirect) {
		this.redirect = redirect;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	
	
	
}
