/*jQuery(function() {
	jQuery("#dataInicial").datepicker({ dateFormat: 'dd/mm/yy' });
	jQuery("#dataFinal").datepicker({ dateFormat: 'dd/mm/yy' });
});*/


function oneventCreditar(data){
	if ( data.status == "begin"){
		jQuery("#btnCreditarCliente").attr('disabled', 'disabled');
	}			
	if (data.status == "success"){
		jQuery("#btnCreditarCliente").attr('disabled', '');
	}
}


function clear() {
	jQuery('.inputText').val('');
	jQuery('#cliente_hinput').val('');
}