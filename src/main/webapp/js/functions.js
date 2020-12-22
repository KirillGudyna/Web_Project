function validForm() {
    if (document.getElementById("password").value != document.getElementById("repeatedPassword").value) {
        if (sessionStorage.language === 'en') {
            alert("Password and repeated password are different");
        } else {
            alert("Введенные пароли не совпадают");
        }
        return false;
    }
}

function chooseLanguage() {
    if (document.getElementById("en").checked) {
        sessionStorage.language = 'en';
    } else {
        sessionStorage.language = 'ru';
    }
    document.getElementById("change_language").submit();
}


function onlickChooseAppointmentId() {
    let appointmentId = document.getElementById("chosen_appointment").value
}

function chooseIsDoctor() {
    if (document.getElementById("doctor").checked) {
        document.getElementById("keyDoctor").style.display = "block";
        document.getElementById("jobType").style.display = "block";
        document.getElementById("timeWork").style.display = "block";
        document.getElementById("profession").value = "doctor";
        document.getElementById("profession").enabled = false;
        document.getElementById("maxAppPerDay").style.display = "block"
        document.getElementById("minPrice").style.display = "block"
    } else {
        document.getElementById("keyDoctor").style.display = "none";
        document.getElementById("keyDoctor").value = "";
        document.getElementById("jobType").style.display = "none";
        document.getElementById("jobType").value = "";
        document.getElementById("timeWork").style.display = "none";
        document.getElementById("timeWork").value = "";
        document.getElementById("maxAppPerDay").style.display = "none"
        document.getElementById("maxAppPerDay").value = "0";
        document.getElementById("profession").value = "";
        document.getElementById("profession").enabled = true;
        document.getElementById("minPrice").style.display = "none"
        document.getElementById("minPrice").value = "0";
    }
}

function chooseAddDrug() {
    if (document.getElementById("add_drug").checked) {
        document.getElementById("drug_name").style.display = "block";
        document.getElementById("amount").style.display = "block";
        document.getElementById("term_taking").style.display = "block";
    } else {
        document.getElementById("drug_name").style.display = "none";
        document.getElementById("amount").style.display = "none";
        document.getElementById("term_taking").style.display = "none";
    }
}

function chooseAddProcedure() {
    if (document.getElementById("add_procedure").checked) {
        document.getElementById("procedure_name").style.display = "block";
        document.getElementById("start_date").style.display = "block";
        document.getElementById("end_date").style.display = "block";
    } else {
        document.getElementById("procedure_name").style.display = "none";
        document.getElementById("start_date").style.display = "none";
        document.getElementById("end_date").style.display = "none";
    }
}


function loadPage() {
    if (sessionStorage.language === 'en') {
        document.getElementById("en").checked = true;
    }
    if (sessionStorage.language === 'ru') {
        document.getElementById("ru").checked = true;
    }
}

window.onload = loadPage;