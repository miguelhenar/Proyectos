package net.gefco.util;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JExcelApiExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;


public class JReportsUtil {

	//	public static String rutaInformes = "D:\\java\\workspaces\\wsGEFCO\\wsGEFCOWeb\\Ej41ProvisionesTabs\\WebContent\\WEB-INF\\reports\\";
	public static String rutaInformes;
	
	@SuppressWarnings("rawtypes") 
	public static void exportarPdf(String informe, Collection datos, String nombreFichero){
		//Map parametros = new HashMap();
		Map<String,Object> parametros= new HashMap<String, Object>();
		exportarPdf(informe, parametros, datos, nombreFichero);
	}
	
	@SuppressWarnings("rawtypes") 
	public static void exportarPdf(String informe, Map<String,Object> parametros, Collection datos, String nombreFichero) {

		FacesContext fCtx = FacesContext.getCurrentInstance();

//		HttpServletRequest r = (HttpServletRequest) fCtx.getExternalContext().getRequest();
		
		//rutaInformes = r.getContextPath()+"\\WebContent\\WEB-INF\\reports\\";		
		
		//Obtenemos el response
		HttpServletResponse response = (HttpServletResponse) fCtx.getExternalContext().getResponse();
		
		try {
			//Creamos un data source con los objetos a mostrar
			JRBeanCollectionDataSource ds =new JRBeanCollectionDataSource(datos);   
			
			//Esto es por si queremos compilar el informe
			//Recuperamos el fichero fuente    
			//JasperDesign jd=JRXmlLoader.load(REPORT_PATH);   
			//Compilamos el informe jrxml   
			//JasperReport report = JasperCompileManager.compileReport(jd);   

			// Rellenamos el informe con la conexion creada y sus parametros establecidos   
			JasperPrint print = JasperFillManager.fillReport(rutaInformes+informe, parametros, ds);   			   

			//Preparamos las cabeceras para la respuesta
			response.setContentType("application/pdf");  
			//con inline el informe se ve directamente en el navegador:
            //response.addHeader("Content-Disposition", "inline; filename=detalleProvision.pdf");  
			//Con attachment el fichero se descarga
            response.addHeader("Content-Disposition", "attachment; filename="+nombreFichero);  

            // Exportamos el informe a formato PDF   
			JasperExportManager.exportReportToPdfStream(print, response.getOutputStream());  

			//Devolvemos el control al navegador dando por finalizado el request: 
            fCtx.getApplication().getStateManager().saveView(fCtx);  
            fCtx.responseComplete();  

		} catch (JRException e) {
			e.printStackTrace();
		} catch (IOException e) {
			//  Auto-generated catch block
			e.printStackTrace();
		}		
	}	
	
	@SuppressWarnings("rawtypes") 
	public static void exportarExcel(String informe, Collection datos, String nombreFichero){
		Map<String,Object> parametros= new HashMap<String, Object>();
		exportarExcel(informe, parametros, datos, nombreFichero);
	}
	
	@SuppressWarnings("rawtypes") 
	public static void exportarExcel(String informe, Map<String,Object> parametros, Collection datos, String nombreFichero) {

		FacesContext fCtx = FacesContext.getCurrentInstance();

		//Obtenemos el response
		HttpServletResponse response = (HttpServletResponse) fCtx.getExternalContext().getResponse();
		
		//HttpServletRequest r = (HttpServletRequest) fCtx.getExternalContext().getRequest();		
		//rutaInformes = r.getContextPath()+"\\WEB-INF\\reports\\";
		
		
		//Miramos cuál es la ruta correcta del fichero		
		rutaInformes="C:\\Program Files\\Apache Software Foundation\\Tomcat 7.0\\webapps\\RegistroOfertasDCM\\WEB-INF\\reports\\";		
		File fichero = new File(rutaInformes+informe);
		if (!fichero.exists()) {
			rutaInformes="D:\\Java\\DesarrolloWebLuna\\RegistroOfertasDCM\\WebContent\\WEB-INF\\reports\\";
		}
						
		try {
			//Creamos un data source con los objetos a mostrar
			JRBeanCollectionDataSource ds =new JRBeanCollectionDataSource(datos);   

			JasperPrint print = JasperFillManager.fillReport(rutaInformes+informe, parametros, ds);   			   
			
			response.setContentType("application/vnd.ms-excel");  
            response.setHeader("Content-Disposition", "attachment;filename="+nombreFichero);  

            JExcelApiExporter exporterXLS = new JExcelApiExporter();  

            exporterXLS.setParameter(JRXlsExporterParameter.JASPER_PRINT, print);  
            exporterXLS.setParameter(JRXlsExporterParameter.IS_DETECT_CELL_TYPE, Boolean.TRUE);  
            exporterXLS.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.FALSE);  
            exporterXLS.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, Boolean.TRUE);  
            exporterXLS.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_COLUMNS, Boolean.TRUE);  
            // exporterXLS.setParameter(  
            // JRXlsExporterParameter.IS_IGNORE_CELL_BORDER,  
            // Boolean.TRUE);  
            exporterXLS.setParameter(JRXlsExporterParameter.OUTPUT_STREAM, response.getOutputStream());  
            exporterXLS.exportReport();  
			
            fCtx.getApplication().getStateManager().saveView(fCtx);  
            fCtx.responseComplete();  
					
		} catch (JRException e) {
			e.printStackTrace();
		} catch (IOException e) {
			//  Auto-generated catch block
			e.printStackTrace();
		}			
		
	}	
	
	
}
