import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Scanner;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/comment")
public class Comment extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        // comment.html 파일 불러오기
        InputStream in = getServletContext().getResourceAsStream("comment.html");
        Scanner scanner = new Scanner(in).useDelimiter("\\A");
        String htmlContent = scanner.hasNext() ? scanner.next() : "";
        scanner.close();

        out.println(htmlContent);
    }
}