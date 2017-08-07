package net.gefco.filtroseguridad;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.gefco.bbean.PermisosMenuBB;
import net.gefco.modelo.Usuario;

public class FiltroSeguridad implements Filter {

    public FiltroSeguridad() {
    }

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		HttpServletRequest rq = (HttpServletRequest) request;
		HttpServletResponse rp = (HttpServletResponse) response;
		
		HttpSession sesion = rq.getSession(true);
		Usuario usr = (Usuario) sesion.getAttribute("usuario");
		PermisosMenuBB permisos = (PermisosMenuBB) sesion.getAttribute("permisosMenuBB");
		
		String recurso = rq.getRequestURI();		
		
		if(usr!=null && usr.getUsua_codigo()!=0 && permisos.tienePermiso(recurso))			
			chain.doFilter(request, response);
		else{
			System.out.println("FUERA!");
			rp.sendRedirect("../faces/login.jspx");
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		//  Auto-generated method stub
	}

}
