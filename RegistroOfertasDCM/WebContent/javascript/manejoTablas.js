function marcar(TR, IdSel,boton){
			//Rellenar el codigo de la firma configuracion inyectada y luego llamar al metodo que busca en la 
			// BBDD esta firma configuración mediante su codigo y recarga la pagina con la firma conf rellena
			document.getElementById(IdSel).value=rTrim(TR.cells[0].innerText);			
			document.getElementById(boton).click();			
		}
		
function rTrim(cadena){
	return (cadena.charAt(cadena.length-1)==" "?cadena.substring(0,cadena.length-1):cadena);	
}		
function colorearTabla(tabla, IdSel){
    if(document.getElementsByTagName){ 
    	//guardar en la variable sel el codigo de la firma configuración inyectada en la BB para marcarla
    	// en el listado como seleccionada.
    	var sel= "" + document.getElementById(IdSel).value;
        var table = document.getElementById(tabla);  
        var rows = table.getElementsByTagName("tr");
        var numFilas = rows.length * 1;	
        for (var i=1; i<numFilas;i++){
        	if(i%2==0){
        		rows[i].className = "evenRowColor";
        	}
        	else {
        		rows[i].className = "oddRowColor";
        	}
        	if(sel==rows[i].cells[0].innerText){
        		rows[i].className = "selectedRowColor";
        	}
        }
 	}
}
		