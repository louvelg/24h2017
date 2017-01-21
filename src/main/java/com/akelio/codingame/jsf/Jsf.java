package com.akelio.codingame.jsf;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;

public class Jsf {

	public static void info(String message) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, message, ""));
	}

	public static void info(String clientId, String message) {
		FacesContext.getCurrentInstance().addMessage(clientId, new FacesMessage(FacesMessage.SEVERITY_INFO, message, ""));
	}

	public static void growl(String message) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, message, ""));
	}

	public static void growl(String clientId, String message) {
		FacesContext.getCurrentInstance().addMessage(clientId, new FacesMessage(FacesMessage.SEVERITY_INFO, message, ""));
	}

	public static void warn(String message) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, message, ""));
	}

	public static void warn(String clientId, String message) {
		FacesContext.getCurrentInstance().addMessage(clientId, new FacesMessage(FacesMessage.SEVERITY_WARN, message, ""));
	}

	public static void error(String message) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, message, ""));
	}

	public static void error(String clientId, String message) {
		FacesContext.getCurrentInstance().addMessage(clientId, new FacesMessage(FacesMessage.SEVERITY_ERROR, message, ""));
	}

	public static Object getFromFlash(String key) {
		return FacesContext.getCurrentInstance().getExternalContext().getFlash().get(key);
	}

	public static void putInFlash(String key, Object obj) {
		FacesContext.getCurrentInstance().getExternalContext().getFlash().put(key, obj);
	}

	public static Object getFromSession(String key) {
		return FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(key);
	}

	public static void putInSession(String key, Object obj) {
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(key, obj);
	}

	public static void removeInSession(String key) {
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove(key);
	}

	public static HttpServletRequest getHttpServletRequest() {
		return (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
	}

	public static void forwardFaces(FacesContext fc, String url) {
		HttpServletResponse response = (HttpServletResponse) fc.getExternalContext().getResponse();
		try {
			response.sendRedirect(fc.getExternalContext().getRequestContextPath() + fc.getExternalContext().getRequestServletPath() + url);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void redirect(String url) throws IOException {
		redirect(FacesContext.getCurrentInstance(), url);
	}

	public static void redirectLogin(String url) throws IOException {
		redirectLogin(FacesContext.getCurrentInstance(), url);
	}

	public static void redirect(FacesContext context, String url) throws IOException {
		String normalizedURL = normalizeRedirectURL(context, url);
		ExternalContext externalContext = context.getExternalContext();
		externalContext.getFlash().setRedirect(true);
		externalContext.redirect(normalizedURL);
	}

	public static void redirectLogin(FacesContext context, String url) throws IOException {
		String normalizedURL = normalizeRedirectURLLogin(context, url);
		context.setViewRoot(context.getApplication().getViewHandler().createView(context, normalizedURL));
		context.getExternalContext().redirect(normalizedURL);
		context.getPartialViewContext().setRenderAll(true);
		context.getPartialViewContext().setPartialRequest(false);
		context.renderResponse();
	}

	private static String normalizeRedirectURLLogin(FacesContext context, String url) {
		if (!url.startsWith("http://") && !url.startsWith("https://") && !url.startsWith("/")) {
			url = getRequestContextPath(context) + "/" + url;
		}
		if (url.contains("faces-redirect") || url.contains("logout")) { return url; }
		return url + "?faces-redirect=true";
	}

	private static String normalizeRedirectURL(FacesContext context, String url) {
		if (!url.startsWith("http://") && !url.startsWith("https://") && !url.startsWith("/")) {
			url = getRequestContextPath(context) + "/" + url;
		}
		return url;
	}

	public static String getRequestContextPath(FacesContext context) {
		return context.getExternalContext().getRequestContextPath();
	}

	public static String getRemoteAdr() {
		return ((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest()).getRemoteAddr();
	}

	public static String getRemoteAdrToLog() {
		return StringUtils.center(getRemoteAdr(), 18);
	}

	public static void downloadString(String stringSource, FacesContext context, String mimeType, String filename) {
		OutputStream outputStream = null;
		try {
			HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();
			response.setContentType(mimeType);
			response.setHeader("Content-Disposition", "attachment; filename=\"" + filename + "\"");
			response.setContentLength(stringSource.getBytes().length);
			outputStream = response.getOutputStream();
			outputStream.write(stringSource.getBytes());
			outputStream.flush();
			outputStream.close();
			outputStream = null;// no close twice
		} catch (IOException e) {
			e.printStackTrace();
		}
		context.responseComplete();
	}

	public static void downloadFile(String pathFile, FacesContext context, String mimeType, String filename, boolean inline) {
		InputStream inputStream = null;
		OutputStream outputStream = null;
		File file = new File(pathFile);

		try {
			inputStream = new FileInputStream(file.getAbsolutePath());
			HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();
			response.setContentType(mimeType);
			if (inline) {
				response.setHeader("Content-Disposition", "inline; filename=\"" + filename + "\"");
			} else {
				response.setHeader("Content-Disposition", "attachment; filename=\"" + filename + "\"");
			}
			response.setContentLength((int) file.length());
			outputStream = response.getOutputStream();

			byte[] buffer = new byte[256];
			int numRead;
			while ((numRead = inputStream.read(buffer)) != -1) {
				outputStream.write(buffer, 0, numRead);
			}

			buffer = null;
			inputStream.close();
			inputStream = null;// no close twice

			outputStream.flush();
			outputStream.close();
			outputStream = null;// no close twice
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		context.responseComplete();
	}
}
