<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>로그인</title>
    <!-- 스타일시트 내용은 동일하게 유지 -->
    <style>
        body {
          display: flex;
          justify-content: center;
          align-items: center;
          height: 100vh;
          background-color: #ececec;
        }
        .login-form {
          background-color: #fff;
          padding: 20px;
          border-radius: 5px;
          box-shadow: 0 0 10px rgba(39, 39, 39, 0.1);
          text-align: center;
        }
        .login-form h2 {
          margin-top: 0;
          margin-bottom: 0;
        }
  
        .login-error {
          color: red;
        }
  
        .form-input {
          margin-bottom: 10px;
        }
        .form-input input {
          width: 80%;
          padding: 8px;
          margin-top: 5px;
          border-radius: 5px;
        }
        .form-actions {
          text-align: center;
        }
  
        /* 로그인 버튼 */
        .form-actions button {
          padding: 8px 15px;
          background-color: blue;
          color: white;
          border: none;
          border-radius: 5px;
          cursor: pointer;
        }
        .form-actions button:hover {
          background-color: darkblue;
        }
  
        /* 회원가입 버튼 */
        .form-actions .button-style {
          background-color: blue;
          color: white;
          text-decoration: none; /* 링크 밑줄 제거 */
          border: none;
          border-radius: 5px;
          cursor: pointer;
        }
        
        .form-actions .button-style:hover {
          background-color: darkblue;
        }
      </style>
  </head>
  <body>
    <div class="login-form">
      <h2>로그인</h2>
      <form action="${pageContext.request.contextPath}/sign_in_process" method="post">
        <% if (request.getAttribute("loginError") != null) { %>
          <div class="login-error">
            <p><%= request.getAttribute("loginError") %></p>
          </div>
        <% } %>
        <div class="form-input">
          <input type="text" id="username" name="username" placeholder="아이디" />
        </div>
        <div class="form-input">
          <input type="password" id="password" name="password" placeholder="패스워드" />
        </div>
        <div class="form-actions">
          <button type="submit">로그인</button>
          <a href="${pageContext.request.contextPath}/sign_up" class="button-style"><button type="button">회원가입</button></a> 
        </div>
      </form>
    </div>
  </body>
</html>