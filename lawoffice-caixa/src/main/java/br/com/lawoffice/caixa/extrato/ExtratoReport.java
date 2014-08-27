/**
 * 
 */
package br.com.lawoffice.caixa.extrato;


/**
 * 
 * Interface de serviço para a criacao de arquivo de extrato.
 * 
 * @author robson
 *
 */
public interface ExtratoReport{

	/**
	 * 
	 * Gera o Extrato utilizando um {@link ExtratoDTO} passada como resource.
	 * 
	 * @param extratoDTO - com os dados para gerar o extrato.
	 * @return byte[] - com o arquivo gerado.
	 * @throws ExtratoReportException quando ocorrer algum problema na geraçao do arquivo.
	 * @throws IllegalArgumentException quando o {@link ExtratoDTO} estiver null ou quando suas propriedades estvier nulas.
	 */
	public byte[] gerarExtrato(ExtratoDTO extratoDTO);
}
