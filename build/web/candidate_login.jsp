<jsp:include page="header.jsp" />

        <form action="controller?action=login_c" method="POST" class="login_center">
            <div class="pad_3 topOfBox">${msg}</div>
            <ul>
                <li class="inline"><label >Email Address*</label></li>
                <li class="inline"><input class="login_input" placeholder="Email" type="email" size="40" name="email" required /><br></li>
            </ul>
            <ul>
                <li class="inline"><label>Password*</label></li>
                <li class="inline"><input class="login_input" placeholder="Password" type="password" size="40" name="password" required /><br></li>
            </ul>
            <ul>
                <li class="inline "></li>
                <li class="inline"> <input type="submit" class="button" value="Log in" name="login" /></li>
            </ul>
 
            <div class="login_input">
                <a href="<%=request.getContextPath() + "/controller?action=signup_c"%>">Register</a>
            </div>
        </form>
<jsp:include page="footer.jsp" />
