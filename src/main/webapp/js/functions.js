function validForm(f) {
    if ( new Date(f.arriving_date.value) >= new Date(f.leaving_date.value))
    {
        alert("Проверьте корректность введенных Вами данных");
    }
    else {
        if ( new Date(f.arriving_date.value) <= new Date())
        {      
            alert("Проверьте корректность введенных Вами данных");
        }
        else{
            f.submit();
        }
    }
}           
            
function chooseLanguage() {
     if ( document.getElementById("en").checked ){
             sessionStorage.language='en';
         }
        else{
            sessionStorage.language='ru';
        }
     document.getElementById("change_language").submit();
}

function chooseIsDoctor() {
    if(document.getElementById("doctor").checked) {
        document.getElementById("keyDoctor").style.display="block";
        document.getElementById("jobType").style.display="block";
        document.getElementById("timeWork").style.display="Block";
        document.getElementById("profession").value="doctor";
    } else{
        document.getElementById("keyDoctor").style.display="none";
        document.getElementById("keyDoctor").value="";
        document.getElementById("jobType").style.display="none";
        document.getElementById("jobType").value = "";
        document.getElementById("timeWork").style.display="none";
        document.getElementById("timeWork").value ="";
        document.getElementById("profession").value="";
    }
}

function loadPage()
{
    if ( sessionStorage.language==='en' )
    {
        document.getElementById("en").checked = true; 
    }
    if( sessionStorage.language==='ru' )
    {
        document.getElementById("ru").checked = true;
    }
}

window.onload = loadPage;