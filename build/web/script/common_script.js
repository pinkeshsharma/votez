/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function redirectTo(url) {
    window.location = url;
}

function submit(id) {
    document.getElementById(id).submit();
}

function submitForm(studyCode) {
    document.getElementById('studyCode').value = studyCode;
    document.getElementById('parcipateForm').submit();
}


function updateStatus(studyCode, action) {
    document.getElementById('studyCode').value = studyCode;
    document.getElementById('editStudyForm').action = action;
    document.getElementById('editStudyForm').submit();
}

function editStudy(studyCode, action) {
    document.getElementById('studyCode').value = studyCode;
    document.getElementById('editStudyForm').action = action;
    document.getElementById('editStudyForm').submit();
}

function submitSignupForm(signupForm) {
    var password = document.getElementById("password").value;
    var cnfPassword = document.getElementById("cnfpassword").value;
    if (password === cnfPassword) {
        document.getElementsById(signupForm).submit();
    } else {
        alert("Password and Confirm password shoould be same");
    }
}


function submitVote() {
    var candidate = document.getElementsByName('candidate');
    var selectedCandidate = '';
    for (var i = 0; i < candidate.length; i++) {
        if (candidate[i].checked) {
            selectedCandidate = candidate[i].value;
        }
    }
    if (selectedCandidate === '') {
        alert('Please select a candidate to vote.');
    } else {
        document.getElementById('selectedCandidate').value = selectedCandidate;
        document.getElementById('voteForm').submit();
    }
}


function submitForm(fieldId, fieldValue, formId) {
    document.getElementById(fieldId).value = fieldValue;
    document.getElementById(formId).submit();
}

function submitFormWithAction(fieldId, fieldValue, formId, action) {
    document.getElementById(fieldId).value = fieldValue;
    document.getElementById(formId).action = action;
    document.getElementById(formId).submit();
}

function registerElection() {
    var election = document.getElementsByName('election');
    var selectedElection = '';
    for (var i = 0; i < election.length; i++) {
        if (election[i].checked) {
            selectedElection = election[i].value;
        }
    }
    if (selectedElection === '') {
        alert('Please select a Election to register.');
    } else {
        document.getElementById('electionID').value = selectedElection;
        document.getElementById('submitform').submit();
    }
}
