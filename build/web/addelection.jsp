<jsp:include page="header.jsp" />
<div class="side_left2">
    <jsp:include page="sidemenu_a.jsp" />
</div>
<form action="controller?action=addelection" method="POST" id="signupForm" class="">
    <div class="side_right">
        <div class="top_Box">${msg}</div>    
        <ul>
            <li class="top_Box centere_top"><label ><b><u>Add a new election</u></b></label></li>
        </ul>
        <ul>
            <li class="inline"><label >Election Type*</label></li>
            <li class="inline"><input class="signup_input" placeholder="Election Type" type="text" size="40" name="electiontype" required /></li>
        </ul>
        <ul>
            <li class="inline"><label>District*</label></li>
            <li class="inline"><input class="signup_input" placeholder="District" type="text" size="40" name="district" required /></li>
        </ul>
        <ul>
            <li class="inline"><label >Day* (DD-MM-YY)</label></li>
            <li class="inline"><input class="signup_input" placeholder="Election Day" type="text" size="40" name="timePeriod" required /></li>
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
            <li class="inline "></li>
            <li class="inline"> <input type="submit" class="button" onclick="submit('signupForm');
                    return false;" value="Create Election" name="signup" /></li>
        </ul>
    </div>
</form>
<jsp:include page="footer.jsp" />