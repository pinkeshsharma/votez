<jsp:include page="header.jsp" />
<div class="side_left2">
    <jsp:include page="sidemenu_a.jsp" />
</div>
<form action="controller?action=addadmin" method="POST" id="signupForm" class="">
    <div class="side_right">
        <div class="top_Box">${msg}</div>     
        <ul>
            <li class="top_Box centere_top"><label ><b><u>Add a new system user</u></b></label></li>
        </ul>
        <ul>
            <li class="inline"><label >First Name*</label></li>
            <li class="inline"><input class="signup_input" placeholder="First Name" type="text" size="40" name="firstname" value="${param.firstName}" required /></li>
        </ul>
        <ul>
            <li class="inline"><label >Last Name*</label></li>
            <li class="inline"><input class="signup_input" placeholder="Last Name" type="text" size="40" name="lastname" value="${param.lastName}" required /></li>
        </ul>
        <ul>
            <li class="inline"><label >Email*</label></li>
            <li class="inline"><input class="signup_input" placeholder="Email" type="email" size="40" name="email" value="${param.email}" required /></li>
        </ul>
        <ul>
            <li class="inline"><label >PhoneNumber*</label></li>
            <li class="inline"><input class="signup_input" placeholder="PhoneNumber" type="number" size="10" name="phonenumber" value="" required /></li>
        </ul>
        <ul>
            <li class="inline"><label >Role*</label></li>
            <li class="inline"><select name="role" class="signup_input">
                    <option value="user">Standard User</option>
                    <option value="admin">Admin</option>
                </select>
        </ul>
        <ul>
            <li class="inline"><label>Password*</label></li>
            <li class="inline"><input class="signup_input" id="password" placeholder="Password" type="password" size="40" name="password" required /></li>
        </ul>
        <ul>
            <li class="inline"><label>Confirm Password*</label></li>
            <li class="inline"><input class="signup_input" id="cnfpassword" placeholder="Confirm Password" type="password" size="40" name="confirm_password" required /></li>
        </ul>
        <ul>
            <li class="inline "></li>
            <li class="inline"> <input type="submit" class="button" onclick="submitSignupForm('signupForm');
                    return false;" value="Add System User" name="signup" /></li>
        </ul>
    </div>
</form>
<jsp:include page="footer.jsp" />