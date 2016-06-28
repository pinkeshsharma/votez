<%@page import="com.java.model.Candidate"%>


<jsp:include page="header.jsp" />
<div class="side_left">
    <jsp:include page="sidemenu_c.jsp" />
</div>
<%
    Candidate user = (Candidate) session.getAttribute("loggedInCandidate");
%>
<div class="side_right">
    <form action="controller?action=doupdatecandidateprofile" method="POST" id="signupForm" class="">
        <div class="top_Box">${msg}</div>
        <input id="AddressId" name="AddressId" type="hidden" value="${loggedInCandidate.address.addressID}"/>
        <input id="CandidateId" name="CandidateId" type="hidden" value="${loggedInCandidate.candidateID}"/>
        <ul>
            <li class="inline"><label >First Name*</label></li>
            <li class="inline"><input class="signup_input" placeholder="First Name" type="text" size="40" name="firstname" value="${loggedInCandidate.firstName}" required /></li>
        </ul>
        <ul>
            <li class="inline"><label >Last Name*</label></li>
            <li class="inline"><input class="signup_input" placeholder="Last Name" type="text" size="40" name="lastname" value="${loggedInCandidate.lastName}" required /></li>
        </ul>
        <ul>
            <li class="inline"><label >Email*</label></li>
            <li class="inline"><input class="signup_input" placeholder="Email" type="email" size="40" name="email" value="${loggedInCandidate.email}" required /></li>
        </ul>
        <ul>
            <li class="inline"><label >Street*</label></li>
            <li class="inline"><input class="signup_input" placeholder="Street" type="text" size="40" name="street" value="${loggedInCandidate.address.street}" required /></li>
        </ul>
        <ul>
            <li class="inline"><label >Apartment*</label></li>
            <li class="inline"><input class="signup_input" placeholder="Apartment" type="text" size="40" name="apartment" value="${loggedInCandidate.address.apartment}" required /></li>
        </ul>
        <ul>
            <li class="inline"><label >City*</label></li>
            <li class="inline"><input class="signup_input" placeholder="City" type="text" size="40" name="city" value="${loggedInCandidate.address.city}" required /></li>
        </ul>
        <ul>
            <li class="inline"><label >State*</label></li>
            <li class="inline"><select name="state" class="signup_input" value='${loggedInCandidate.address.state}'>
                    <option <%=user.getAddress().getState().equalsIgnoreCase("-1") ? "selected" : ""%> value="-1">Select</option>
                    <option <%=user.getAddress().getState().equalsIgnoreCase("AL") ? "selected" : ""%> value="AL">Alabama</option>
                    <option <%=user.getAddress().getState().equalsIgnoreCase("AK") ? "selected" : ""%> value="AK">Alaska</option>
                    <option <%=user.getAddress().getState().equalsIgnoreCase("AZ") ? "selected" : ""%>  value="AZ">Arizona</option>
                    <option <%=user.getAddress().getState().equalsIgnoreCase("AR") ? "selected" : ""%>  value="AR">Arkansas</option>
                    <option <%=user.getAddress().getState().equalsIgnoreCase("CA") ? "selected" : ""%>  value="CA">California</option>
                    <option <%=user.getAddress().getState().equalsIgnoreCase("CO") ? "selected" : ""%>  value="CO">Colorado</option>
                    <option <%=user.getAddress().getState().equalsIgnoreCase("CT") ? "selected" : ""%>  value="CT">Connecticut</option>
                    <option <%=user.getAddress().getState().equalsIgnoreCase("DE") ? "selected" : ""%>  value="DE">Delaware</option>
                    <option <%=user.getAddress().getState().equalsIgnoreCase("DC") ? "selected" : ""%>  value="DC">District Of Columbia</option>
                    <option <%=user.getAddress().getState().equalsIgnoreCase("FL") ? "selected" : ""%>  value="FL">Florida</option>
                    <option <%=user.getAddress().getState().equalsIgnoreCase("GA") ? "selected" : ""%>  value="GA">Georgia</option>
                    <option <%=user.getAddress().getState().equalsIgnoreCase("HI") ? "selected" : ""%>  value="HI">Hawaii</option>
                    <option <%=user.getAddress().getState().equalsIgnoreCase("ID") ? "selected" : ""%>  value="ID">Idaho</option>
                    <option <%=user.getAddress().getState().equalsIgnoreCase("IL") ? "selected" : ""%>  value="IL">Illinois</option>
                    <option <%=user.getAddress().getState().equalsIgnoreCase("IN") ? "selected" : ""%>  value="IN">Indiana</option>
                    <option <%=user.getAddress().getState().equalsIgnoreCase("IA") ? "selected" : ""%>  value="IA">Iowa</option>
                    <option <%=user.getAddress().getState().equalsIgnoreCase("KS") ? "selected" : ""%>  value="KS">Kansas</option>
                    <option <%=user.getAddress().getState().equalsIgnoreCase("KY") ? "selected" : ""%>  value="KY">Kentucky</option>
                    <option <%=user.getAddress().getState().equalsIgnoreCase("LA") ? "selected" : ""%>  value="LA">Louisiana</option>
                    <option <%=user.getAddress().getState().equalsIgnoreCase("ME") ? "selected" : ""%>  value="ME">Maine</option>
                    <option <%=user.getAddress().getState().equalsIgnoreCase("MD") ? "selected" : ""%>  value="MD">Maryland</option>
                    <option <%=user.getAddress().getState().equalsIgnoreCase("MA") ? "selected" : ""%>  value="MA">Massachusetts</option>
                    <option <%=user.getAddress().getState().equalsIgnoreCase("MI") ? "selected" : ""%>  value="MI">Michigan</option>
                    <option <%=user.getAddress().getState().equalsIgnoreCase("MN") ? "selected" : ""%>  value="MN">Minnesota</option>
                    <option <%=user.getAddress().getState().equalsIgnoreCase("MS") ? "selected" : ""%>  value="MS">Mississippi</option>
                    <option <%=user.getAddress().getState().equalsIgnoreCase("MO") ? "selected" : ""%>  value="MO">Missouri</option>
                    <option <%=user.getAddress().getState().equalsIgnoreCase("MT") ? "selected" : ""%>  value="MT">Montana</option>
                    <option <%=user.getAddress().getState().equalsIgnoreCase("NE") ? "selected" : ""%>  value="NE">Nebraska</option>
                    <option <%=user.getAddress().getState().equalsIgnoreCase("NV") ? "selected" : ""%>  value="NV">Nevada</option>
                    <option <%=user.getAddress().getState().equalsIgnoreCase("NH") ? "selected" : ""%>  value="NH">New Hampshire</option>
                    <option <%=user.getAddress().getState().equalsIgnoreCase("NJ") ? "selected" : ""%>  value="NJ">New Jersey</option>
                    <option <%=user.getAddress().getState().equalsIgnoreCase("NM") ? "selected" : ""%>  value="NM">New Mexico</option>
                    <option <%=user.getAddress().getState().equalsIgnoreCase("NY") ? "selected" : ""%>  value="NY">New York</option>
                    <option <%=user.getAddress().getState().equalsIgnoreCase("NC") ? "selected" : ""%>  value="NC">North Carolina</option>
                    <option <%=user.getAddress().getState().equalsIgnoreCase("ND") ? "selected" : ""%>  value="ND">North Dakota</option>
                    <option <%=user.getAddress().getState().equalsIgnoreCase("OH") ? "selected" : ""%>  value="OH">Ohio</option>
                    <option <%=user.getAddress().getState().equalsIgnoreCase("OK") ? "selected" : ""%>  value="OK">Oklahoma</option>
                    <option <%=user.getAddress().getState().equalsIgnoreCase("OR") ? "selected" : ""%>  value="OR">Oregon</option>
                    <option <%=user.getAddress().getState().equalsIgnoreCase("PA") ? "selected" : ""%>  value="PA">Pennsylvania</option>
                    <option <%=user.getAddress().getState().equalsIgnoreCase("RI") ? "selected" : ""%>  value="RI">Rhode Island</option>
                    <option <%=user.getAddress().getState().equalsIgnoreCase("SC") ? "selected" : ""%>  value="SC">South Carolina</option>
                    <option <%=user.getAddress().getState().equalsIgnoreCase("SD") ? "selected" : ""%>  value="SD">South Dakota</option>
                    <option <%=user.getAddress().getState().equalsIgnoreCase("TN") ? "selected" : ""%>  value="TN">Tennessee</option>
                    <option <%=user.getAddress().getState().equalsIgnoreCase("TX") ? "selected" : ""%>  value="TX">Texas</option>
                    <option <%=user.getAddress().getState().equalsIgnoreCase("UT") ? "selected" : ""%>  value="UT">Utah</option>
                    <option <%=user.getAddress().getState().equalsIgnoreCase("VT") ? "selected" : ""%>  value="VT">Vermont</option>
                    <option <%=user.getAddress().getState().equalsIgnoreCase("VA") ? "selected" : ""%>  value="VA">Virginia</option>
                    <option <%=user.getAddress().getState().equalsIgnoreCase("WA") ? "selected" : ""%>  value="WA">Washington</option>
                    <option <%=user.getAddress().getState().equalsIgnoreCase("WV") ? "selected" : ""%>  value="WV">West Virginia</option>
                    <option <%=user.getAddress().getState().equalsIgnoreCase("WI") ? "selected" : ""%>  value="WI">Wisconsin</option>
                    <option <%=user.getAddress().getState().equalsIgnoreCase("WY") ? "selected" : ""%>  value="WY">Wyoming</option>
                    <option <%=user.getAddress().getState().equalsIgnoreCase("AS") ? "selected" : ""%>  value="AS">American Samoa</option>
                    <option <%=user.getAddress().getState().equalsIgnoreCase("GU") ? "selected" : ""%>  value="GU">Guam</option>
                    <option <%=user.getAddress().getState().equalsIgnoreCase("MP") ? "selected" : ""%>  value="MP">Northern Mariana Islands</option>
                    <option <%=user.getAddress().getState().equalsIgnoreCase("PR") ? "selected" : ""%>  value="PR">Puerto Rico</option>
                    <option <%=user.getAddress().getState().equalsIgnoreCase("UM") ? "selected" : ""%>  value="UM">United States Minor Outlying Islands</option>
                    <option <%=user.getAddress().getState().equalsIgnoreCase("VI") ? "selected" : ""%>  value="VI">Virgin Islands</option>
                </select>				
            </li>
        </ul>
        <ul>
            <li class="inline"><label >PostalCode*</label></li>
            <li class="inline"><input class="signup_input" placeholder="PostalCode" type="number" size="40" name="postalCode" value="${loggedInCandidate.address.zip}" required /></li>
        </ul>
        <ul>
            <li class="inline"><label >PhoneNumber*</label></li>
            <li class="inline"><input class="signup_input" placeholder="PhoneNumber" type="number" size="10" name="phonenumber" value="${loggedInCandidate.phoneNumber}" required /></li>
        </ul>
        <ul>
            <li class="inline"><label >Total Assets</label></li>
            <li class="inline"><input class="signup_input" placeholder="Total Assets" type="number" size="10" name="totalassets" value="${loggedInCandidate.totalAssets}" /></li>
        </ul>
        <ul>
            <li class="inline"><label >Account Number</label></li>
            <li class="inline"><input class="signup_input" placeholder="Account Number" type="number" size="40"  name="accountnumber" value="${loggedInCandidate.accountNumber}" /></li>
        </ul>
        <ul>
            <li class="inline"><label >Account Holding Bank</label></li>
            <li class="inline"><input class="signup_input" placeholder="Account Holding Bank" type="text" size="40"  name="accountholdingbank" value="${loggedInCandidate.accountHoldingBank}" /></li>
        </ul>
        <ul>
            <li class="inline "></li>
            <li class="inline"> <input type="submit" class="button" onclick="submitUpdateProfileForm('signupForm');
                    return false;" value="Update Profile" name="signup" /></li>
        </ul>
    </form>
</div>
<jsp:include page="footer.jsp" />