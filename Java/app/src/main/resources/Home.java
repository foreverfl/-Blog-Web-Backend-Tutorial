
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Scanner;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/home")
public class Home extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        // HTML 파일 불러오기
        InputStream in = getServletContext().getResourceAsStream("sign-in.html");
        Scanner scanner = new Scanner(in).useDelimiter("\\A");
        String htmlContent = scanner.hasNext() ? scanner.next() : "";
        scanner.close();

        out.println(htmlContent);
    }

}
