/**
 * 
 */
package br.com.lawoffice.caixa.extrato;

/**
 * 
 * Exceção de runtime lançada quando ocorre algum erro na geração do arquivo de extrato.
 * 
 * @author rduarte
 *
 */
public class ExtratoReportException extends RuntimeException {

	private static final long serialVersionUID = -6600055577821066753L;


	public ExtratoReportException() {
	}

	
	public ExtratoReportException(String message) {
		super(message);
	}


	public ExtratoReportException(Throwable cause) {
		super(cause);
	}


	public ExtratoReportException(String message, Throwable cause) {
		super(message, cause);
	}
}
