function soloNumeros(evt) {
 var charCode = (evt.which) ? evt.which : event.keyCode;
    if (charCode > 31 && (charCode < 48 || charCode > 57))
       return false;
    return true; 
}

function soloLetras(evt) {
    evt = (evt) ? evt : event;
    var charCode = (evt.charCode) ? evt.charCode : ((evt.keyCode) ? evt.keyCode : ((evt.which) ? evt.which : 0));
    if (charCode > 31 && (charCode < 65 || charCode > 90) && (charCode < 97 || charCode > 122) && charCode !== 32) {
       return false;
    }
    return true;
}
function cambiarMayuscula(elemento, e){
 tecla=(document.all) ? e.keyCode : e.which;
 if (elemento!==null) 
  elemento.value = elemento.value.toUpperCase();
}
