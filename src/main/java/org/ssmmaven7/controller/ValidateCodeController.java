package org.ssmmaven7.controller;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import util.ValidateCode;
@Controller
public class ValidateCodeController{
	
	private ValidateCode validateCode;
	@Autowired
	private HttpServletRequest request ;
	@Autowired
	private HttpServletResponse response;
	
	@RequestMapping(value = "createValidateCode.do")
	public void createValidateCode() {
		// TODO Auto-generated method stub
		validateCode = new ValidateCode(80, 20, 4);
		String vcode = validateCode.getCode();
		HttpSession session = request.getSession();
		session.setAttribute("vcode", vcode); 
		try {
			//将服务器产生的验证码以流的形式写给客户端
			validateCode.write(response.getOutputStream());
		} catch (Exception e) {
			// TODO: handle exceptionzz
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "checkValidateCode.do")
	public void checkValidateCode() {
		// TODO Auto-generated method stub
		String code = request.getParameter("code");
		HttpSession session = request.getSession();
		String vcode = session.getAttribute("vcode").toString();
		PrintWriter out = null ;
		try {
			out = response.getWriter();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		if(out != null){
			if(!code.equalsIgnoreCase(vcode)){
				out.write("check success");
			}else{
				out.write("check fail");
			}
		}
	}

}
