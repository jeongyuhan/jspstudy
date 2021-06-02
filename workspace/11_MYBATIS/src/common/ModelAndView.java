package common;

public class ModelAndView {

	private String view; // 응답 View (jsp 파일명)
	private boolean isRedirect; // 이동방식 (Redirect : true, Forward : false)
	
	public ModelAndView() {}

	public ModelAndView(String view, boolean isRedirect) {
		super();
		this.view = view;
		this.isRedirect = isRedirect;
	}

	public String getView() {
		return view;
	}

	public void setView(String view) {
		this.view = view;
	}

	public boolean isRedirect() {
		return isRedirect;
	}

	public void setRedirect(boolean isRedirect) {
		this.isRedirect = isRedirect;
	}
	
}
