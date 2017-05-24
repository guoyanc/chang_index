package com.chang.action;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.chang.util.CommonUtil;

public class HttpAction {

	private static Logger logger = LogManager.getLogger(HttpAction.class.getName());

	public String getMediaSearchURL(String siteName) {
		logger.info("the siteName is: " + siteName);
		String mediaSearchURL = "";
		String result = "";
		BufferedReader br = null;
		HttpGet httpGet = new HttpGet(siteName);
		CloseableHttpClient httpClient = HttpClients.createDefault();
		httpGet.setHeader("Accept-Charset",
				"Mozilla/5.0 (Windows; U; Windows NT 5.1; zh-CN; rv:1.9.1.2");
		// 发送请求
		try {
			HttpResponse response = httpClient.execute(httpGet);
			logger.info("URI：" + httpGet.getURI());
			// 获取状态码
			logger.info("状态码："
					+ response.getStatusLine().getStatusCode());
			logger.info("头部信息："
					+ httpGet.getFirstHeader("Accept-Charset").getValue());

			// 获取所有的请求头信息
			Header headers[] = response.getAllHeaders();
			int ii = 0;
			while (ii < headers.length) {
				System.out.println(headers[ii].getName() + ":"
						+ headers[ii].getValue());
				++ii;
			}
			// 抓取网页内容
			HttpEntity entity = response.getEntity();
			br = new BufferedReader(new InputStreamReader(
					entity.getContent(), "UTF-8"));
			String line;
			while ((line = br.readLine()) != null) {
				result += line;
			}
			br.close();
			httpGet.abort();
		} catch (ClientProtocolException e) {
			logger.error(e.getMessage());
		} catch (IOException e) {
			logger.error(e.getMessage());
		} finally {
			try {
				if(br != null) br.close();
				if(httpClient != null) httpClient.close();
			} catch (Exception e) {
				logger.error(e.getMessage());
			}
		}
		String tmpUrl = CommonUtil.getUrl(result, "[电影]", "</a>");
		logger.info("result:" + tmpUrl);
		logger.info("url : " + CommonUtil.getUrl(tmpUrl, "\"", "\""));
		return mediaSearchURL;
	}
}
