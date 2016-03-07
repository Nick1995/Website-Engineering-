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
//            int pid = ex.getPid();
            Connection con = ex.getConnection();
            stmt = con.createStatement();
            String strSql = "select bilder  from Bilder where id='" + id + "' ";
            rs = stmt.executeQuery(strSql);
            if (rs.next()){
                byte[] bytearray = new byte[1048576];
                int size = 0;
                sImage = rs.getBinaryStream("bilder");
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
