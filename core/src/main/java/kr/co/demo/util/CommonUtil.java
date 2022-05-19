package kr.co.demo.util;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Array;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class CommonUtil {
	public static String getRealIp() {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
		return getRealIp(request);
	}

	public static String getRealIp(HttpServletRequest request) {
		String ip = request.getHeader("X-Forwarded-For");
		if(StringUtils.isBlank(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if(StringUtils.isBlank(ip)) {
			ip = request.getHeader("WL_Proxy-Client-IP");
		}
		if(StringUtils.isBlank(ip)) {
			ip = request.getHeader("HTTP_CLIENT_IP");
		}
		if(StringUtils.isBlank(ip)) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if(StringUtils.isBlank(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

	public static String randomCode() {
		Random rnd = new Random();
		StringBuffer buf = new StringBuffer();

		for(int i=0; i<5; i++) {
			if(rnd.nextBoolean()) {
				buf.append((char)((int)(rnd.nextInt(26))+65));
			} else {
				buf.append((rnd.nextInt(5)));
			}
		}

		return buf.toString();
	}

	public static boolean isNullEmpty (String str) {
		if (str == null || str == "") return true;
		return false;
	}

	public static boolean isEmpty(Object obj){
		boolean result = false;
		if( obj instanceof String ) result = obj==null || "".equals(obj.toString().trim());
		else if( obj instanceof List) result = obj==null || ((List)obj).isEmpty();
		else if( obj instanceof Map) result = obj==null || ((Map)obj).isEmpty();
		else if( obj instanceof Object[] ) result = obj==null || Array.getLength(obj)==0;
		else result = obj==null;

		return result;
	}

	public static boolean isNotEmpty(String s){
		return !isEmpty(s);
	}
}
