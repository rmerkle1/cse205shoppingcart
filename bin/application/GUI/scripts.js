var currentType;

$(document).ready(function() {

    if (checkLoginStatus()) {
        $("#first").hide();
        $("#second").show();

        populateProfile();
        currentType = myApp.getCurrentType();

        if (currentType.localeCompare("Manager") != 0) {
            // $("a.managerOnly").attr("href", "#");
            // $("a.managerOnly").css("text-decoration", "line-through");
            $("a.managerOnly").hide();
        }
    } else {
        $("#first").show();
        $("#second").hide();
    }

    $("#test").click(function() {
        $("#second").hide();
    });

    hideLogin();

    $("#login").click(function() {
        showLogin();
    });

    $("#loginSubmit").click(function() {
        var loginInfo = myApp.login($("#loginForm").serialize());
        $("#loginStatus").text("You should be logged in with this login info:" + loginInfo);
    });

    $("#signupSubmit").click(function() {
        var signupInfo = myApp.signup($("#signupForm").serialize());
        $("#signupStatus").text("You should be signed up with this signup info:" + signupInfo);
    });

    // testing buttons

    $("#refresh").click(function() {
        myApp.refreshPage();
    });

    $("#doSomething").click(function() {
        $("header h1 a").text(myApp.test(1));
    });
});

function checkLoginStatus() {
    // Check Java code for login status and return that when finished
    return myApp.checkLoginStatus();
    // return false;
}

function hideLogin() {
    $("#first #loginForm, #first #loginSubmit").hide();
}

function showLogin() {
    $("#first #loginForm, #first #loginSubmit").slideDown();
}

function populateProfile() {
    $("#usernameDisp .disp").text(myApp.getCurrentUsername());
    $("#usertypeDisp .disp").text(myApp.getCurrentType());
    $("#fnameDisp .disp").text(myApp.getCurrentFname());
    $("#lnameDisp .disp").text(myApp.getCurrentLname());
    $("#emailDisp .disp").text(myApp.getCurrentEmail());
    $("#balanceDisp .disp").text("$"+myApp.getCurrentBalance());
}