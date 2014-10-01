function onClickBtnNovo(){	
	showDados();
	jQuery("#btnSalvarEditar").hide();
	jQuery("#btnSalvarNovo").show();
}


function onClickBtnEditar(){	
	showDados();
	jQuery("#btnSalvarEditar").show();
	jQuery("#btnSalvarNovo").hide();
	
}

function onClickBtnSalvar(){
	showListaDados();
	bindClickTable();
}


function onClickBtnCancelar(){		
	showListaDados();
}


function showListaDados() {
	jQuery("#listaDados").show();
	jQuery("#dados").hide();
}


function showDados() {
	jQuery("#listaDados").hide();		
	jQuery("#dados").show();
}


// faz o bind do click em uma linha ( tr ) da tabela no carregamento da página.
jQuery(function() {
	bindClickTable();
});



// desabilidata btn de editar/remover no carregamento da página.
jQuery(function() {
	jQuery("#btnEditar")
		.attr('disabled', true)
		.addClass('ui-button-disabled ui-state-disabled');
	
	jQuery("#btnRemover")
		.attr('disabled', true)
		.addClass('ui-button-disabled ui-state-disabled');	
});



// faz o bind no evento de click do btn remover no carregamento da página
jQuery(function() {
	jQuery("#btnRemover").click(function() {
		jQuery("#btnEditar")
			.attr('disabled', true)
			.addClass('ui-button-disabled ui-state-disabled');
		
		jQuery("#btnRemover")
			.attr('disabled', true)
			.addClass('ui-button-disabled ui-state-disabled');		
	});
});


// faz o bind no click de uma linha ( tr ) da tabela de clientes/colaboradores 
function bindClickTable() {
	jQuery("table > tbody > tr").click(function() {
		jQuery("#btnEditar")
			.attr('disabled', false)
			.removeClass('ui-button-disabled ui-state-disabled');
		
		jQuery("#btnRemover")
			.attr('disabled', false)
			.removeClass('ui-button-disabled ui-state-disabled');		
	});
}