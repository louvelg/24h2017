package com.akelio.codingame.common.view;

import javax.annotation.PostConstruct;
import javax.faces.application.ViewExpiredException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.servlet.http.HttpSession;
import org.springframework.web.context.support.WebApplicationContextUtils;
import com.akelio.codingame.base.BaseView;

@RequestScoped
@ManagedBean(name = "headerView")
public class HeaderView extends BaseView {

	@PostConstruct
	public void init() {
		WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext()).getAutowireCapableBeanFactory().autowireBean(this);
		HttpSession session = (HttpSession) getFacesContext().getExternalContext().getSession(false);
		if((session==null || getVisit()==null) && !getFacesContext().getPartialViewContext().isAjaxRequest()){
			throw new ViewExpiredException();
		}
	}
}
