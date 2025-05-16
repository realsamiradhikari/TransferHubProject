package com.TransferHubProject.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class ContactUsController
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/contactus" })
public class ContactUsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ContactUsController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * Handles HTTP GET requests.
	 * Takes the request and forwards it to the ContactUs.jsp file for display.
	 * 
	 * @param request The HttpServletRequest object representing the request.
	 * @param response The HttpServletResponse object representing the response.
	 * @throws ServletException If the servlet encounters a ServletException.
	 * @throws IOException If an I/O error occurs while processing the request.
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Forward the request to ContactUs.jsp page to show the contact us page
		request.getRequestDispatcher("WEB-INF/pages/ContactUs.jsp").forward(request, response);
	}

	/**
	 * Handles HTTP POST requests.
	 * Takes the request and passes it to doGet method for processing.
	 * 
	 * @param request The HttpServletRequest object representing the request.
	 * @param response The HttpServletResponse object representing the response.
	 * @throws ServletException If the servlet encounters a ServletException.
	 * @throws IOException If an I/O error occurs while processing the request.
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// Pass the POST request to doGet to handle it
		doGet(request, response);
	}
}