package com.test.one;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONObject;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.asserts.Assertion;

import com.test.Data.ExcelData;
import com.test.common.HttpUtils;


public class Test {

	public Assertion assertion;
	@BeforeClass
	public void beforeClass() {
		assertion = new Assertion();
	}

	@BeforeMethod
	public void runBeforeMethod() {
		// 打开httpclient,相当于打开一个浏览器
		HttpUtils.OpenHttpClient();
	}
	@AfterMethod
	public void runAfterMethod() {
		// 打开httpclient,相当于打开一个浏览器
		HttpUtils.CloseHttpClient();
	}
	
  @org.testng.annotations.Test(dataProvider="AttentionTrainingCamp")
  public void f(Map<String, String> map) throws ClientProtocolException, IOException {
	  
	  	String loginName = map.get("LoginName");
		String password = map.get("PassWord");
		System.out.println(loginName);
		System.out.println(password);
	  	String loginUrl = "http://wc.surgecapital.cn/Org/PCUserLogin.do?u="+loginName+"&p="+password+"&groupId=1";
	  	JSONObject json = HttpUtils.visitUrl(loginUrl);
	  	boolean success = json.getBoolean("success");
	    String enterTrainningUrl = "http://wc.surgecapital.cn/Training/enterTrainingCamp.do?roomid=1002";
		System.out.println(enterTrainningUrl);
		JSONObject enterObj = HttpUtils.visitUrl(enterTrainningUrl);
		System.out.println(enterObj.toString());
		boolean success2 = enterObj.getBoolean("success");
		assertion.assertTrue(success);
  }
  @DataProvider(name = "AttentionTrainingCamp")
	protected Object[][] dataInfo(Method m) throws IOException {

		Object[][] myObj = null;
		String sheetName = m.getName();
		String file = this.getClass().getSimpleName() + ".xlsx";
		String fileName = com.test.Data.Constant.CaseConfigDir + file;
		myObj = ExcelData.getData(fileName, sheetName);
		return myObj;
	}
  
  
}
