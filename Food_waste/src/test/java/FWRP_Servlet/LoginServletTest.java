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
import java.util.ArrayList;
import java.util.List;
import static org.mockito.Mockito.*;

/**
 *
 * @author agsam
 */
public class LoginServletTest {

    private LoginServlet servlet;
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
        servlet = new LoginServlet();
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
    public void testDoPost_SuccessfulConsumerLogin() throws Exception {
        // parameters
        when(request.getParameter("username")).thenReturn("consumer1");
        when(request.getParameter("password")).thenReturn("consumerPass");

        // simulated responses
        List<ConsumerDTO> consumers = new ArrayList<>();
        ConsumerDTO validConsumer = new ConsumerDTO(1, "consumer1", "consumer1@email.com", "consumerPass", true);
        consumers.add(validConsumer);
        when(consumerDAO.getAllConsumers()).thenReturn(consumers);


        // inject simulation in servlet
        LoginServlet servletWithMockDAO = new LoginServlet() {
            protected ConsumerDAO getConsumerDAO() {
                return consumerDAO;
            }
        };

        // run doPost
        servletWithMockDAO.doPost(request, response);

        // check login 
        verify(response, times(1)).sendRedirect("LoginSuccessful.jsp");
    }

    @Test
    public void testDoPost_FailedLogin() throws Exception {
        // simulated parameters
        when(request.getParameter("username")).thenReturn("invalidUser");
        when(request.getParameter("password")).thenReturn("invalidPassword");

        // empty response
        when(consumerDAO.getAllConsumers()).thenReturn(new ArrayList<>());

        // inject simulation in servlet
        LoginServlet servletWithMockDAO = new LoginServlet() {
            protected ConsumerDAO getConsumerDAO() {
                return consumerDAO;
            }
        };

        // run doPost
        servletWithMockDAO.doPost(request, response);

        // check login
        verify(response, times(1)).sendRedirect("loginError.jsp");
    }

    @Test
    public void testDoPost_SQLException() throws Exception {
        // simulated parameters
        when(request.getParameter("username")).thenReturn("someUser");
        when(request.getParameter("password")).thenReturn("somePass");

        //  SQLException for simulation
        when(consumerDAO.getAllConsumers()).thenThrow(new SQLException("Database error"));

        // inject simulation in servlet
        LoginServlet servletWithMockDAO = new LoginServlet() {
            protected ConsumerDAO getConsumerDAO() {
                return consumerDAO;
            }
        };

        // run doPost
        servletWithMockDAO.doPost(request, response);

        // check error 
        verify(response, times(1)).sendRedirect("Failed.jsp");
    }
}