<jsp:include page="header.jsp" />
<div class="side_left2">
    <jsp:include page="sidemenu_a.jsp" />
</div>

<form action="controller?action=create" method="POST" id="signupForm" class="signup_center">
    <div class="top_Box">${msg}</div>
    <ul>
        <li class="top_Box centere_top"><label ><b><u>Add a new party</u></b></label></li>
    </ul>
    <ul>
        <li class="inline"><label >Party Name*</label></li>
        <li class="inline"><input class="signup_input" placeholder="Party Name" type="text" size="40" name="partyname" required /></li>
    </ul>
    <ul>
        <li class="inline"><label >Party Symbol*</label></li>
        <li class="inline"><input class="signup_input" placeholder="Party Symbol" type="text" size="40" name="partysymbol" required /></li>
    </ul>
    <ul>
        <li class="inline"><label >Party Ideology*</label></li>
        <li class="inline"><input class="signup_input" placeholder="Party Ideology" type="text" size="40" name="partyideology" required /></li>
    </ul>
    <ul>
        <li class="inline"><label >Party Chairperson*</label></li>
        <li class="inline"><input class="signup_input" placeholder="Party Chairperson" type="text" size="40" name="partychairman" required /></li>
    </ul>
    <ul>
        <li class="inline"><label >Email*</label></li>
        <li class="inline"><input class="signup_input" placeholder="Email" type="email" size="40" name="email" required /></li>
    </ul>
    <ul>
        <li class="inline"><label >Street*</label></li>
        <li class="inline"><input class="signup_input" placeholder="Street" type="text" size="40" name="street" required /></li>
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
        <li class="inline"><label >Founded (Year)</label></li>
        <li class="inline"><input class="signup_input" placeholder="Founded" type="text" size="40" name="founded" value="" /></li>
    </ul>
    <ul>
        <li class="inline "></li>
        <li class="inline"> <input type="submit" class="button" onclick="submit('signupForm');
                return false;" value="Add Party" name="signup" /></li>
    </ul>
</form>
<jsp:include page="footer.jsp" />