<jsp:include page="header.jsp" />

        <form action="controller?action=login_a" method="POST" class="login_center">
            <div class="pad_3 topOfBox">${msg}</div>
            <ul>
                <li class="inline"><label >User ID*</label></li>
                <li class="inline"><input class="login_input" placeholder="User ID" type="text" size="40" name="userid" required /><br></li>
            </ul>
            <ul>
                <li class="inline"><label>Password*</label></li>
                <li class="inline"><input class="login_input" placeholder="Password" type="password" size="40" name="password" required /><br></li>
            </ul>
            <ul>
                <li class="inline "></li>
                <li class="inline"> <input type="submit" class="button" value="Log in" name="login" /></li>
            </ul>
            
        </form>
<jsp:include page="footer.jsp" />
