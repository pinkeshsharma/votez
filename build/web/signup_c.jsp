<jsp:include page="header.jsp" />

<form action="controller?action=create_c" method="POST" id="signupForm" class="signup_center">
    <div class="top_Box">${msg}</div>
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
        <li class="inline"><label >Street*</label></li>
        <li class="inline"><input class="signup_input" placeholder="Street" type="text" size="40" name="street" value="" required /></li>
    </ul>
    <ul>
        <li class="inline"><label >Apartment*</label></li>
        <li class="inline"><input class="signup_input" placeholder="Apartment" type="text" size="40" name="apartment" value="" required /></li>
    </ul>
    <ul>
        <li class="inline"><label >City*</label></li>
        <li class="inline"><input class="signup_input" placeholder="City" type="text" size="40" name="city" value="" required /></li>
    </ul>
    <ul>
        <li class="inline"><label >State*</label></li>
        <li class="inline"><select name="state" class="signup_input">
                <option value="AL">Alabama</option>
                <option value="AK">Alaska</option>
                <option value="AZ">Arizona</option>
                <option value="AR">Arkansas</option>
                <option value="CA">California</option>
                <option value="CO">Colorado</option>
                <option value="CT">Connecticut</option>
                <option value="DE">Delaware</option>
                <option value="DC">District Of Columbia</option>
                <option value="FL">Florida</option>
                <option value="GA">Georgia</option>
                <option value="HI">Hawaii</option>
                <option value="ID">Idaho</option>
                <option value="IL">Illinois</option>
                <option value="IN">Indiana</option>
                <option value="IA">Iowa</option>
                <option value="KS">Kansas</option>
                <option value="KY">Kentucky</option>
                <option value="LA">Louisiana</option>
                <option value="ME">Maine</option>
                <option value="MD">Maryland</option>
                <option value="MA">Massachusetts</option>
                <option value="MI">Michigan</option>
                <option value="MN">Minnesota</option>
                <option value="MS">Mississippi</option>
                <option value="MO">Missouri</option>
                <option value="MT">Montana</option>
                <option value="NE">Nebraska</option>
                <option value="NV">Nevada</option>
                <option value="NH">New Hampshire</option>
                <option value="NJ">New Jersey</option>
                <option value="NM">New Mexico</option>
                <option value="NY">New York</option>
                <option value="NC">North Carolina</option>
                <option value="ND">North Dakota</option>
                <option value="OH">Ohio</option>
                <option value="OK">Oklahoma</option>
                <option value="OR">Oregon</option>
                <option value="PA">Pennsylvania</option>
                <option value="RI">Rhode Island</option>
                <option value="SC">South Carolina</option>
                <option value="SD">South Dakota</option>
                <option value="TN">Tennessee</option>
                <option value="TX">Texas</option>
                <option value="UT">Utah</option>
                <option value="VT">Vermont</option>
                <option value="VA">Virginia</option>
                <option value="WA">Washington</option>
                <option value="WV">West Virginia</option>
                <option value="WI">Wisconsin</option>
                <option value="WY">Wyoming</option>
                <option value="AS">American Samoa</option>
                <option value="GU">Guam</option>
                <option value="MP">Northern Mariana Islands</option>
                <option value="PR">Puerto Rico</option>
                <option value="UM">United States Minor Outlying Islands</option>
                <option value="VI">Virgin Islands</option>
            </select>				
        </li>
    </ul>
    <ul>
        <li class="inline"><label >PostalCode*</label></li>
        <li class="inline"><input class="signup_input" placeholder="PostalCode" type="number" size="40" name="postalCode" value="" required /></li>
    </ul>
    <ul>
        <li class="inline"><label >PhoneNumber*</label></li>
        <li class="inline"><input class="signup_input" placeholder="PhoneNumber" type="number" size="10" name="phonenumber" value="" required /></li>
    </ul>
    <ul>
        <li class="inline"><label >Party*</label></li>
        <li class="inline"><select name="partyID" class="signup_input">
                <option value="VA1956">Vacant</option>
                <option value="DP1828">Democratic Party</option>
                <option value="ID1967">Independent</option>
                <option value="RP1854">Republican Party</option>
                
            </select>				
        </li>
    </ul>
    <ul>
        <li class="inline"><label >Total Assets</label></li>
        <li class="inline"><input class="signup_input" placeholder="Total Assets" type="number" size="10" name="totalassets" value="" /></li>
    </ul>
    <ul>
        <li class="inline"><label >Account Number</label></li>
        <li class="inline"><input class="signup_input" placeholder="Account Number" type="number" size="40"  name="accountnumber" value="" /></li>
    </ul>
    <ul>
        <li class="inline"><label >Account Holding Bank</label></li>
        <li class="inline"><input class="signup_input" placeholder="Account Holding Bank" type="text" size="40"  name="accountholdingbank" value="" /></li>
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
                return false;" value="Create Account" name="signup" /></li>
    </ul>
</form>
<jsp:include page="footer.jsp" />