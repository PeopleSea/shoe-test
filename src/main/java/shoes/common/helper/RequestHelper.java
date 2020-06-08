package shoes.common.helper;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Component
public class RequestHelper {
	private static final Logger LOGGER = LoggerFactory.getLogger(RequestHelper.class);

	public static String getLanguage() {
		return getRequest().getHeader("language");
	}

	public static String getMerchantCode() {
		return getRequest().getHeader("Merchant");
	}

	public static String getToken() {
		return getRequest().getHeader("Authorization");
	}

	public static String getSystemType() {
		String consoleCode = getRequest().getHeader("Console");
		return consoleCode.equalsIgnoreCase("AMC") ? "1" : (consoleCode.equalsIgnoreCase("BMC") ? "2" : "3");
	}

	public static String getConsoleCode() {
		return getRequest().getHeader("Console");
	}

	public static HttpServletRequest getRequest() {
		return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
	}

	public static Map<String, String> getMapFromUrlFormat(String query) throws UnsupportedEncodingException {
		Map<String, String> queryPairs = new LinkedHashMap<>();
		String[] pairs = query.split("&");
		for (String pair : pairs) {
			int idx = pair.indexOf("=");
			if (idx >= 0)
				queryPairs.put(URLDecoder.decode(pair.substring(0, idx), "UTF-8"),
						URLDecoder.decode(pair.substring(idx + 1), "UTF-8"));
		}
		return queryPairs;
	}

	public static String getIp() {
		HttpServletRequest request = getRequest();
		String ip = request.getHeader("X-Forwarded-For");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
			ip = request.getHeader("Proxy-Client-IP");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
			ip = request.getHeader("WL-Proxy-Client-IP");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
			ip = request.getHeader("HTTP_CLIENT_IP");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
			ip = request.getHeader("X-Real-IP");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
			ip = request.getRemoteAddr();
		if (ip.equals("127.0.0.1") || ip.startsWith("fe80") || ip.equals("0:0:0:0:0:0:0:1"))
			ip = request.getHeader("X-Real-IP");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
			ip = "127.0.0.1";
		try {
			String[] realIP = ip.split(",");
			ip = realIP[0];
		} catch (Exception e) {
			LOGGER.error("Cant get real ip. forward IP : {}", ip, e);
			return "127.0.0.1";
		}
		return ip;
	}
}