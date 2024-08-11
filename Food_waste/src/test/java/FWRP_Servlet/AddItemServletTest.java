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

public class AddItemServletTest {

    private AddItemServlet servlet;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private HttpSession session;
    private StringWriter responseWriter;

    @BeforeEach
    // setUp method must be executed before each test method
    public void setUp() throws Exception {
        servlet = new AddItemServlet();
        request = mock(HttpServletRequest.class); // simulates http request
        response = mock(HttpServletResponse.class);// simulates http response
        session = mock(HttpSession.class); // simulates http session
        responseWriter = new StringWriter();

        when(request.getSession()).thenReturn(session);
        when(response.getWriter()).thenReturn(new PrintWriter(responseWriter));
    }

    @Test
    public void testDoPost_Success() throws Exception {
        //  parameters
        when(request.getParameter("retailer_id")).thenReturn("1");
        when(request.getParameter("item_name")).thenReturn("Item test");
        when(request.getParameter("item_description")).thenReturn("Item test description");
        when(request.getParameter("price")).thenReturn("19.99");
        when(request.getParameter("expiry_date")).thenReturn("2024-12-31");
        when(request.getParameter("quantity")).thenReturn("100");

        // run doPost
        servlet.doPost(request, response);

        // check sendRedirect calling
        verify(response, times(1)).sendRedirect("RetailerHome.jsp");
    }

    @Test
    public void testDoPost_InvalidInput() throws Exception {
        // invalid input data
        when(request.getParameter("retailer_id")).thenReturn(null); 

        // run doPost
        servlet.doPost(request, response);

        // check sendError called
        verify(response, times(1)).sendError(eq(HttpServletResponse.SC_BAD_REQUEST), contains("Invalid input"));
    }

    @Test
    public void testDoPost_SQLException() throws Exception {
        //  parameters
        when(request.getParameter("retailer_id")).thenReturn("1");
        when(request.getParameter("item_name")).thenReturn("Item test");
        when(request.getParameter("item_description")).thenReturn("Item test description");
        when(request.getParameter("price")).thenReturn("19.99");
        when(request.getParameter("expiry_date")).thenReturn("2024-12-31");
        when(request.getParameter("quantity")).thenReturn("100");

        // simulated object to throw SQLException
        InventoryDAO mockDAO = mock(InventoryDAO.class);
        doThrow(new SQLException("Database error")).when(mockDAO).addItem(any(InventoryDTO.class));

        // simulated class in servlet
        AddItemServlet servletWithMockDAO = new AddItemServlet() {
            protected InventoryDAO getInventoryDAO() {
                return mockDAO;
            }
        };

        // run doPost
        servletWithMockDAO.doPost(request, response);

        // check sendError was called 
        verify(response, times(1)).sendError(eq((byte) HttpServletResponse.SC_BAD_REQUEST), contains("Database error"));
    }
}
