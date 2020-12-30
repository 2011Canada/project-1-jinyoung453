package com.revature.servlets;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.controllers.AuthController;
import com.revature.controllers.ErrorController;
import com.revature.controllers.UserController;
import com.revature.models.Credentials;
import com.revature.models.User;
import com.revature.controllers.ReimbController;
/**
 * Servlet implementation class FrontController
 */
public class FrontController extends HttpServlet {
	
	private AuthController authController = new AuthController();
	private ErrorController errorController = new ErrorController();
	private UserController userController = new UserController();
	private ReimbController reimbController = new ReimbController();
	
	protected void directControlRouter(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		String URI = req.getRequestURI().substring(req.getContextPath().length(), 
													req.getRequestURI().length());	
		String value = "";
		Pattern pattern = Pattern.compile("\\d+$");
		//System.out.println("req.getRequestURI() : " + req.getRequestURI());
		Matcher matcher = pattern.matcher(req.getRequestURI());
		boolean isMatch = matcher.find();
		//System.out.println("isMatch: " + isMatch);
		if(isMatch) {
			value = matcher.group(0);
		}
		if(isMatch) {
			if(req.getRequestURI().substring(req.getContextPath().length(), req.getRequestURI().length()-value.length()-1).equalsIgnoreCase("/reimb")) {
				switch (req.getMethod()) {
					case "GET":
						reimbController.findReimbursementByStatus(req, res, Integer.parseInt(value));
						break;
					case "POST":
						res.setStatus(400);	res.getWriter().write("Method Not Supported");	break;
					case "PUT":
						res.setStatus(400);	res.getWriter().write("Method Not Supported");	break;
					case "DELETE":
						res.setStatus(400); res.getWriter().write("Method Not Supported");	break;
					default:
						res.setStatus(400); res.getWriter().write("Method Not Supported");	break;
				}
			}
			else if(req.getRequestURI().substring(req.getContextPath().length(), req.getRequestURI().length()-value.length()-1).equalsIgnoreCase("/viewHistory")) {
				//System.out.println("/emp");
				switch (req.getMethod()) {
				case "GET":
					reimbController.findReimbursementByUserId(req, res, Integer.parseInt(value));
					break;
				case "POST":
					res.setStatus(400);	res.getWriter().write("Method Not Supported");	break;
				case "PUT":
					res.setStatus(400);	res.getWriter().write("Method Not Supported");	break;
				case "DELETE":
					res.setStatus(400); res.getWriter().write("Method Not Supported");	break;
				default:
					res.setStatus(400); res.getWriter().write("Method Not Supported");	break;
				}
			}
			else if(req.getRequestURI().substring(req.getContextPath().length(), req.getRequestURI().length()-2).equalsIgnoreCase("/approval")) {
				switch (req.getMethod()) {
				case "GET":
					res.setStatus(400);	res.getWriter().write("Method Not Supported");	break;
				case "POST":
					res.setStatus(400);	res.getWriter().write("Method Not Supported");	break;
				case "PUT":
					reimbController.approval(req, res, Integer.parseInt(value));
					break;
				case "DELETE":
					res.setStatus(400);	res.getWriter().write("Method Not Supported");	break;
				default:
					res.setStatus(400);	res.getWriter().write("Method Not Supported");	break;
				}
			}
			else if(req.getRequestURI().substring(req.getContextPath().length(), req.getRequestURI().length()-value.length()-1).equalsIgnoreCase("/allReimb")) {
				//System.out.println("/allReimb");
				switch (req.getMethod()) {
					case "GET":
						reimbController.findAllReimbursement(req, res, Integer.parseInt(value));
						break;
					case "POST":
						res.setStatus(400);	res.getWriter().write("Method Not Supported");	break;
					case "PUT":
						res.setStatus(400);	res.getWriter().write("Method Not Supported");	break;
					case "DELETE":
						res.setStatus(400);	res.getWriter().write("Method Not Supported");	break;
					default:
						res.setStatus(400);	res.getWriter().write("Method Not Supported");	break;
				}
			}
			else if(req.getRequestURI().substring(req.getContextPath().length(), req.getRequestURI().length()-value.length()-1).equalsIgnoreCase("/viewHistoryBy")) {
				switch (req.getMethod()) {
				case "GET":
					reimbController.findReimbursementByUserIdStatus(req, res, Integer.parseInt(value));
					break;
				case "POST":
					res.setStatus(400);	res.getWriter().write("Method Not Supported");	break;
				case "PUT":
					res.setStatus(400);	res.getWriter().write("Method Not Supported");	break;
				case "DELETE":
					res.setStatus(400);	res.getWriter().write("Method Not Supported");	break;
				default:
					res.setStatus(400);	res.getWriter().write("Method Not Supported");	break;
			}
			}
		}else {
			switch (URI) {
			case "/login":
				switch (req.getMethod()) {
					case "GET":
						res.setStatus(400);	res.getWriter().write("Method Not Supported");	break;
					case "POST":
						//System.out.println("login_post");
						authController.userLogin(req, res);
						break;
					case "PUT":
						res.setStatus(400);	res.getWriter().write("Method Not Supported");	break;
					case "DELETE":
						res.setStatus(400);	res.getWriter().write("Method Not Supported");	break;
					default:
						res.setStatus(400);	res.getWriter().write("Method Not Supported");	break;
				}
				break;
			case "/users": {
				switch (req.getMethod()) {
					case "GET":
						userController.findAllUsers(req, res);
						break;
					case "POST":
						res.setStatus(400);	res.getWriter().write("Method Not Supported");	break;
					case "PUT":
						res.setStatus(400);	res.getWriter().write("Method Not Supported");	break;
					case "DELETE":
						res.setStatus(400);	res.getWriter().write("Method Not Supported");	break;
					default:
						res.setStatus(400);	res.getWriter().write("Method Not Supported");	break;
				}
				break;
			}
//			case "/allReimb": {
//				switch (req.getMethod()) {
//					case "GET":
//						reimbController.findAllReimbursement(req, res);
//						break;
//					case "POST":
//						res.setStatus(400);	res.getWriter().write("Method Not Supported");	break;
//					case "PUT":
//						res.setStatus(400);	res.getWriter().write("Method Not Supported");	break;
//					case "DELETE":
//						res.setStatus(400);	res.getWriter().write("Method Not Supported");	break;
//					default:
//						res.setStatus(400);	res.getWriter().write("Method Not Supported");	break;
//				}
//				break;
//			}
			case "/register" : {
				switch (req.getMethod()) {
				case "GET":
					res.setStatus(400);	res.getWriter().write("Method Not Supported");	break;
				case "POST":
					res.setStatus(400);	res.getWriter().write("Method Not Supported");	break;
				case "PUT":
					userController.createAccount(req, res);
//					ObjectMapper om = new ObjectMapper();
//					User user = om.readValue(req.getInputStream(), User.class);
//					System.out.println("user: " + user);
					break;
				case "DELETE":
					res.setStatus(400);	res.getWriter().write("Method Not Supported");	break;
				default:
					res.setStatus(400);	res.getWriter().write("Method Not Supported");	break;
				}
				break;
			}
			case "/approver": {
				switch (req.getMethod()) {
					case "GET":
						userController.findAllApprovers(req, res);
						break;
					case "POST":
						res.setStatus(400);	res.getWriter().write("Method Not Supported");	break;
					case "PUT":
						res.setStatus(400);	res.getWriter().write("Method Not Supported");	break;
					case "DELETE":
						res.setStatus(400);	res.getWriter().write("Method Not Supported");	break;
					default:
						res.setStatus(400);	res.getWriter().write("Method Not Supported");	break;
				}
				break;
			}
			case "/newReimb" : {
				switch (req.getMethod()) {
				case "GET":
					res.setStatus(400);	res.getWriter().write("Method Not Supported");	break;
				case "POST":
					res.setStatus(400);	res.getWriter().write("Method Not Supported");	break;
				case "PUT":
					reimbController.createReimb(req, res);
					break;
				case "DELETE":
					res.setStatus(400);	res.getWriter().write("Method Not Supported");	break;
				default:
					res.setStatus(400);	res.getWriter().write("Method Not Supported");	break;
				}
				break;
			}
			default:
				res.setStatus(404);
				res.getWriter().write("No Such Resource");
				break;
			}
		}
		
	}
	
	protected void directControl(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//to handle all internal errors/exceptions
		try {
			directControlRouter(request, response);
		}catch (Throwable t) {
			errorController.handle(request, response, t);//go to the error controller
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		directControl(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		directControl(request, response);
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		directControl(request, response);
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

}