package com.gonghan.service;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import relatedWorks.Person;
import relatedWorks.Start;

/**
 * Servlet implementation class CoauthorsServlet
 */
public class CoauthorsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CoauthorsServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String first = request.getParameter("first");
		String second = request.getParameter("second");
		RequestDispatcher dispatcher = null;
		try {
			Person[] path = Start.path(first, second);
			String[] persons = new String[path.length];
			for (int i = 0; i < path.length; i++) {
				persons[i] = path[i].toString();
			}
			request.setAttribute("persons", persons);
			request.setAttribute("first", first);
			request.setAttribute("second", second);
			dispatcher = request.getRequestDispatcher("/authors.jsp");
			dispatcher.forward(request, response);
		} catch (Exception e) {
			response.sendRedirect("./error.jsp");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
