function oneventPesquisar(data){
	if ( data.status == "begin"){
		jQuery("#btnPesquisar").attr('disabled', 'disabled');
	}			
	if (data.status == "success"){
		jQuery("#btnPesquisar").attr('disabled', '');
	}
}


