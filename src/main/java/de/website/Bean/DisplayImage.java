package de.website.Bean;

/**
 * Created by Nick on 07.03.2016.
 */
import java.sql.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class DisplayImage extends HttpServlet {

    Exchange ex = Exchange.getInstance();
    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws ServletException, IOException {
        Statement stmt = null;
        ResultSet rs;
        InputStream sImage;
        try {
            String id = request.getParameter("id");
            System.out.println("inside servlet–>" + id);
            int pid = ex.getPid();
            Connection con = ex.getConnection();
            stmt = con.createStatement();
            String strSql = "select Bilder from Bilder where iid='" + pid + "' ";
            rs = stmt.executeQuery(strSql);
            while (rs.next()) {
                byte[] bytearray = new byte[1048576];
                int size = 0;
                sImage = rs.getBinaryStream(1);
                response.reset();
                response.setContentType("image/jpeg");
                while ((size = sImage.read(bytearray)) != -1) {
                    response.getOutputStream().write(bytearray, 0, size);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
