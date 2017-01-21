package com.akelio.codingame.base;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.support.WebApplicationContextUtils;
import com.akelio.base.message.MessageInterface;
import com.akelio.codingame.app.user.entity.User;
import com.akelio.codingame.common.view.VisitBean;
import com.akelio.codingame.util.Constants;

public abstract class BaseView implements MessageInterface, Serializable {

	private static final Logger	logger	= LoggerFactory.getLogger(BaseView.class);

	public void initBean() {
		WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext()).getAutowireCapableBeanFactory().autowireBean(this);
	}

	private void readObject(ObjectInputStream ois) throws ClassNotFoundException, IOException {
		ois.defaultReadObject();
		if (getServletContext() != null) {
			WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext()).getAutowireCapableBeanFactory().autowireBean(this);
		}
	}

	public FacesContext getFacesContext() {
		return FacesContext.getCurrentInstance();
	}

	public ServletContext getServletContext() {
		if (getFacesContext() == null) { return null; }
		return (ServletContext) getFacesContext().getExternalContext().getContext();
	}

	public String getParam(String param) {
		return getFacesContext().getExternalContext().getRequestParameterMap().get(param);
	}
	
	public int getParamAsInt(String param) {
		return Integer.parseInt(getParam(param));
	}

	public Logger getLogger() {
		return logger;
	}
	
	protected static VisitBean getVisitBean() {
		if (FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("visit") != null) { return (VisitBean) FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
				.get("visit"); }
		return null;
	}
	
	public User getUser() {
		VisitBean visit = getVisitBean();
		if (visit != null) { return visit.getUser(); }
		User user = new User();
		return user;
	}

	public VisitBean getVisit() {
		return getVisitBean();
	}

}
