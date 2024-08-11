/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package FWRP_Servlet;

import DataAccess.*;
import FWRP_Servlet.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.SQLException;
import static org.mockito.Mockito.*;


/**
 *
 * @author agsam
 */

public class RegisterServletTest {

    private RegisterServlet servlet;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private HttpSession session;
    private StringWriter responseWriter;
    private ConsumerDAO consumerDAO;
    private RetailerDAO retailerDAO;
    private CharitableDAO charitableDAO;

    @BeforeEach
            // setUp method must be executed before each test method
    public void setUp() throws Exception {
        servlet = new RegisterServlet();
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        session = mock(HttpSession.class);
        consumerDAO = mock(ConsumerDAO.class);
        retailerDAO = mock(RetailerDAO.class);
        charitableDAO = mock(CharitableDAO.class);
        responseWriter = new StringWriter();

        when(request.getSession()).thenReturn(session);
        when(response.getWriter()).thenReturn(new PrintWriter(responseWriter));
    }

    @Test
    public void testDoPost_SuccessfulConsumerRegistration() throws Exception {
        //  parameters
        when(request.getParameter("username")).thenReturn("consumer2");
        when(request.getParameter("email")).thenReturn("consumer2@email.com");
        when(request.getParameter("password")).thenReturn("consumer2pass");
        when(request.getParameter("user_type")).thenReturn("consumer");
        when(request.getParameter("description")).thenReturn("");

        // inject simulation in servlet
        RegisterServlet servletWithMockDAO = new RegisterServlet() {
            protected ConsumerDAO getConsumerDAO() {
                return consumerDAO;
            }
        };

        // run doPost
        servletWithMockDAO.doPost(request, response);

        // check addConsumer call
        verify(consumerDAO, times(1)).addConsumer(any(ConsumerDTO.class));

        // check registration redirection
        verify(response, times(1)).sendRedirect("RegisterSuccessful.jsp");
    }

    @Test
    public void testDoPost_FailedRetailerRegistration() throws Exception {
        // simulated parameters
        when(request.getParameter("username")).thenReturn("retailer1");
        when(request.getParameter("email")).thenReturn("retailer1@email.com");
        when(request.getParameter("password")).thenReturn("retailer1pass");
        when(request.getParameter("user_type")).thenReturn("retailer");
        when(request.getParameter("description")).thenReturn("retailer1 description");

        // SQLException simulation
        doThrow(new SQLException("Database error")).when(retailerDAO).addRetailer(any(RetailerDTO.class));

        // inject simulation in servlet
        RegisterServlet servletWithMockDAO = new RegisterServlet() {
            protected RetailerDAO getRetailerDAO() {
                return retailerDAO;
            }
        };

        // run doPost
        servletWithMockDAO.doPost(request, response);

        // check redirection
        verify(response, times(1)).sendRedirect("Failed.jsp");
    }

    @Test
    public void testDoPost_SuccessfulCharitableRegistration() throws Exception {
        // simulated parameters
        when(request.getParameter("username")).thenReturn("charitable1");
        when(request.getParameter("email")).thenReturn("charitable1@email.com");
        when(request.getParameter("password")).thenReturn("charitable1pass");
        when(request.getParameter("user_type")).thenReturn("charitable_organization");
        when(request.getParameter("description")).thenReturn("charitable1 description");

        // inject simulation in servlet
        RegisterServlet servletWithMockDAO = new RegisterServlet() {
            protected CharitableDAO getCharitableDAO() {
                return charitableDAO;
            }
        };

        // run doPost
        servletWithMockDAO.doPost(request, response);

        // check addCharitable call
        verify(charitableDAO, times(1)).addCharitable(any(CharitableDTO.class));

        // check registration
        verify(response, times(1)).sendRedirect("RegisterSuccessful.jsp");
    }
}
